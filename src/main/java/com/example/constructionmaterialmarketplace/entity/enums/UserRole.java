package com.example.constructionmaterialmarketplace.entity.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
@AllArgsConstructor
public enum UserRole implements GrantedAuthority {
    ADMIN(1L),
    BUYER(2L);

    private final Long id;

    public static UserRole getById(Long id) {
        for (UserRole role : UserRole.values()) {
            if (role.id.equals(id)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No role found for id: " + id);
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}
