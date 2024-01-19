package com.example.constructionmaterialmarketplace.dto.response;

import com.example.constructionmaterialmarketplace.entity.enums.UserRole;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {

    private String token;

    private UserRole roleName;

    private String email;
}
