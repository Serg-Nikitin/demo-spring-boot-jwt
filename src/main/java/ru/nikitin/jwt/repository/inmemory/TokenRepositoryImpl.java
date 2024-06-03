package ru.nikitin.jwt.repository.inmemory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.nikitin.jwt.model.RefreshToken;
import ru.nikitin.jwt.repository.RefreshTokenRepository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class TokenRepositoryImpl implements RefreshTokenRepository {

    private Map<Long, RefreshToken> map = new HashMap<>();

    public RefreshToken save(RefreshToken token) {
        map.put(token.getId(), token);
        log.info("save token = {}", token);
        return token;
    }
}
