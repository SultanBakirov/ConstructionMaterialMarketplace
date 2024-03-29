package com.example.constructionmaterialmarketplace.entity;

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
@Table(name = "addresses")
public class Address {

    @Id
    @SequenceGenerator(name = "address_gen", sequenceName = "address_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_gen")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User userId;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;
}
