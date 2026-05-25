package pl.alan.streamcda.dto;

import lombok.Data;

@Data
public class LoginResponse {
    private String token;
    private String role;
    private boolean isPremium;
    private String username;
}
