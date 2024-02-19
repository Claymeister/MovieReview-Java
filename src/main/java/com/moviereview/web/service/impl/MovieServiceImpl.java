package com.moviereview.web.service.impl;

import com.moviereview.web.dto.MovieDto;
import com.moviereview.web.models.Movie;
import com.moviereview.web.models.UserEntity;
import com.moviereview.web.repository.MovieRepository;
import com.moviereview.web.repository.UserRepository;
import com.moviereview.web.security.SecurityUtil;
import com.moviereview.web.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.moviereview.web.mapper.MovieMapper.mapToMovie;
import static com.moviereview.web.mapper.MovieMapper.mapToMovieDto;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieRepository movieRepository;
    private UserRepository userRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.movieRepository = movieRepository;
    }

    @Override
    public List<MovieDto> findAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return movies.stream().map((movie) -> mapToMovieDto(movie)).collect(Collectors.toList());
    }
    @Override
    public List<MovieDto> findMoviesByTitleAndGenre(String titled, String genre) {
        List<Movie> movies = movieRepository.findMoviesByTitleAndGenre(titled, genre);
        return movies.stream().map((movie) -> mapToMovieDto(movie)).collect(Collectors.toList());
    }

    @Override
    public Movie saveMovie(MovieDto movieDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Movie movie = mapToMovie(movieDto);
        movie.setCreatedBy(user);
        return movieRepository.save(movie);
    }

    @Override
    public MovieDto findMovieById(Long movieId) {
        Movie movie = movieRepository.findById(movieId).get();
        return mapToMovieDto(movie);
    }

    @Override
    public void updateMovie(MovieDto movieDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity user = userRepository.findByUsername(username);
        Movie movie = mapToMovie(movieDto);
        movie.setCreatedBy(user);
        movieRepository.save(movie);
    }

    @Override
    public void delete(Long movieId) {
        movieRepository.deleteById(movieId);
    }

    @Override
    public List<MovieDto> searchMovies(String query) {
        List<Movie> movies = movieRepository.searchMovies(query);
        return movies.stream().map(movie -> mapToMovieDto(movie)).collect(Collectors.toList());
    }
    @Override
    public List<MovieDto> sortDescMovies(String query) {
        List<Movie> movies = movieRepository.sortDescMovies(query);
        return movies.stream().map(movie -> mapToMovieDto(movie)).collect(Collectors.toList());
    };
    @Override
    public List<MovieDto> sortAscMovies(String query) {
        List<Movie> movies = movieRepository.sortAscMovies(query);
        return movies.stream().map(movie -> mapToMovieDto(movie)).collect(Collectors.toList());
    };

}
