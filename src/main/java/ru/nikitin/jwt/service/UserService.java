package ru.nikitin.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.model.dto.FullUserData;
import ru.nikitin.jwt.model.dto.Result;
import ru.nikitin.jwt.model.dto.UserData;
import ru.nikitin.jwt.model.exception.UserNotFoundException;
import ru.nikitin.jwt.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public Optional<User> findUserByUserName(String userName) {
        return repo.findUserByUsername(userName);
    }

    public Optional<User> findUserById(Long aLong) {
        return repo.findUserById(aLong);
    }

    public UserData register(UserData user) {
        User newUser = repo.save(new User(user));
        return new UserData(newUser);
    }

    public FullUserData create(FullUserData data) {
        User newUser = repo.save(new User(data));
        return new FullUserData(newUser);
    }

    public FullUserData getUserById(Long id) {
        User user = repo.findUserById(id).orElseThrow(() -> new UserNotFoundException("user not found"));
        return new FullUserData(user);
    }

    public Result deleteById(Long id) {
        int quantity = repo.delete(id);
        if (quantity > 0) {
            return new Result(true, "user deleted");
        } else {
            return new Result(false, "user not found by id");
        }
    }

    public List<UserData> getAll() {
        return repo.findAll(Sort.by(Sort.Direction.ASC, "username"))
                .stream()
                .map(UserData::new)
                .toList();
    }
}
