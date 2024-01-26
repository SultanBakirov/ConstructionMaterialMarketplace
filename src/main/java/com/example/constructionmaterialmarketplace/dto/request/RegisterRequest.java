package com.example.constructionmaterialmarketplace.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    private String username;

    private String email;

    private String phoneNumber;

    private String password;
}
