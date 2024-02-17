package com.moviereview.web.service.impl;

import com.moviereview.web.dto.ReviewDto;
import com.moviereview.web.models.Movie;
import com.moviereview.web.models.Review;
import com.moviereview.web.repository.MovieRepository;
import com.moviereview.web.repository.ReviewRepository;
import com.moviereview.web.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.moviereview.web.mapper.ReviewMapper.mapToReview;
import static com.moviereview.web.mapper.ReviewMapper.mapToReviewDto;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private MovieRepository movieRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, MovieRepository movieRepository) {
        this.reviewRepository = reviewRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public void createReview(Long movieId, ReviewDto reviewDto) {
        Movie movie = movieRepository.findById(movieId).get();
        Review review = mapToReview(reviewDto);
        review.setMovie(movie);
        reviewRepository.save(review);
    }

    @Override
    public List<ReviewDto> findAllReviews() {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().map(review -> mapToReviewDto(review)).collect(Collectors.toList());
    }

    @Override
    public ReviewDto findByReviewId(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).get();
        return mapToReviewDto(review);
    }

    @Override
    public void updateReview(ReviewDto reviewDto) {
        Review review = mapToReview(reviewDto);
        reviewRepository.save(review);
    }

    @Override
    public void deleteReview(long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
