package com.example.moviedatabase.service;

import com.example.moviedatabase.entity.Movie;
import com.example.moviedatabase.entity.MovieView;
import com.example.moviedatabase.exception.MovieAlreadyExistsException;
import com.example.moviedatabase.exception.MovieNotFoundException;
import com.example.moviedatabase.mapper.MovieMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    private final MovieMapper movieMapper;

    public MovieService(MovieMapper movieMapper) {
        this.movieMapper = movieMapper;
    }

    //Read処理　全件表示 Mapper呼び出し
    public List<MovieView> findAll(){
        return this.movieMapper.findAll();
    }

    //Read処理　検索表示 Mapper呼び出し
    public MovieView findById(int id) {
        Optional<MovieView> movie = this.movieMapper.findById(id);
        movie.orElseThrow(() -> new MovieNotFoundException("movie not found"));
        return movie.get();
    }

    //POST処理　登録処理 Mapper呼び出し
    public Movie insert(Movie movie) {
        this.movieMapper.checkByName(movie.getName()).ifPresent(foundMovie -> {
            throw new MovieAlreadyExistsException("name :" + movie.getName() + " already exists");
        });
        movieMapper.insert(movie);
        return movie;
    }

    //PATCH処理 更新処理 Mapper呼び出し
    public Movie update(Movie movie) {
        this.movieMapper.checkById(movie.getId()).orElseThrow(() -> new MovieNotFoundException("id does not exist"));
        movieMapper.update(movie);
        return movie;
    }

    //DELETE処理　削除処理 Mapper呼び出し
    public int delete(int id) {
        this.movieMapper.checkById(id).orElseThrow(() -> new MovieNotFoundException("id does not exist"));
        movieMapper.delete(id);
        return id;
    }

}
