package ru.nikitin.jwt.service.function;

import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.dto.TokenData;
import ru.nikitin.jwt.model.dto.TokenResponse;

import java.util.function.Function;

@Service
public class ConvertTokenToResponse implements Function<TokenData, TokenResponse> {
    @Override
    public TokenResponse apply(TokenData tokenData) {
        return new TokenResponse(tokenData);
    }
}
