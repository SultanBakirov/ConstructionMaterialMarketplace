package com.example.constructionmaterialmarketplace.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentGatewayResponse {

    private boolean isSuccessful;

    private String transactionId;
}
