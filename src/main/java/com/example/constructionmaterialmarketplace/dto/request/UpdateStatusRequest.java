package com.example.constructionmaterialmarketplace.dto.request;

import com.example.constructionmaterialmarketplace.entity.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusRequest {

    private PaymentStatus status;
}
