package ru.nikitin.jwt.service.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.model.dto.TokenData;

import java.util.function.Function;

@Service
public class CreateTokenDataFunction implements Function<User, TokenData> {

    @Autowired
    private CreateRefreshTokenFunction refreshToken;

    @Autowired
    private CreateAccessTokenFunction accessToken;


    @Override
    public TokenData apply(User user) {
        return new TokenData(accessToken.apply(user), refreshToken.apply(user).getValue());
    }
}
