package ru.nikitin.jwt.service.function;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.User;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.function.Function;

@Service
public class CreateAccessTokenFunction implements Function<User, String> {

    private static final String ROLE_CLAIM = "role";

    private static final String ID_CLAIM = "id";

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.tokenExpiration}")
    private Duration tokenExpiration;


    @Override
    public String apply(User user) {
        return generateToken(user);
    }

    private String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + tokenExpiration.toMillis()))
                .claim(ID_CLAIM, user.getId())
                .claim(ROLE_CLAIM, user.getRoles())
                .signWith(getSigningKey())
                .compact();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = this.jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
