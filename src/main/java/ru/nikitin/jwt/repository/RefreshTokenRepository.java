package ru.nikitin.jwt.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.nikitin.jwt.model.RefreshToken;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    RefreshToken save(RefreshToken token);

    RefreshToken findTokenByValue(String value);

    RefreshToken findTokenById(Long id);
}
