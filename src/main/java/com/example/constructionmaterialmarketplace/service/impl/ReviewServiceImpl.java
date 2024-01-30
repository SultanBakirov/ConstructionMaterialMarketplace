package com.example.constructionmaterialmarketplace.service.impl;

import com.example.constructionmaterialmarketplace.converter.request.ReviewRequestConverter;
import com.example.constructionmaterialmarketplace.converter.response.ReviewResponseConverter;
import com.example.constructionmaterialmarketplace.dto.request.ReviewRequest;
import com.example.constructionmaterialmarketplace.dto.response.ReviewResponse;
import com.example.constructionmaterialmarketplace.dto.response.SimpleResponse;
import com.example.constructionmaterialmarketplace.entity.Review;
import com.example.constructionmaterialmarketplace.exception.NotFoundException;
import com.example.constructionmaterialmarketplace.repository.ProductRepository;
import com.example.constructionmaterialmarketplace.repository.ReviewRepository;
import com.example.constructionmaterialmarketplace.repository.UserRepository;
import com.example.constructionmaterialmarketplace.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ReviewRequestConverter reviewRequestConverter;

    private final ReviewResponseConverter reviewResponseConverter;

    @Override
    public ReviewResponse saveReview(ReviewRequest reviewRequest) {
        Review review = reviewRequestConverter.create(reviewRequest);
        reviewRepository.save(review);
        return reviewResponseConverter.viewReview(review);
    }

    @Override
    public ReviewResponse getReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundException(String.format("Review with id = %d not found", reviewId)));
        return reviewResponseConverter.viewReview(review);
    }

    @Override
    public List<ReviewResponse> getReviewsByProductId(Long productId) {
        if (!productRepository.existsById(productId)) {
            throw new IllegalArgumentException(String.format("Product not found with id: " + productId));
        }
        List<Review> reviews = reviewRepository.findAllByProductId(productId);
        return reviewResponseConverter.getAllReviews(reviews);
    }

    @Override
    public List<ReviewResponse> getReviewsByUserId(Long userId) {
        if (!userRepository.existsById(userId)) {
            throw new IllegalArgumentException(String.format("User not found with id: " + userId));
        }
        List<Review> reviews = reviewRepository.findAllByUserId(userId);
        return reviewResponseConverter.getAllReviews(reviews);
    }

    @Override
    public ReviewResponse updateReview(Long reviewId, ReviewRequest reviewRequest) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundException(String.format("Review not found with id: " + reviewId)));
        reviewRequestConverter.update(review, reviewRequest);
        return reviewResponseConverter.viewReview(reviewRepository.save(review));
    }

    @Override
    public SimpleResponse deleteReviewById(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new NotFoundException(String.format("Review not found with id: " + reviewId)));
        reviewRepository.delete(review);
        return new SimpleResponse("The Review removed!");
    }

    @Override
    public Double calculateAverageRating(Long productId) {
        boolean productExists = productRepository.existsById(productId);
        if (!productExists) {
            throw new IllegalArgumentException(String.format("Product not found with id: " + productId));
        }
        Double average = reviewRepository.findAverageRatingByProductId(productId);
        return average != null ? average : 0.0;
    }
}
