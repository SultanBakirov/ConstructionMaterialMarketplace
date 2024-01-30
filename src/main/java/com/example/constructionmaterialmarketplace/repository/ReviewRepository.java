package com.example.constructionmaterialmarketplace.repository;

import com.example.constructionmaterialmarketplace.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select r from Review r where r.productId.id = :productId")
    List<Review> findAllByProductId(Long productId);

    @Query("select r from Review r where r.userId.id = :userId")
    List<Review> findAllByUserId(Long userId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.productId.id = :productId")
    Double findAverageRatingByProductId(@Param("productId") Long productId);
}
