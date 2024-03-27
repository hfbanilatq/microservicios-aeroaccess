package co.edu.eafit.aeroaccess.authentication.services;

import co.edu.eafit.aeroaccess.authentication.entities.UserRole;
import co.edu.eafit.aeroaccess.authentication.enums.UserRoleEnum;
import co.edu.eafit.aeroaccess.authentication.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class UserRoleService {
    private RoleRepository roleRepository;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Optional<UserRole> getByRoleName(UserRoleEnum userRoleEnum) {
        return this.roleRepository.findByRoleName(userRoleEnum);
    }

    public void save(UserRole userRole) {
        this.roleRepository.save(userRole);
    }
}
