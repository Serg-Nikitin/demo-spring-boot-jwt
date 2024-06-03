package ru.nikitin.jwt.service.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.RefreshToken;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.repository.RefreshTokenRepository;

import java.util.UUID;
import java.util.function.Function;

@Service
public class CreateRefreshTokenFunction implements Function<User, RefreshToken> {

    private RefreshTokenRepository repository;

    @Override
    public RefreshToken apply(User user) {
        String value = UUID.randomUUID().toString();
        Long id = user.getId();
        return repository.save(new RefreshToken(id, value));
    }
}
