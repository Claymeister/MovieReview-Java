package com.moviereview.web.mapper;

import com.moviereview.web.dto.ReviewDto;
import com.moviereview.web.models.Review;

public class ReviewMapper {
    public static Review mapToReview(ReviewDto reviewDto) {
        return Review.builder()
                .id(reviewDto.getId())
                .title(reviewDto.getTitle())
                .score(reviewDto.getScore())
                .content(reviewDto.getContent())
                .createdOn(reviewDto.getCreatedOn())
                .updatedOn(reviewDto.getUpdatedOn())
                .createdBy(reviewDto.getCreatedBy())
                .movie(reviewDto.getMovie())
                .build();
    }

    public static ReviewDto mapToReviewDto(Review review) {
        return ReviewDto.builder()
                .id(review.getId())
                .title(review.getTitle())
                .score(review.getScore())
                .content(review.getContent())
                .createdOn(review.getCreatedOn())
                .updatedOn(review.getUpdatedOn())
                .createdBy(review.getCreatedBy())
                .movie(review.getMovie())
                .build();
    }
}
