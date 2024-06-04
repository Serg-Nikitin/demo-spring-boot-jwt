package ru.nikitin.jwt.controller;

import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.nikitin.jwt.model.dto.RefreshTokenRequest;
import ru.nikitin.jwt.model.dto.TokenCredentialRequest;
import ru.nikitin.jwt.model.dto.TokenResponse;
import ru.nikitin.jwt.service.JwtSecurityService;


@RestController
@RequestMapping("/public/token")
public class TokenController extends BaseController {

    @Autowired
    private JwtSecurityService service;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody TokenCredentialRequest credential) throws AuthException {
        return service.login(credential.login(), credential.password());
    }

    @GetMapping("/refresh")
    public TokenResponse refresh(@RequestBody RefreshTokenRequest request) {
        return service.refreshAccessToken(request.refreshToken());
    }
}
