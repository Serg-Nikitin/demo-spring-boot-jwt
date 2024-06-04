package ru.nikitin.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Long id;
    private String username;
    private String password;
    private List<Role> roles = new ArrayList<>();

    public boolean isEmpty() {
        return Objects.isNull(id);
    }

}
