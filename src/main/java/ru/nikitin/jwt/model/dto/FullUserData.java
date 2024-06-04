package ru.nikitin.jwt.model.dto;

import ru.nikitin.jwt.model.Role;
import ru.nikitin.jwt.model.User;

import java.util.List;

public record FullUserData(Long id, String username, String password, List<String> roles) {
    public FullUserData(User user) {
        this(user.getId(), user.getUsername(), user.getPassword(), Role.getListStr(user.getRoles()));
    }
}
