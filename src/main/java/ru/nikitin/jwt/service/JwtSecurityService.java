package ru.nikitin.jwt.service;


import jakarta.security.auth.message.AuthException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.dto.TokenResponse;
import ru.nikitin.jwt.service.function.ConvertTokenToResponse;
import ru.nikitin.jwt.service.function.CreateTokenDataFunction;
import ru.nikitin.jwt.service.function.RefreshTokenIfUserExistsFunction;

@Service
@AllArgsConstructor
public class JwtSecurityService {
    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    private final RefreshTokenIfUserExistsFunction check;
    private final ConvertTokenToResponse convert;
    private final CreateTokenDataFunction createToken;

    public TokenResponse login(String login, String password) throws AuthException {
        return userService.findUserByUserName(login)
                .filter(user -> !passwordEncoder.matches(password, user.getPassword()))
                .map(createToken)
                .map(convert)
                .orElseThrow(() -> new AuthException("Exception trying to check password for user: " + login));
    }

    public TokenResponse refreshAccessToken(String refreshToken) {
        return check
                .andThen(createToken)
                .andThen(convert)
                .apply(refreshToken);
    }
}
