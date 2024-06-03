package ru.nikitin.jwt.service.function;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.repository.RefreshTokenRepository;
import ru.nikitin.jwt.service.UserService;

import java.util.function.Function;

@Slf4j
@Service
public class RefreshTokenIfUserExistsFunction implements Function<String, User> {

    @Autowired
    private UserService userService;

    @Autowired
    private RefreshTokenRepository refreshRepository;

    @Override
    public User apply(String refreshToken) {
        Long id = refreshRepository.findValueById(refreshToken).getId();
        return userService.findUserById(id);
    }
}
