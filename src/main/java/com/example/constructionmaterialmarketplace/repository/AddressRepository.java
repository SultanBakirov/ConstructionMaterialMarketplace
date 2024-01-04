package com.example.constructionmaterialmarketplace.repository;

import com.example.constructionmaterialmarketplace.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
