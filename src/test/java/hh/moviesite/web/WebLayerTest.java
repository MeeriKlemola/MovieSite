package hh.moviesite.web;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLogin() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Username:")));
    }

    @Test
    public void testIndex() throws Exception {
        this.mockMvc.perform(get("/index"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome to my movie site!")));
    }

    @Test
    public void testMovieList() throws Exception {
        this.mockMvc.perform(get("/movielist"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Movies")));

    }

    @Test
    public void testStreamingServiceList() throws Exception {
        this.mockMvc.perform(get("/streamingservicelist"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Streaming services")));
    }
}
