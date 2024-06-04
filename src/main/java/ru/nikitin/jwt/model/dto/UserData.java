package ru.nikitin.jwt.model.dto;

import ru.nikitin.jwt.model.Role;
import ru.nikitin.jwt.model.User;

import java.util.List;

public record UserData(String username, String password, List<String> roles) {
    public UserData(User user) {
        this(user.getUsername(), user.getPassword(), Role.getListStr(user.getRoles()));
    }
}
