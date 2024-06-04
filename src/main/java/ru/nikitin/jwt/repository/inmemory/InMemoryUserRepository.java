package ru.nikitin.jwt.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.nikitin.jwt.model.Role;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryUserRepository  {

    private List<User> repos;

    public InMemoryUserRepository() {
        this.repos = List.of(
                new User(1L, "admin", "$2y$10$ownGmACmEFGsEoRzDkFa8.AiWavQNH92daelZDMj1WIt.sNn5vSPe", List.of(Role.ADMIN, Role.USER)),
                new User(2L, "user", "pass", List.of(Role.USER))
        );
    }

    public Optional<User> findUserByUserName(String login) {
        return repos
                .stream()
                .filter(el -> el.getUsername().equals(login))
                .findFirst();
    }

    public List<User> getAll() {
        return repos;
    }

    public Optional<User> findUserById(Long aLong) {
        return repos.stream().filter(el -> el.getId().equals(aLong)).findFirst();
    }


    public User save(User user) {
        repos.add(user);
        return user;
    }

}
