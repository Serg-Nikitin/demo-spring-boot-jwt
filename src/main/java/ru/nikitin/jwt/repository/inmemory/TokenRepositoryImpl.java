package ru.nikitin.jwt.repository.inmemory;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.nikitin.jwt.model.RefreshToken;
import ru.nikitin.jwt.model.exception.RefreshTokenNotFoundException;
import ru.nikitin.jwt.repository.RefreshTokenRepository;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Repository
public class TokenRepositoryImpl implements RefreshTokenRepository {

    private static Map<Long, RefreshToken> map = new HashMap<>();

    static {
        map.put(1L, new RefreshToken(1L, "236f42bb-e523-4187-bb79-815fe135be6c", 132321321321L));
    }


    public RefreshToken save(RefreshToken token) {
        map.put(token.getId(), token);
        log.info("save token = {}", token);
        return token;
    }

    @Override
    public RefreshToken findTokenByValue(String value) {
        return map.values()
                .stream()
                .filter(el -> el.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new RefreshTokenNotFoundException("Refresh token not found"));
    }

    @Override
    public RefreshToken findTokenById(Long id) {
        return map.get(id);
    }
}
