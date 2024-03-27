package co.edu.eafit.aeroaccess.authentication.repositories;

import co.edu.eafit.aeroaccess.authentication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUsername(String userName);

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);
}
