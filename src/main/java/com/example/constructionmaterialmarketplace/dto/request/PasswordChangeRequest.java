package com.example.constructionmaterialmarketplace.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordChangeRequest {

    private String oldPassword;

    private String newPassword;
}
