package ru.nikitin.jwt.repository.inmemory;

import lombok.extern.slf4j.Slf4j;
import ru.nikitin.jwt.model.RefreshToken;
import ru.nikitin.jwt.repository.RefreshTokenRepository;

import java.util.HashMap;

@Slf4j
//@Repository
public class TokenRepositoryImpl implements RefreshTokenRepository {

    private HashMap<Long, RefreshToken> map;

    public RefreshToken save(RefreshToken token) {
        map.put(token.getId(), token);
        log.info("save token = {}", token);
        return token;
    }
}
