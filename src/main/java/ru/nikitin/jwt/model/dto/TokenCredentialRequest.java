package ru.nikitin.jwt.model.dto;

public record TokenCredentialRequest(String login, String password) {
}
