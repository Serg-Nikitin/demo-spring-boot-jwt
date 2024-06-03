package ru.nikitin.jwt.service.function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.RefreshToken;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.repository.RefreshTokenRepository;

import java.util.function.Function;
@Service
public class GetRefreshTokenFunction implements Function<User, RefreshToken> {

    @Autowired
    private RefreshTokenRepository repository;

    @Override
    public RefreshToken apply(User user) {
        return repository.findTokenById(user.getId());
    }
}
