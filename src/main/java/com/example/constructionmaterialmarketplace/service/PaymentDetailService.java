package com.example.constructionmaterialmarketplace.service;

import com.example.constructionmaterialmarketplace.entity.Order;
import com.example.constructionmaterialmarketplace.entity.PaymentDetail;
import com.example.constructionmaterialmarketplace.entity.enums.PaymentMethod;
import com.example.constructionmaterialmarketplace.entity.enums.PaymentStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface PaymentDetailService {
    PaymentDetail processNewPayment(Order order, BigDecimal amount, PaymentMethod method);

    List<PaymentDetail> getPaymentDetailsByOrderId(Long orderId);

    PaymentDetail updatePaymentStatus(Long paymentDetailId, PaymentStatus newStatus);

    PaymentDetail getPaymentDetailById(Long paymentDetailId);

    PaymentDetail refundPayment(Long paymentDetailId);
}
