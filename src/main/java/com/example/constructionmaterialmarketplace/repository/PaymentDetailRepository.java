package com.example.constructionmaterialmarketplace.repository;

import com.example.constructionmaterialmarketplace.entity.PaymentDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentDetailRepository extends JpaRepository<PaymentDetail, Long> {

    @Query("select p from PaymentDetail p where p.orderId.id = :orderId")
    List<PaymentDetail> findByOrderId(Long orderId);
}
