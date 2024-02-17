package com.moviereview.web.mapper;

import com.moviereview.web.dto.ReviewDto;
import com.moviereview.web.models.Review;

public class ReviewMapper {
    public static Review mapToReview(ReviewDto reviewDto) {
        return Review.builder()
                .id(reviewDto.getId())
                .name(reviewDto.getName())
                .startTime(reviewDto.getStartTime())
                .endTime(reviewDto.getEndTime())
                .type(reviewDto.getType())
                .photoUrl(reviewDto.getPhotoUrl())
                .createdOn(reviewDto.getCreatedOn())
                .updatedOn(reviewDto.getUpdatedOn())
                .movie(reviewDto.getMovie())
                .build();
    }

    public static ReviewDto mapToReviewDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .name(review.getName())
                .startTime(review.getStartTime())
                .endTime(review.getEndTime())
                .type(review.getType())
                .photoUrl(review.getPhotoUrl())
                .createdOn(review.getCreatedOn())
                .updatedOn(review.getUpdatedOn())
                .movie(review.getMovie())
                .build();
    }
}
