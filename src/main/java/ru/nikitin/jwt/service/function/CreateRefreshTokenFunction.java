package ru.nikitin.jwt.service.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.RefreshToken;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.repository.RefreshTokenRepository;

import java.time.Duration;
import java.util.Date;
import java.util.UUID;
import java.util.function.Function;

@Service
public class CreateRefreshTokenFunction implements Function<User, RefreshToken> {
    @Value("${jwt.refreshTokenExpiration}")
    private Duration duration;
    @Autowired
    private RefreshTokenRepository repository;

    @Override
    public RefreshToken apply(User user) {
        String value = UUID.randomUUID().toString();
        Long id = user.getId();
        Date date = new Date(new Date().getTime() + duration.toMillis());
        return repository.save(new RefreshToken(id, value, date.getTime()));
    }
}
