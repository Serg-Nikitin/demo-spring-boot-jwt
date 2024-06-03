package ru.nikitin.jwt.controller;

import jakarta.security.auth.message.AuthException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nikitin.jwt.model.dto.RefreshTokenRequest;
import ru.nikitin.jwt.model.dto.TokenCredentialRequest;
import ru.nikitin.jwt.model.dto.TokenResponse;
import ru.nikitin.jwt.service.JwtSecurityService;


@RestController
@RequestMapping("/token")
public class TokenController {

    private JwtSecurityService service;

    @GetMapping("/login")
    public TokenResponse login(@RequestBody TokenCredentialRequest credential) throws AuthException {
        return service.login(credential.login(), credential.password());
    }

    @GetMapping("/refresh")
    public TokenResponse refresh(@RequestBody RefreshTokenRequest request) {
        return service.refreshAccessToken(request.refreshToken());
    }

}