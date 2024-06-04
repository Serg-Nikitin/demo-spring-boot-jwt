package ru.nikitin.jwt.model.dto;

import io.jsonwebtoken.lang.Objects;

public record TokenResponse(String token, String refreshToken) {

    public TokenResponse(TokenData data) {
        this(data.token(), data.refreshToken());
    }
    public boolean isEmpty() {
        return Objects.isEmpty(this.refreshToken);
    }
}
