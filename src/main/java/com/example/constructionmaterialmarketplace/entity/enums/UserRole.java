package com.example.constructionmaterialmarketplace.entity.enums;

import org.springframework.security.core.GrantedAuthority;

public enum UserRole implements GrantedAuthority {
    ADMIN, BUYER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}
