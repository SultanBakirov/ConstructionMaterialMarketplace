package com.example.constructionmaterialmarketplace.repository;

import com.example.constructionmaterialmarketplace.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}
