package com.example.moviedatabase.mapper;
import com.example.moviedatabase.entity.Movie;
import com.example.moviedatabase.entity.MovieView;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Optional;

@Mapper
public interface MovieMapper {

    //Read処理　全件表示　表示用DB(movies_view)呼び出し
    @Select("SELECT * FROM movies_view")
    List<MovieView> findAll();

    //Read処理　検索表示　表示用DB(movies_view)呼び出し
    @Select("SELECT * FROM movies_view WHERE id = #{id}")
    Optional<MovieView> findById(int id);

    //POST処理　登録処理
    @Insert("INSERT INTO movies (name, releaseYear, country) VALUES (#{name}, #{releaseYear}, #{country})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    void insert(Movie movie);

    //POST処理時に、既に存在しているNameか確認
    @Select("SELECT * FROM movies WHERE name = #{name}")
    Optional<Movie> checkByName(String name);

    //PATCH処理　更新処理
    @Update("UPDATE movies SET name = #{name}, releaseYear = #{releaseYear}, country = #{country} WHERE id = #{id}")
    void update(Movie movie);

    //PATCH処理、DELETE処理時に、既に存在しているIDか確認
    @Select("SELECT * FROM movies WHERE id = #{id}")
    Optional<Movie> checkById(int id);

    //DELETE処理　削除処理
    @Delete("DELETE FROM movies WHERE id = #{id}")
    void delete(int id);

}
