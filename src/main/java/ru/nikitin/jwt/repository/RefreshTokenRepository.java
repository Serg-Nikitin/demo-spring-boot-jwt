package ru.nikitin.jwt.repository;


import ru.nikitin.jwt.model.RefreshToken;

//@Repository
public interface RefreshTokenRepository {

    RefreshToken save(RefreshToken token);

    RefreshToken findTokenByValue(String value);

    RefreshToken findTokenById(Long id);
}
