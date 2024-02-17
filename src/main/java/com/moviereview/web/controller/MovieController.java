package com.moviereview.web.controller;

import com.moviereview.web.dto.MovieDto;
import com.moviereview.web.models.Movie;
import com.moviereview.web.models.UserEntity;
import com.moviereview.web.security.SecurityUtil;
import com.moviereview.web.service.MovieService;
import com.moviereview.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MovieController {
    private MovieService movieService;
    private UserService userService;

    @Autowired
    public MovieController(MovieService movieService, UserService userService) {
        this.userService = userService;
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public String listMovies(Model model) {
        UserEntity user = new UserEntity();
        List<MovieDto> movies = movieService.findAllMovies();
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("movies", movies);
        return "movies-list";
    }

    @GetMapping("/movies/{movieId}")
    public String movieDetail(@PathVariable("movieId") long movieId, Model model) {
        UserEntity user = new UserEntity();
        MovieDto movieDto = movieService.findMovieById(movieId);
        String username = SecurityUtil.getSessionUser();
        if(username != null) {
            user = userService.findByUsername(username);
            model.addAttribute("user", user);
        }
        model.addAttribute("user", user);
        model.addAttribute("movie", movieDto);
        return "movies-detail";
    }

    @GetMapping("/movies/new")
    public String createMovieForm(Model model) {
        Movie movie = new Movie();
        model.addAttribute("movie", movie);
        return "movies-create";
    }

    @GetMapping("/movies/{movieId}/delete")
    public String deleteMovie(@PathVariable("movieId")Long movieId) {
        movieService.delete(movieId);
        return "redirect:/movies";
    }

    @GetMapping("/movies/search")
    public String searchMovie(@RequestParam(value = "query") String query, Model model) {
        List<MovieDto> movies = movieService.searchMovies(query);
        model.addAttribute("movies", movies);
        return "movies-list";
    }

    @PostMapping("/movies/new")
    public String saveMovie(@Valid @ModelAttribute("movie") MovieDto movieDto, BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("movie", movieDto);
            return "movies-create";
        }
        movieService.saveMovie(movieDto);
        return "redirect:/movies";
    }

    @GetMapping("/movies/{movieId}/edit")
    public String editMovieForm(@PathVariable("movieId") Long movieId, Model model) {
        MovieDto movie = movieService.findMovieById(movieId);
        model.addAttribute("movie", movie);
        return "movies-edit";
    }
    @PostMapping("/movies/{movieId}/edit")
    public String updateMovie(@PathVariable("movieId") Long movieId,
                             @Valid @ModelAttribute("movie") MovieDto movie,
                             BindingResult result, Model model) {
        if(result.hasErrors()) {
            model.addAttribute("movie", movie);
            return "movies-edit";
        }
        movie.setId(movieId);
        movieService.updateMovie(movie);
        return "redirect:/movies";
    }
}
