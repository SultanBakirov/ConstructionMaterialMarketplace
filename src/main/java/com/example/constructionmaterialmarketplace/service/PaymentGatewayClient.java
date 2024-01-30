package com.example.constructionmaterialmarketplace.service;

import com.example.constructionmaterialmarketplace.dto.response.PaymentGatewayResponse;
import com.example.constructionmaterialmarketplace.entity.PaymentDetail;
import com.example.constructionmaterialmarketplace.entity.enums.PaymentMethod;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface PaymentGatewayClient {

    PaymentGatewayResponse processPayment(BigDecimal amount, PaymentMethod method, Long orderId);

    PaymentGatewayResponse processRefund(PaymentDetail paymentDetail);
}
