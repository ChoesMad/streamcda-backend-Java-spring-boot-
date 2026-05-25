package pl.alan.streamcda.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import pl.alan.streamcda.entity.User;
import pl.alan.streamcda.repository.UserRepository;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerIntegrationTest {
    private MockMvc mockMvc;
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserController userController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void shouldReturnListOfUsersAsJson() throws Exception {
        User user = new User();
        user.setUsername("john");
        user.setEmail("john@streamcda.pl");

        when(userRepository.findAll()).thenReturn(List.of(user));

        mockMvc.perform(get("/api/users")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("john"))
                .andExpect(jsonPath("$[0].email").value("john@streamcda.pl"));
    }
}