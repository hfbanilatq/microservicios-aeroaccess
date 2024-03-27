package co.edu.eafit.aeroaccess.authentication.repositories;


import co.edu.eafit.aeroaccess.authentication.entities.UserRole;
import co.edu.eafit.aeroaccess.authentication.enums.UserRoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<UserRole, Long> {
    boolean existsByRoleName(UserRoleEnum userRoleEnum);

    Optional<UserRole> findByRoleName(UserRoleEnum userRoleEnum);
}
