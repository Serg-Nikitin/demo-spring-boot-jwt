package ru.nikitin.jwt.service.function;

import ru.nikitin.jwt.model.User;

import java.util.function.Function;

public class RefreshTokenIfUserExistsFunction implements Function<String, User> {
    @Override
    public User apply(String s) {
        return null;
    }
}
