package com.example.product_api.repository;

import com.example.product_api.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Long> {
//TODO use optional review
    Review findByProductIdAndUserId(Long productId, Long userId);

    List<Review>findByProductId(Long productId);
}
