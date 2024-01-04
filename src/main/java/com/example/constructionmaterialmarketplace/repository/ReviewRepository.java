package com.example.constructionmaterialmarketplace.repository;

import com.example.constructionmaterialmarketplace.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
}
