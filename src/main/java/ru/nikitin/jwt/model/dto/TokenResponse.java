package ru.nikitin.jwt.model.dto;

public record TokenResponse(String token, String refreshToken) {

    public TokenResponse(TokenData data) {
        this(data.token(), data.refreshToken());
    }
}
