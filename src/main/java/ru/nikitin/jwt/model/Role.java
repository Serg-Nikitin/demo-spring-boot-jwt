package ru.nikitin.jwt.model;

import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public enum Role implements GrantedAuthority {
    USER, ADMIN;

    @Override
    public String getAuthority() {
        return "ROLE_" + name();
    }

    public static List<Role> getRoles(List<String> rolesStr) {
        return rolesStr.stream()
                .map(Role::valueOf).toList();
    }

    public static List<String> getListStr(List<Role> roles) {
        return roles.stream().map(Role::name).toList();
    }
}
