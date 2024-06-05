package ru.nikitin.jwt.model.dto;

import jakarta.validation.constraints.NotBlank;

public record TokenCredentialRequest(@NotBlank String login, @NotBlank String password) {
}
