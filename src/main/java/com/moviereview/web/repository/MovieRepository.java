package com.moviereview.web.repository;

import com.moviereview.web.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    Optional<Movie> findByTitle(String url);
    @Query("SELECT c FROM Movie c WHERE c.title LIKE CONCAT('%', :titled, '%') AND c.genre LIKE CONCAT('%', :genre, '%')")
    List<Movie> findMoviesByTitleAndGenre(String titled, String genre);
    @Query("SELECT c from Movie c WHERE c.title LIKE CONCAT('%', :query, '%')")
    List<Movie> searchMovies(String query);
    @Query("SELECT c FROM Movie c ORDER BY :query DESC")
    List<Movie> sortDescMovies(String query);
    @Query("SELECT c FROM Movie c ORDER BY :query ASC")
    List<Movie> sortAscMovies(String query);

}
