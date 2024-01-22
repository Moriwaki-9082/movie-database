package com.example.moviedatabase.mapper;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.example.moviedatabase.entity.MovieView;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DBRider
@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MovieMapperTest {

    @Autowired
    MovieMapper movieMapper;

    @Test
    @DataSet(value = "datasets/movieView.yml")
    @Transactional
    void すべてのデータが取得できること() {
        List<MovieView> movies = movieMapper.findAll();
        assertThat(movies)
                .hasSize(3)
                .contains(
                        new MovieView(1, "シン・ゴジラ", "2016年", "日本"),
                        new MovieView(2, "RRR", "2022年", "インド"),
                        new MovieView(3, "アイアンマン", "2008年", "アメリカ")
                );
    }

    @Test
    @DataSet(value = "datasets/movieView.yml")
    @Transactional
    public void 存在するデータのIDを指定したときに正常にデータが返されること() {
        Optional<MovieView> actual = movieMapper.findById(1);
        assertThat(actual).contains(new MovieView(1, "シン・ゴジラ", "2016年", "日本"));
    }

}
