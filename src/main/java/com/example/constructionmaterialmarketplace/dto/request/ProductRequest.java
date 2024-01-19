package com.example.constructionmaterialmarketplace.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {

    private String productName;

    private String image;

    private String description;

    private BigDecimal price;

    private Integer stockQuantity;
}
