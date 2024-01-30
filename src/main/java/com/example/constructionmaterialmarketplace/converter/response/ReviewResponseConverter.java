package com.example.constructionmaterialmarketplace.converter.response;

import com.example.constructionmaterialmarketplace.dto.response.ReviewResponse;
import com.example.constructionmaterialmarketplace.entity.Review;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReviewResponseConverter {

    public ReviewResponse viewReview(Review review) {
        if (review == null) {
            return null;
        }
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setRating(review.getRating());
        reviewResponse.setComment(review.getComment());
        reviewResponse.setReviewDate(review.getReviewDate());
        return reviewResponse;
    }

    public List<ReviewResponse> getAllReviews(List<Review> reviews) {
        List<ReviewResponse> reviewResponses = new ArrayList<>();
        for (Review review : reviews) {
            reviewResponses.add(viewReview(review));
        }
        return reviewResponses;
    }
}
