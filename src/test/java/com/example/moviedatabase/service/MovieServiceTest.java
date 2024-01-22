package com.example.moviedatabase.service;

import com.example.moviedatabase.entity.Movie;
import com.example.moviedatabase.entity.MovieView;
import com.example.moviedatabase.exception.MovieAlreadyExistsException;
import com.example.moviedatabase.exception.MovieNotFoundException;
import com.example.moviedatabase.mapper.MovieMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    @InjectMocks
    MovieService movieService;

    @Mock
    MovieMapper movieMapper;

    @Test
    public void 指定したIDデータの検索表示テスト() {
        doReturn(Optional.of(new MovieView(1, "シン・ゴジラ", "2016年", "日本"))).when(movieMapper).findById(1);
        MovieView actual = movieService.findById(1);
        assertThat(actual).isEqualTo(new MovieView(1, "シン・ゴジラ", "2016年", "日本"));
        verify(movieMapper, times(1)).findById(1);
    }

    @Test
    public void 指定したIDが存在しない場合の例外処理テスト() throws MovieNotFoundException {
        doReturn(Optional.empty()).when(movieMapper).findById(100);
        assertThrows(MovieNotFoundException.class, () -> {
            movieService.findById(100);
        });
        verify(movieMapper, times(1)).findById(100);
    }

    @Test
    void 全データの表示テスト() {
        List<MovieView> fishList = Arrays.asList(
                new MovieView(1, "シン・ゴジラ", "2016年", "日本"),
                new MovieView(2, "RRR", "2022年", "インド"),
                new MovieView(3, "アイアンマン", "2008年", "アメリカ"));
        doReturn(fishList).when(movieMapper).findAll();
        List<MovieView> movies = movieService.findAll();
        assertThat(movies)
                .hasSize(3)
                .contains(
                        new MovieView(1, "シン・ゴジラ", "2016年", "日本"),
                        new MovieView(2, "RRR", "2022年", "インド"),
                        new MovieView(3, "アイアンマン", "2008年", "アメリカ"));
        verify(movieMapper, times(1)).findAll();
    }

    @Test
    public void データの新規登録テスト() {
        Movie movie = new Movie(null, "レッドクリフ", 2008, "中国");
        doNothing().when(movieMapper).insert(movie);
        Movie actual = movieService.insert(movie);
        assertThat(actual).isEqualTo(new Movie(null, "レッドクリフ", 2008, "中国"));
        verify(movieMapper, times(1)).insert(movie);
    }

    @Test
    public void 新規登録しようとしたデータ名が既に存在した場合の例外処理テスト() throws MovieAlreadyExistsException {
        Movie movie = new Movie(4, "レッドクリフ", 2008, "中国");
        doReturn(Optional.of(movie)).when(movieMapper).checkByName("レッドクリフ");
        assertThrows(MovieAlreadyExistsException.class, () -> {
            movieService.insert(movie);
        });
        verify(movieMapper, times(1)).checkByName("レッドクリフ");
    }

    @Test
    public void データの更新処理テスト() {
        Movie movie = new Movie(1, "ゴジラ・マイナスワン", 2023, "日本");
        doNothing().when(movieMapper).update(movie);
        doReturn(Optional.of(movie)).when(movieMapper).checkById(1);
        Movie actual = movieService.update(movie);
        assertThat(actual).isEqualTo(new Movie(1, "ゴジラ・マイナスワン", 2023, "日本"));
        verify(movieMapper, times(1)).update(movie);
    }

    @Test
    public void 存在しないデータを更新しようした際の例外処理テスト() throws MovieNotFoundException {
        Movie movie = new Movie(999, "ゴジラ・マイナスワン", 2023, "日本");
        doReturn(Optional.empty()).when(movieMapper).checkById(999);
        assertThrows(MovieNotFoundException.class, () -> {
            movieService.update(movie);
        });
        verify(movieMapper, times(1)).checkById(999);
    }

    @Test
    public void データの削除処理テスト() {
        Movie movie = new Movie(1, "ゴジラ・マイナスワン", 2023, "日本");
        doReturn(Optional.of(movie)).when(movieMapper).checkById(1);
        doNothing().when(movieMapper).delete(1);
        int actual = movieService.delete(1);
        assertThat(actual).isEqualTo(1);
        verify(movieMapper, times(1)).delete(1);
    }

    @Test
    public void 存在しないデータを削除しようした際の例外処理テスト() throws MovieNotFoundException {
        doReturn(Optional.empty()).when(movieMapper).checkById(999);
        assertThrows(MovieNotFoundException.class, () -> {
            movieService.delete(999);
        });
        verify(movieMapper, times(1)).checkById(999);
    }

}
