package com.example.constructionmaterialmarketplace.service.impl;

import com.example.constructionmaterialmarketplace.dto.response.PaymentGatewayResponse;
import com.example.constructionmaterialmarketplace.entity.PaymentDetail;
import com.example.constructionmaterialmarketplace.entity.enums.PaymentMethod;
import com.example.constructionmaterialmarketplace.service.PaymentGatewayClient;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class PaymentGatewayClientImpl implements PaymentGatewayClient {

    @Override
    public PaymentGatewayResponse processPayment(BigDecimal amount, PaymentMethod method, Long orderId) {
        boolean isSuccessful = true;
        String transactionId = "12345";
        return new PaymentGatewayResponse(isSuccessful, transactionId);
    }

    @Override
    public PaymentGatewayResponse processRefund(PaymentDetail paymentDetail) {
        boolean refundSuccessful = true;
        String transactionId = "refundTx12345";
        return new PaymentGatewayResponse(refundSuccessful, transactionId);
    }
}
