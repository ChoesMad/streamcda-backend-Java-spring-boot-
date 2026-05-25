package pl.alan.streamcda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.alan.streamcda.dto.LoginRequest;
import pl.alan.streamcda.dto.LoginResponse;
import pl.alan.streamcda.dto.RegisterRequest;
import pl.alan.streamcda.entity.User;
import pl.alan.streamcda.repository.UserRepository;
import org.springframework.web.bind.annotation.*;
import pl.alan.streamcda.security.JwtUtils;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        if(userRepository.findByEmail(registerRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already taken");
        }
        User newUser = new User();
        newUser.setUsername(registerRequest.getUsername());
        newUser.setEmail(registerRequest.getEmail());
        newUser.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        newUser.setRole("ROLE_USER");
        newUser.setPremium(false);

        userRepository.save(newUser);
        return ResponseEntity.ok("Registered successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOpt = userRepository.findByEmail(loginRequest.getEmail());
        if(userOpt.isEmpty() || !passwordEncoder.matches(loginRequest.getPassword(), userOpt.get().getPassword())){
            return ResponseEntity.status(401).body("Incorrect email or password");
        }
        User user = userOpt.get();
        String token = jwtUtils.generateToken(user);

        LoginResponse response = new LoginResponse();
        response.setToken(token);
        response.setRole(user.getRole());
        response.setUsername(user.getUsername());
        response.setPremium(user.isPremium());
        return ResponseEntity.ok(response);
    }
}
