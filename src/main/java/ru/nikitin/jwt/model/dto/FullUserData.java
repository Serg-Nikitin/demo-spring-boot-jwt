package ru.nikitin.jwt.model.dto;

import java.util.List;

public record FullUserData(Long id, String username, String password, List<String> roles) {
}
