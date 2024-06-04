package ru.nikitin.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.nikitin.jwt.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Transactional
    User save(User user);

    @Transactional
    @Modifying
    @Query("DELETE FROM #{#entityName} u WHERE u.id=:id")
    int delete(long id);

    Optional<User> findUserByUsername(String login);

    Optional<User> findUserById(Long aLong);
}
