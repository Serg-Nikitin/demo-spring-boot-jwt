package ru.nikitin.jwt.model.exception;

public class RefreshTokenNotFoundException extends RuntimeException{
    public RefreshTokenNotFoundException(String message) {
        super(message);
    }
}
