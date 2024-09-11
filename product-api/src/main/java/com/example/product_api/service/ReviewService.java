package com.example.product_api.service;

import com.example.product_api.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewsByProductId(Long productId);

    Review createReview(Review review);

    Review updateReview(Long productId, String content, Long userId);

    void deleteReview(Long productId, Long userId);
}
