package com.example.constructionmaterialmarketplace.repository;

import com.example.constructionmaterialmarketplace.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query("select o from Order o where o.userId.id = :userId")
    List<Order> findAllByUserId(Long userId);
}
