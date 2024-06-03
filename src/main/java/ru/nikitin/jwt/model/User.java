package ru.nikitin.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
//    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();

}
