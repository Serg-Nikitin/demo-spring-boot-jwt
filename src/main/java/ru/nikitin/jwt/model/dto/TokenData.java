package ru.nikitin.jwt.model.dto;

import io.jsonwebtoken.lang.Objects;

public record TokenData(String token, String refreshToken) {

    public boolean isEmpty() {
        return Objects.isEmpty(this.refreshToken);
    }
}
