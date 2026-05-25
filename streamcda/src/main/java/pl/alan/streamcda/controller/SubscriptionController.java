package pl.alan.streamcda.controller;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import pl.alan.streamcda.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.alan.streamcda.repository.UserRepository;
import java.security.Principal;
import java.util.Optional;

@Data
@RestController
@RequestMapping("/api/subscriptions")
@CrossOrigin(origins = "*")
public class SubscriptionController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/buy")
    public ResponseEntity<?> buySubscription(Principal principal) {
        Optional<User> userOpt = userRepository.findByEmail(principal.getName());
        if(userOpt.isPresent()) {
            User user = userOpt.get();
            user.setPremium(true);
            userRepository.save(user);
            return ResponseEntity.ok("Subscription purchased successfully");
        }
        return ResponseEntity.badRequest().body("User not found");
    }

}
