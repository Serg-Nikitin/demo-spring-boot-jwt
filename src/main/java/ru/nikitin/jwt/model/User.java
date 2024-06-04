package ru.nikitin.jwt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.nikitin.jwt.model.dto.FullUserData;
import ru.nikitin.jwt.model.dto.UserData;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String username;
    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "role"}, name = "uk_user_roles"))
    @Column(name = "role")
    private List<Role> roles = new ArrayList<>();

    public User(UserData data) {
        this(null, data.username(), data.password(), Role.getRoles(data.roles()));
    }

    public User(FullUserData data) {
        this(null, data.username(), data.password(), Role.getRoles(data.roles()));
    }

    public boolean isEmpty() {
        return Objects.isNull(id);
    }

}
