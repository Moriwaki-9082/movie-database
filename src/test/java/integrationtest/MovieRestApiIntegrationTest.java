package integrationtest;

import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.spring.api.DBRider;
import com.example.moviedatabase.MovieDatabaseApplication;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;

@SpringBootTest(classes = MovieDatabaseApplication.class)
@AutoConfigureMockMvc
@DBRider
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieRestApiIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DataSet(value = "datasets/movieView.yml")
    @Transactional
    void ユーザーが全件取得できること() throws Exception {
        String response = mockMvc.perform(MockMvcRequestBuilders.get("/movies"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
        JSONAssert.assertEquals("[" +
                " {" +
                " \"id\": 1," +
                " \"name\": \"シン・ゴジラ\"," +
                " \"releaseYear\": \"2016年\"," +
                " \"country\": \"日本\"" +
                " }," +
                " {" +
                " \"id\": 2," +
                " \"name\": \"RRR\"," +
                " \"releaseYear\": \"2022年\"," +
                " \"country\": \"インド\"" +
                " }," +
                " {" +
                " \"id\": 3," +
                " \"name\": \"アイアンマン\"," +
                " \"releaseYear\": \"2008年\"," +
                " \"country\": \"アメリカ\"" +
                " }" +
                "]", response, JSONCompareMode.STRICT);
    }

}
