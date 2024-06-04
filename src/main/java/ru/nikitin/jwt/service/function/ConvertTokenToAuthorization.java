package ru.nikitin.jwt.service.function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import ru.nikitin.jwt.model.Role;
import ru.nikitin.jwt.model.security.JwtPrincipal;
import ru.nikitin.jwt.service.function.utils.JwtUtils;

import java.util.function.Function;

@Service
public class ConvertTokenToAuthorization implements Function<String, Authentication> {

    @Value("${jwt.secret}")
    private String jwtSecret;


    @Override
    public Authentication apply(String token) {
        JwtPrincipal principal = JwtUtils.getPrincipal(token, jwtSecret);

        return new UsernamePasswordAuthenticationToken(
                principal,
                null,
                principal.getRoles()
                        .stream()
                        .map(Role::valueOf)
                        .toList());
    }
}
