package com.moviereview.web.controller;

import com.moviereview.web.dto.ReviewDto;
import com.moviereview.web.models.Review;
import com.moviereview.web.models.UserEntity;
import com.moviereview.web.security.SecurityUtil;
import com.moviereview.web.service.MovieService;
import com.moviereview.web.service.ReviewService;
import com.moviereview.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ReviewController {

    private ReviewService reviewService;
    private UserService userService;
    private MovieService movieService;

    @Autowired
    public ReviewController(ReviewService reviewService, UserService userService) {
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @GetMapping("/reviews")
    public String reviewList(Model model) {
        UserEntity user = new UserEntity();
        List<ReviewDto> reviews = reviewService.findAllReviews();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("reviews", reviews);
        return "reviews-list";
    }

    @GetMapping("/reviews/{reviewId}")
    public String viewReview(@PathVariable("reviewId")Long reviewId, Model model) {
        UserEntity user = new UserEntity();
        ReviewDto reviewDto = reviewService.findByReviewId(reviewId);
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("movie", reviewDto.getMovie());
        model.addAttribute("user", user);
        model.addAttribute("review", reviewDto);
        return "reviews-detail";
    }

    @GetMapping("/reviews/{movieId}/new")
    public String createReviewForm(@PathVariable("movieId") Long movieId, Model model) {
        Review review = new Review();
        model.addAttribute("movieId", movieId);
        model.addAttribute("review", review);
        return "reviews-create";
    }

    @GetMapping("/reviews/{reviewId}/edit")
    public String editReviewForm(@PathVariable("reviewId") Long reviewId, Model model) {
        ReviewDto review = reviewService.findByReviewId(reviewId);
        model.addAttribute("review", review);
        return "reviews-edit";
    }

    @PostMapping("/reviews/{movieId}")
    public String createReview(@PathVariable("movieId") Long movieId, @ModelAttribute("review") ReviewDto reviewDto,
                              BindingResult result,
                              Model model) {
        if(result.hasErrors()) {
            model.addAttribute("review", reviewDto);
            return "movies-create";
        }
        reviewService.createReview(movieId, reviewDto);
        return "redirect:/movies/" + movieId;
    }

    @PostMapping("/reviews/{reviewId}/edit")
    public String updateReview(@PathVariable("reviewId") Long reviewId,
                             @Valid @ModelAttribute("review") ReviewDto review,
                             BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("review", review);
            return "reviews-edit";
        }
        ReviewDto reviewDto = reviewService.findByReviewId(reviewId);
        review.setId(reviewId);
        review.setMovie(reviewDto.getMovie());
        review.setCreatedBy(reviewDto.getCreatedBy());
        reviewService.updateReview(review);
        return "redirect:/reviews";
    }

    @GetMapping("/reviews/{reviewId}/delete")
    public String deleteReview(@PathVariable("reviewId") long reviewId) {
        reviewService.deleteReview(reviewId);
        return "redirect:/reviews";
    }

}
