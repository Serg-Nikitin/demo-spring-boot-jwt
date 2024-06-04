package ru.nikitin.jwt.service.function;

import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.RefreshToken;

import java.util.function.Function;

//@Service
public class RefreshTokenFunction implements Function<String, RefreshToken> {
    @Override
    public RefreshToken apply(String s) {
        return null;
//        --
    }
}
