package ru.nikitin.jwt.repository;

import ru.nikitin.jwt.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findUserByUserName(String login);

    List<User> getAll();
}
