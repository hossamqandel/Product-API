package com.example.product_api.controller;

import com.example.product_api.entity.Review;
import com.example.product_api.service.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {

        this.reviewService = reviewService;
    }

    @PostMapping("/product")
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        Review newReview = reviewService.createReview(review);
        return ResponseEntity.status(HttpStatus.CREATED).body(newReview);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getReviewsByProductId(@PathVariable Long productId) {
        List<Review> reviews = reviewService.getReviewsByProductId(productId);
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reviews);
    }

    @PutMapping("/product/{productId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long productId,
                                               @RequestParam String content,
                                               @RequestParam Long userId) {
        Review review = reviewService.updateReview(productId, content, userId);
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long productId,
                                             @RequestParam Long userId) {
        reviewService.deleteReview(productId, userId);
        return ResponseEntity.ok().build();
    }
}
