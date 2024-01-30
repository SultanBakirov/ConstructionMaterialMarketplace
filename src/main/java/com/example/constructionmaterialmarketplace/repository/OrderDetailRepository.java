package com.example.constructionmaterialmarketplace.repository;

import com.example.constructionmaterialmarketplace.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query("select o from OrderDetail o where o.orderId.id = :orderId")
    List<OrderDetail> findAllByOrderId(Long orderId);
}
