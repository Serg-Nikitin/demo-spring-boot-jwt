package ru.nikitin.jwt.controller;

import jakarta.security.auth.message.AuthException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.nikitin.jwt.controller.base.BaseController;
import ru.nikitin.jwt.model.dto.RefreshTokenRequest;
import ru.nikitin.jwt.model.dto.TokenCredentialRequest;
import ru.nikitin.jwt.model.dto.TokenResponse;
import ru.nikitin.jwt.model.exception.JwtAuthException;
import ru.nikitin.jwt.service.JwtSecurityService;


@RestController
@RequestMapping(path = "/public/token", produces = MediaType.APPLICATION_JSON_VALUE)
public class TokenController extends BaseController {

    @Autowired
    private JwtSecurityService service;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody TokenCredentialRequest credential) throws AuthException {
        return service.login(credential.login(), credential.password());
    }

    @GetMapping("/refresh")
    public TokenResponse refresh(@RequestBody RefreshTokenRequest request) {
        TokenResponse tokenResponse = service.refreshAccessToken(request.refreshToken());
        if (tokenResponse.isEmpty()) {
            throw new JwtAuthException("the token is expired");
        } else {
            return tokenResponse;
        }
    }
}
