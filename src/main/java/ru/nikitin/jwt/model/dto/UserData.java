package ru.nikitin.jwt.model.dto;

import java.util.List;

public record UserData(String username, String password, List<String> roles) {
}
