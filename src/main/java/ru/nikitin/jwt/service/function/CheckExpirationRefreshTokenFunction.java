package ru.nikitin.jwt.service.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.RefreshToken;
import ru.nikitin.jwt.repository.RefreshTokenRepository;

import java.util.Date;
import java.util.function.Function;

@Service
public class CheckExpirationRefreshTokenFunction implements Function<String, RefreshToken> {

    @Autowired
    private RefreshTokenRepository refreshRepository;

    @Override
    public RefreshToken apply(String token) {
        RefreshToken refreshToken = refreshRepository.findTokenByValue(token);
        Date expirationDate = new Date(refreshToken.getExpiration());

        if (new Date().before(expirationDate)) {
            return refreshToken;
        } else {
            return new RefreshToken();
        }
    }
}
