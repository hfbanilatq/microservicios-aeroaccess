package co.edu.eafit.aeroaccess.authentication.services;


import co.edu.eafit.aeroaccess.authentication.entities.User;
import co.edu.eafit.aeroaccess.authentication.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean existsByUserName(String userName) {
        return this.userRepository.existsByUsername(userName);
    }

    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email);
    }

    public Optional<User> getByUserName(String userName) {
        return this.userRepository.findByUsername(userName);
    }

    public User save(User user) {
        return this.userRepository.save(user);
    }
}
