package com.example.constructionmaterialmarketplace.service;

import com.example.constructionmaterialmarketplace.dto.request.ReviewRequest;
import com.example.constructionmaterialmarketplace.dto.response.ReviewResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ReviewService {
    ReviewResponse saveReview(ReviewRequest reviewRequest);

    ReviewResponse getReviewById(Long reviewId);

    List<ReviewResponse> getReviewsByProductId(Long productId);

    List<ReviewResponse> getReviewsByUserId(Long userId);

    ReviewResponse updateReview(Long reviewId, ReviewRequest reviewRequest);

    SimpleResponse deleteReviewById(Long reviewId);

    Double calculateAverageRating(Long productId);
}
