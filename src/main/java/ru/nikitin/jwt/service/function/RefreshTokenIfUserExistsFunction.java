package ru.nikitin.jwt.service.function;

import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.User;

import java.util.function.Function;

@Service
public class RefreshTokenIfUserExistsFunction implements Function<String, User> {
    @Override
    public User apply(String s) {
        return null;
    }
}
