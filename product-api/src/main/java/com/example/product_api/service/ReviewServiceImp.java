package com.example.product_api.service;

import com.example.product_api.entity.Review;
import com.example.product_api.repository.ReviewRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ReviewServiceImp implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public List<Review> getReviewsByProductId(Long productId) {

        return reviewRepository.findByProductId(productId);
    }

    @Override
    public Review createReview(Review review) {
//TODO .orelsethrow
        Review existingReview = reviewRepository.findByProductIdAndUserId(review.getProductId(), review.getUserId());
        if (existingReview != null) {
            throw new RuntimeException("Customer has already submitted a review for this product.");
        }

        if (StringUtils.isBlank(review.getContent())) {
            throw new RuntimeException("review content ca not be null or empty");
        }
        review.setContent(review.getContent().trim());
        return reviewRepository.save(review);
    }

    @Override
    public Review updateReview(Long productId, String content, Long userId) {
        Review existingReview = reviewRepository.findByProductIdAndUserId(productId, userId);
        if (existingReview == null) {
            throw new RuntimeException("Review not found.");
        }
        if (StringUtils.isBlank(content)) {
            throw new RuntimeException("review content ca not be null or empty");
        }
        existingReview.setContent(content.trim());
        return reviewRepository.save(existingReview);
    }

    @Override
    public void deleteReview(Long productId, Long userId) {
        Review review = reviewRepository.findByProductIdAndUserId(productId, userId);
        if (review == null) {
            throw new RuntimeException("Review not found.");
        }
        reviewRepository.delete(review);
    }

}