package ru.nikitin.jwt.service.function.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.lang.Objects;
import io.jsonwebtoken.security.Keys;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.model.security.JwtPrincipal;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class JwtUtils {

    private static final String ROLE_CLAIM = "role";

    private static final String ID_CLAIM = "id";

    public static JwtPrincipal getPrincipal(String token, String jwtSecret) {
        Claims claims = getClaims(token, jwtSecret);
        String subject = claims.getSubject();
        Long id = claims.get(ID_CLAIM, Long.class);
        List<String> roles = (List<String>) claims.get(ROLE_CLAIM);

        return new JwtPrincipal(id, subject, roles);
    }

    public static Claims getClaims(String token, String jwtSecret) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey(jwtSecret))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public static String generateToken(User user, String jwtSecret, Duration tokenExpiration) {
        return Jwts.builder()
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(new Date(new Date().getTime() + tokenExpiration.toMillis()))
                .claim(ID_CLAIM, user.getId())
                .claim(ROLE_CLAIM, user.getRoles())
                .signWith(getSigningKey(jwtSecret))
                .compact();
    }

    private static SecretKey getSigningKey(String jwtSecret) {
        byte[] keyBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }


    public static boolean checkAccessToken(String token, String jwtSecret) {
        if (Objects.isEmpty(token)) {
            return false;
        }
        Claims claims = getClaims(token, jwtSecret);
        long expiredTime = claims.getExpiration().getTime();
        return new Date().getTime() > expiredTime;
    }
}
