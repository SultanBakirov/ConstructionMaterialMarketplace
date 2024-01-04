package com.example.constructionmaterialmarketplace.repository;

import com.example.constructionmaterialmarketplace.entity.PaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {
}
