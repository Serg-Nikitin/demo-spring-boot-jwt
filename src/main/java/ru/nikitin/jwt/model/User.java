package ru.nikitin.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
    //    private String email;
    private String password;
    private List<Role> roles = new ArrayList<>();

}
