package ru.nikitin.jwt.model.security;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.security.Principal;
import java.util.List;

@Data
@AllArgsConstructor
public class JwtPrincipal implements Principal {
    private final Long id;
    private final String name;
    private final List<String> roles;

    @Override
    public String getName() {
        return name;
    }
}
