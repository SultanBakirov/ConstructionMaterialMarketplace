package com.example.constructionmaterialmarketplace.dto.response;

import com.example.constructionmaterialmarketplace.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddressResponse {

    private Long id;

    private User userId;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;
}
