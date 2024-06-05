package ru.nikitin.jwt.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Data
@Entity
@Table(name = "tokens")
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {
    @Id
    private Long id;
    @Column(name = "value")
    private String value;
    @Column(name = "expiration")
    private Long expiration;

    public boolean isEmpty() {
        return Objects.isNull(this.id);
    }
}