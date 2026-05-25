package pl.alan.streamcda.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import pl.alan.streamcda.entity.User;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtils {
    private final String secret_string = "U8DI2HLi92hl01y094hlk14u1o9piXYZ";
    private final SecretKey key = Keys.hmacShaKeyFor(secret_string.getBytes(StandardCharsets.UTF_8));
    public String generateToken(User user){
        return Jwts.builder()
                .subject(user.getEmail())
                .claim("role", user.getRole())
                .claim("username", user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(key)
                .compact();
    }
    public String getEmailFromtoken(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
    public boolean validateToken(String token){
        try {
            Jwts.parser().verifyWith(key).build().parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
