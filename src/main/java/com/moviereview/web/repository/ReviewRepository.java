package com.moviereview.web.repository;

import com.moviereview.web.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findAllByOrderByUpdatedOnDesc();
}
