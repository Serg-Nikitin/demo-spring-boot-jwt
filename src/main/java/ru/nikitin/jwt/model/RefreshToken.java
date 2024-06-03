package ru.nikitin.jwt.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    private Long id;
    private String value;
}
