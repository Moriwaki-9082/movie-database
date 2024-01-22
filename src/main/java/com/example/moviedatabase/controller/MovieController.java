package com.example.moviedatabase.controller;

import com.example.moviedatabase.entity.Movie;
import com.example.moviedatabase.entity.MovieView;
import com.example.moviedatabase.exception.MovieAlreadyExistsException;
import com.example.moviedatabase.exception.MovieNotFoundException;
import com.example.moviedatabase.request.MovieRequest;
import com.example.moviedatabase.request.MovieUpdateRequest;
import com.example.moviedatabase.response.MovieResponse;
import com.example.moviedatabase.service.MovieService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;

@RestController
public class MovieController {

    private final MovieService movieService;
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    //全件表示
    @GetMapping("/movies")
    public List<MovieView> getMovies(){
        return movieService.findAll();
    }

    //検索表示
    @GetMapping("/movies/{id}")
    public MovieView getMovie(@PathVariable("id") int id) {
        return movieService.findById(id);
    }

    //対象が存在しない場合の例外処理
    @ExceptionHandler(value = MovieNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleMovieNotFoundException(
            MovieNotFoundException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
                "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.NOT_FOUND);
    }

    //登録処理時、Nameが既に存在している物の場合の例外処理
    @ExceptionHandler(value = MovieAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleMovieAlreadyExistsException(
            MovieAlreadyExistsException e, HttpServletRequest request) {
        Map<String, String> body = Map.of(
                "timestamp", ZonedDateTime.now().toString(),
                "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "message", e.getMessage(),
                "path", request.getRequestURI());
        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }

    //登録処理
    @PostMapping("/movies")
    public ResponseEntity<MovieResponse> insert(@RequestBody MovieRequest movieRequest, UriComponentsBuilder uriBuilder) {
        Movie movie = movieService.insert(movieRequest.convertToMovie());
        URI location = uriBuilder.path("/movies/{id}").buildAndExpand(movie.getId()).toUri();
        MovieResponse body = new MovieResponse("movie　data created");
        return ResponseEntity.created(location).body(body);
    }

    //更新処理
    @PatchMapping("/movies")
    public ResponseEntity<String> update(@RequestBody MovieUpdateRequest movieUpdateRequest) {
        movieService.update(movieUpdateRequest.updateToMovie());
        return ResponseEntity.ok("movie data updated");
    }

    //削除処理
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int id){
        movieService.delete(id);
        return ResponseEntity.ok("movie data deleted");
    }

}
