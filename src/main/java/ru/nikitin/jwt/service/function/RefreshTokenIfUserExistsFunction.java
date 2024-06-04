package ru.nikitin.jwt.service.function;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.RefreshToken;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.service.UserService;

import java.util.Optional;
import java.util.function.Function;

@Slf4j
@Service
public class RefreshTokenIfUserExistsFunction implements Function<RefreshToken, User> {

    @Autowired
    private UserService userService;

    @Override
    public User apply(RefreshToken refreshToken) {
        if (refreshToken.isEmpty()) {
            return new User();
        } else {
            Optional<User> userById = userService.findUserById(refreshToken.getId());
            return userById.orElseGet(User::new);
        }
    }
}
