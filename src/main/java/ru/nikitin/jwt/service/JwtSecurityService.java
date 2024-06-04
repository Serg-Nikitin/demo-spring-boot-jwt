package ru.nikitin.jwt.service;


import jakarta.security.auth.message.AuthException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.dto.TokenResponse;
import ru.nikitin.jwt.service.function.*;

@Service
@AllArgsConstructor
public class JwtSecurityService {
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final UserService userService;
    @Autowired
    private final RefreshTokenIfUserExistsFunction findUser;
    @Autowired
    private final ConvertTokenToResponse convert;
    @Autowired
    private final CreateTokenDataFunction createToken;

    @Autowired
    private final GetTokenDataFunction getToken;
    @Autowired
    private final CheckExpirationRefreshTokenFunction checkRefreshToken;

    public TokenResponse login(String login, String password) throws AuthException {
        return userService.findUserByUserName(login)
                .filter(user -> !passwordEncoder.matches(password, user.getPassword()))
                .map(createToken)
                .map(convert)
                .orElseThrow(() -> new AuthException("Exception trying to check password for user: " + login));
    }

    public TokenResponse refreshAccessToken(String refreshToken) {
        return checkRefreshToken
                .andThen(findUser)
                .andThen(getToken)
                .andThen(convert)
                .apply(refreshToken);
    }
}
