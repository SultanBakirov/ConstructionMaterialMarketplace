package com.example.constructionmaterialmarketplace.controller;

import com.example.constructionmaterialmarketplace.dto.request.ReviewRequest;
import com.example.constructionmaterialmarketplace.dto.response.ReviewResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import com.example.constructionmaterialmarketplace.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/save")
    public ReviewResponse saveReview(@RequestBody ReviewRequest reviewRequest) {
        return reviewService.saveReview(reviewRequest);
    }

    @GetMapping("/{reviewId}")
    public ReviewResponse getReviewById(@PathVariable Long reviewId) {
        return reviewService.getReviewById(reviewId);
    }

    @GetMapping("/{productId}")
    public List<ReviewResponse> getReviewsByProductId(@PathVariable Long productId) {
        return reviewService.getReviewsByProductId(productId);
    }

    @GetMapping("/{userId}")
    public List<ReviewResponse> getReviewsByUserId(@PathVariable Long userId) {
        return reviewService.getReviewsByUserId(userId);
    }

    @PutMapping("/{reviewId}")
    public ReviewResponse updateReviewData(@PathVariable Long reviewId, @RequestBody ReviewRequest reviewRequest) {
        return reviewService.updateReview(reviewId, reviewRequest);
    }

    @DeleteMapping("/{reviewId}")
    public SimpleResponse deleteReviewById(@PathVariable Long reviewId) {
        return reviewService.deleteReviewById(reviewId);
    }

    @GetMapping("/{productId}/average-rating")
    public ResponseEntity<Double> calculateAverageRating(@PathVariable Long productId) {
        double averageRating = reviewService.calculateAverageRating(productId);
        return ResponseEntity.ok(averageRating);
    }
}
