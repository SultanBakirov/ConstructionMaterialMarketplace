package com.example.constructionmaterialmarketplace.dto.request;

import com.example.constructionmaterialmarketplace.entity.Order;
import com.example.constructionmaterialmarketplace.entity.enums.PaymentMethod;
import com.example.constructionmaterialmarketplace.entity.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetailRequest {

    private Order orderId;

    private BigDecimal amount;

    private Date paymentDate;

    private PaymentMethod paymentMethod;

    private PaymentStatus status;
}
