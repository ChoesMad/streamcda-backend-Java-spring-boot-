package pl.alan.streamcda.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import pl.alan.streamcda.entity.User;
import pl.alan.streamcda.repository.UserRepository;
import java.security.Principal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class SubscriptionControllerTest {
    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private SubscriptionController subscriptionController;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldBuySubscriptionSuccessfully() {
        Principal mockPrincipal = mock(Principal.class);
        when(mockPrincipal.getName()).thenReturn("test@user.com");

        User mockUser = new User();
        mockUser.setEmail("test@user.com");
        mockUser.setPremium(false);

        when(userRepository.findByEmail("test@user.com")).thenReturn(Optional.of(mockUser));

        ResponseEntity<?> response = subscriptionController.buySubscription(mockPrincipal);

        assertEquals(200, response.getStatusCode().value());
        assertEquals("Subscription purchased successfully", response.getBody());
        assertTrue(mockUser.isPremium());

        verify(userRepository, times(1)).save(mockUser);
    }
}