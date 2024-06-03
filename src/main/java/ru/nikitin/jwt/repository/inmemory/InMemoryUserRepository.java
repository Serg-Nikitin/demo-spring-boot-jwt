package ru.nikitin.jwt.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.nikitin.jwt.model.Role;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.model.exception.UserNotFoundException;
import ru.nikitin.jwt.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private List<User> repos;

    public InMemoryUserRepository() {
        this.repos = List.of(
                new User(1L, "admin", "secret", Set.of(Role.ADMIN, Role.USER)),
                new User(2L, "user", "pass", Set.of(Role.USER))
        );
    }

    public Optional<User> findUserByUserName(String login) {
        return repos
                .stream()
                .filter(el -> el.getUsername().equals(login))
                .findFirst();
    }

    @Override
    public List<User> getAll() {
        return repos;
    }

    @Override
    public User findUserById(Long aLong) {
        return repos.stream()
                .filter(el -> el.getId().equals(aLong))
                .findFirst().orElseThrow(() -> new UserNotFoundException("User not found by id"));
    }


    @Override
    public User save(User user) {
        repos.add(user);
        return user;
    }

}
