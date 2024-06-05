package ru.nikitin.jwt.service.consumer;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.service.function.utils.JwtUtils;

import java.util.function.Predicate;

@Service
public class CheckIfAccessTokenExpire implements Predicate<String> {

    @Value("${jwt.secret}")
    private String jwtSecret;
    @Override
    public boolean test(String token) {
        return JwtUtils.checkAccessToken(token, jwtSecret);
    }
}
