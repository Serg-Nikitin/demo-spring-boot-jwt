package ru.nikitin.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.User;
import ru.nikitin.jwt.repository.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repo;
    public Optional<User> findUserByUserName(String userName) {
        return repo.findUserByUserName(userName);
    }

    public User findUserById(Long aLong) {
       return repo.findUserById(aLong);
    }
}
