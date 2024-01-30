package com.example.constructionmaterialmarketplace.converter.request;

import com.example.constructionmaterialmarketplace.dto.request.ReviewRequest;
import com.example.constructionmaterialmarketplace.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewRequestConverter {

    public Review create(ReviewRequest reviewRequest) {
        if (reviewRequest == null) {
            return null;
        }
        Review review = new Review();
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        review.setReviewDate(reviewRequest.getReviewDate());
        return review;
    }

    public void update(Review review, ReviewRequest reviewRequest) {
        review.setRating(reviewRequest.getRating());
        review.setComment(reviewRequest.getComment());
        review.setReviewDate(reviewRequest.getReviewDate());
    }
}
