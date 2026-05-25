package pl.alan.streamcda.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.alan.streamcda.entity.Movie;
import pl.alan.streamcda.repository.MovieRepository;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class MovieControllerIntegrationTest {
    private MockMvc mockMvc;
    @Mock
    private MovieRepository movieRepository;
    @InjectMocks
    private MovieController movieController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @Test
    void shouldReturnListOfMoviesSuccessfully() throws Exception {
        Movie movie = new Movie();
        movie.setTitle("Inception");
        movie.setGenre("Sci-Fi");

        when(movieRepository.findAll()).thenReturn(List.of(movie));

        mockMvc.perform(get("/api/movies")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Inception"))
                .andExpect(jsonPath("$[0].genre").value("Sci-Fi"));
    }
}