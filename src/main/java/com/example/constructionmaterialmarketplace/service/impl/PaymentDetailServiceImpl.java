package com.example.constructionmaterialmarketplace.service.impl;

import com.example.constructionmaterialmarketplace.dto.response.PaymentGatewayResponse;
import com.example.constructionmaterialmarketplace.entity.Order;
import com.example.constructionmaterialmarketplace.entity.PaymentDetail;
import com.example.constructionmaterialmarketplace.entity.enums.PaymentMethod;
import com.example.constructionmaterialmarketplace.entity.enums.PaymentStatus;
import com.example.constructionmaterialmarketplace.exception.InvalidStatusUpdateException;
import com.example.constructionmaterialmarketplace.repository.OrderRepository;
import com.example.constructionmaterialmarketplace.repository.PaymentDetailRepository;
import com.example.constructionmaterialmarketplace.service.PaymentDetailService;
import com.example.constructionmaterialmarketplace.service.PaymentGatewayClient;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentDetailServiceImpl implements PaymentDetailService {

    private static final Logger logger = LoggerFactory.getLogger(PaymentDetailServiceImpl.class);

    private final PaymentDetailRepository paymentDetailRepository;

    private final OrderRepository orderRepository;

    private final PaymentGatewayClient paymentGatewayClient;

    @Override
    public PaymentDetail processNewPayment(Order order, BigDecimal amount, PaymentMethod method) {
        PaymentDetail paymentDetail = new PaymentDetail();
        paymentDetail.setOrderId(order);
        paymentDetail.setAmount(amount);
        paymentDetail.setPaymentMethod(method);
        paymentDetail.setPaymentDate(new Date());
        paymentDetail.setStatus(PaymentStatus.PENDING);

        PaymentGatewayResponse gatewayResponse = paymentGatewayClient.processPayment(amount, method, order.getId());
        if (gatewayResponse.isSuccessful()) {
            paymentDetail.setStatus(PaymentStatus.SUCCESS);
        } else {
            paymentDetail.setStatus(PaymentStatus.FAILED);
        }

        return paymentDetailRepository.save(paymentDetail);
    }

    @Override
    public PaymentDetail getPaymentDetailById(Long paymentDetailId) {
        return paymentDetailRepository.findById(paymentDetailId)
                .orElseThrow(() -> new EntityNotFoundException("Payment detail not found for ID: " + paymentDetailId));
    }

    @Override
    public List<PaymentDetail> getPaymentDetailsByOrderId(Long orderId) {
        boolean orderExists = orderRepository.existsById(orderId);
        if (!orderExists) {
            throw new EntityNotFoundException("Order not found with ID: " + orderId);
        }
        return paymentDetailRepository.findByOrderId(orderId);
    }

    @Override
    public PaymentDetail updatePaymentStatus(Long paymentDetailId, PaymentStatus newStatus) {
        PaymentDetail paymentDetail = paymentDetailRepository.findById(paymentDetailId)
                .orElseThrow(() -> new EntityNotFoundException("Payment Detail not found with id: " + paymentDetailId));

        if (!isStatusUpdateAllowed(paymentDetail.getStatus(), newStatus)) {
            throw new InvalidStatusUpdateException("Update from " + paymentDetail.getStatus() + " to " + newStatus + " is not allowed.");
        }

        paymentDetail.setStatus(newStatus);
        return paymentDetailRepository.save(paymentDetail);
    }

    private boolean isStatusUpdateAllowed(PaymentStatus currentStatus, PaymentStatus newStatus) {
        if (currentStatus == PaymentStatus.SUCCESS && newStatus == PaymentStatus.PENDING) {
            return false;
        }
        return true;
    }

    @Override
    public PaymentDetail refundPayment(Long paymentDetailId) {
        PaymentDetail paymentDetail = paymentDetailRepository.findById(paymentDetailId)
                .orElseThrow(() -> new EntityNotFoundException("Payment detail not found with ID: " + paymentDetailId));
        if (!canBeRefunded(paymentDetail)) {
            throw new InvalidStatusUpdateException("The payment cannot be refunded.");
        }
        PaymentGatewayResponse refundResponse = paymentGatewayClient.processRefund(paymentDetail);
        if (refundResponse.isSuccessful()) {
            paymentDetail.setStatus(PaymentStatus.REFUNDED);
        } else {
            paymentDetail.setStatus(PaymentStatus.REFUND_FAILED);
            logger.error("Refund failed for PaymentDetail ID: " + paymentDetail.getId());
        }
        return paymentDetailRepository.save(paymentDetail);
    }

    private boolean canBeRefunded(PaymentDetail paymentDetail) {
        if (paymentDetail.getStatus() != PaymentStatus.SUCCESS) {
            return false;
        }
        long refundablePeriodInDays = 30;
        long daysSincePayment = ChronoUnit.DAYS.between(
                paymentDetail.getPaymentDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                LocalDate.now()
        );
        if (daysSincePayment > refundablePeriodInDays) {
            return false;
        }
        return true;
    }
}
