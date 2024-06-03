package ru.nikitin.jwt.repository;


import org.springframework.stereotype.Repository;
import ru.nikitin.jwt.model.RefreshToken;

@Repository
public interface RefreshTokenRepository {

    RefreshToken save(RefreshToken token);
}
