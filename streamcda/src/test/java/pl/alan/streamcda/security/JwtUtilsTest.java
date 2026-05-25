package pl.alan.streamcda.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.alan.streamcda.entity.User;
import static org.junit.jupiter.api.Assertions.*;

class JwtUtilsTest {
    private JwtUtils jwtUtils;
    private User testUser;

    @BeforeEach
    void setUp() {
        jwtUtils = new JwtUtils();
        testUser = new User();
        testUser.setEmail("CTO@streamcda.pl");
        testUser.setUsername("Johnny");
        testUser.setRole("ROLE_USER");
    }

    @Test
    void shouldGenerateAndExtractDataFromToken() {
        String token = jwtUtils.generateToken(testUser);
        assertNotNull(token);
        String extractedEmail = jwtUtils.getEmailFromtoken(token);
        assertEquals("CTO@streamcda.pl", extractedEmail);
        boolean isValid = jwtUtils.validateToken(token);
        assertTrue(isValid);
    }

    @Test
    void shouldReturnFalseForInvalidOrTamperedToken() {
        String token = jwtUtils.generateToken(testUser);
        String tamperedToken = token + "error";

        boolean isValid = jwtUtils.validateToken(tamperedToken);
        assertFalse(isValid);
    }
}