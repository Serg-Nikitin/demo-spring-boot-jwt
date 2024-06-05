package ru.nikitin.jwt.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import ru.nikitin.jwt.model.Role;
import ru.nikitin.jwt.model.User;

import java.util.List;

public record UserData(@NotBlank String username, @NotBlank String password, @NotEmpty List<String> roles) {
    public UserData(User user) {
        this(user.getUsername(), user.getPassword(), Role.getListStr(user.getRoles()));
    }
}
