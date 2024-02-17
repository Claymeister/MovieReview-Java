package com.moviereview.web.service;

import com.moviereview.web.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    void createReview(Long movieId, ReviewDto reviewDto);
    List<ReviewDto> findAllReviews();
    ReviewDto findByReviewId(Long reviewId);
    void updateReview(ReviewDto reviewDto);
    void deleteReview(long reviewId);
}
