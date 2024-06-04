package ru.nikitin.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.model.dto.FullUserData;
import ru.nikitin.jwt.model.dto.Result;
import ru.nikitin.jwt.model.dto.UserData;
import ru.nikitin.jwt.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;

    public Optional<User> findUserByUserName(String userName) {
        return repo.findUserByUserName(userName);
    }

    public Optional<User> findUserById(Long aLong) {
        return repo.findUserById(aLong);
    }

    public UserData register(UserData user) {
        return null;
    }

    public FullUserData create(FullUserData data) {
        return null;
    }

    public FullUserData getUserById(Long id) {
        return null;
    }

    public Result deleteById(Long id) {
        return null;
    }

    public List<FullUserData> getAll() {
        return null;
    }
}
