package ru.nikitin.jwt.service.function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.service.function.utils.JwtUtils;

import java.time.Duration;
import java.util.function.Function;

@Service
public class CreateAccessTokenFunction implements Function<User, String> {

    @Value("${jwt.secret}")
    private String JWT_SECRET;

    @Value("${jwt.tokenExpiration}")
    private Duration tokenExpiration;


    @Override
    public String apply(User user) {
        return JwtUtils.generateToken(user, JWT_SECRET, tokenExpiration);
    }
}
