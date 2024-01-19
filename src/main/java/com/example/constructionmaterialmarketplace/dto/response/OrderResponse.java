package com.example.constructionmaterialmarketplace.dto.response;

import com.example.constructionmaterialmarketplace.entity.enums.OrderStatus;
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
public class OrderResponse {

    private Long id;

    private Date orderDate;

    private BigDecimal totalAmount;

    private OrderStatus status;
}
