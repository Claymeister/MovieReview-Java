package com.moviereview.web.service;

import com.moviereview.web.dto.MovieDto;
import com.moviereview.web.models.Movie;

import java.util.List;

public interface MovieService {
    List<MovieDto> findAllMovies();
    List<MovieDto> findMoviesByTitleAndGenre(String titled, String genre);
    Movie saveMovie(MovieDto movieDto);
    MovieDto findMovieById(Long movieId);
    void updateMovie(MovieDto movie);
    void delete(Long movieId);
    List<MovieDto> searchMovies(String query);
    List<MovieDto> sortDescMovies(String query);
    List<MovieDto> sortAscMovies(String query);
}
