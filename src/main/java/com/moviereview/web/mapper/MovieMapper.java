package com.moviereview.web.mapper;

import com.moviereview.web.dto.MovieDto;
import com.moviereview.web.models.Movie;

import java.util.stream.Collectors;

import static com.moviereview.web.mapper.ReviewMapper.mapToReviewDto;

public class MovieMapper {
    public static Movie mapToMovie(MovieDto movie) {
        Movie movieDto = Movie.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .photoUrl(movie.getPhotoUrl())
                .content(movie.getContent())
                .createdBy(movie.getCreatedBy())
                .createdOn(movie.getCreatedOn())
                .updatedOn(movie.getUpdatedOn())
                .build();
        return  movieDto;
    }

    public static MovieDto mapToMovieDto(Movie movie) {
        MovieDto movieDto = MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .photoUrl(movie.getPhotoUrl())
                .content(movie.getContent())
                .createdBy(movie.getCreatedBy())
                .createdOn(movie.getCreatedOn())
                .updatedOn(movie.getUpdatedOn())
                .reviews(movie.getReviews().stream().map((review) -> mapToReviewDto(review)).collect(Collectors.toList()))
                .build();
        return movieDto;
    }
}
