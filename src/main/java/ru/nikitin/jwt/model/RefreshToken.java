package ru.nikitin.jwt.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    private Long id;
    private String value;
    private Long expiration;

    public boolean isEmpty() {
        return Objects.isNull(this.id);
    }
}
