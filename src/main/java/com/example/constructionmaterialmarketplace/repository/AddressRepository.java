package com.example.constructionmaterialmarketplace.repository;

import com.example.constructionmaterialmarketplace.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("select a from Address a where a.userId.id = :userId")
    List<Address> findAllByUserId(Long userId);
}
