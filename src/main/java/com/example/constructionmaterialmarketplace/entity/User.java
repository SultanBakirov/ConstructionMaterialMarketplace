package com.example.constructionmaterialmarketplace.entity;

import com.example.constructionmaterialmarketplace.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    // role constants
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_BUYER = "BUYER";

    @Id
    @SequenceGenerator(name = "user_gen", sequenceName = "user_seq", allocationSize = 1, initialValue = 7)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_gen")
    private Long id;

    private String username;

    private String password;

    private String email;

    private UserRole role;

    @Column(name = "contact_details")
    private String contactDetails;

}
