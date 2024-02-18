package com.moviereview.web.service;

import com.moviereview.web.dto.ReviewDto;
import com.moviereview.web.models.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    void createReview(Long movieId, ReviewDto reviewDto);
    List<ReviewDto> findAllReviews();
    ReviewDto findByReviewId(Long reviewId);
    void updateReview(ReviewDto reviewDto);
    void deleteReview(long reviewId);


}
