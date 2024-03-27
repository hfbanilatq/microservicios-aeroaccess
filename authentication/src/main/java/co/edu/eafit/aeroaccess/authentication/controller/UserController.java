package co.edu.eafit.aeroaccess.authentication.controller;

import co.edu.eafit.aeroaccess.authentication.constants.HttpConstant;
import co.edu.eafit.aeroaccess.authentication.dto.ResponseDto;
import co.edu.eafit.aeroaccess.authentication.dto.UserDto;
import co.edu.eafit.aeroaccess.authentication.entities.User;
import co.edu.eafit.aeroaccess.authentication.entities.UserRole;
import co.edu.eafit.aeroaccess.authentication.enums.UserRoleEnum;
import co.edu.eafit.aeroaccess.authentication.services.UserRoleService;
import co.edu.eafit.aeroaccess.authentication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(HttpConstant.PATH_USER)
public class UserController {
    private UserService userService;
    private UserRoleService userRoleService;

    /**
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    */

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setUserRoleService(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@Valid @RequestBody UserDto userDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors())
                return new ResponseEntity<>(new ResponseDto(false, "Campo inválido", bindingResult.getAllErrors()),
                        HttpStatus.BAD_REQUEST);

            if (this.userService.existsByUserName(userDto.getUsername()))
                return new ResponseEntity<>(new ResponseDto(false, "Existe el nombre de usuario", null), HttpStatus.FORBIDDEN);

            if (this.userService.existsByEmail(userDto.getEmail()))
                return new ResponseEntity<>(new ResponseDto(false, "Existe el correo electrónico", null), HttpStatus.FORBIDDEN);

            if (userDto.getRoles().isEmpty())
                return new ResponseEntity<>(new ResponseDto(false, "No roles", null), HttpStatus.FORBIDDEN);

            Set<UserRole> roles = new HashSet<>();
            if (userDto.getRoles().contains(UserRoleEnum.ROLE_ADMIN.toString()))
                roles.add(this.userRoleService.getByRoleName(UserRoleEnum.ROLE_ADMIN).get());
            if (userDto.getRoles().contains(UserRoleEnum.ROLE_SUPERVISOR.toString()))
                roles.add(this.userRoleService.getByRoleName(UserRoleEnum.ROLE_SUPERVISOR).get());
            if (userDto.getRoles().contains(UserRoleEnum.ROLE_AGENT.toString()))
                roles.add(this.userRoleService.getByRoleName(UserRoleEnum.ROLE_AGENT).get());
            if (userDto.getRoles().contains(UserRoleEnum.ROLE_USER.toString()))
                roles.add(this.userRoleService.getByRoleName(UserRoleEnum.ROLE_USER).get());

            User user = User.builder()
                    .name(userDto.getName())
                    .lastname(userDto.getLastname())
                    .username(userDto.getUsername())
                    .email(userDto.getEmail())
                    .password(null) //passwordEncoder.encode(userDto.getPassword())
                    .active(true)
                    .roles(roles)
                    .build();

            return new ResponseEntity<>(new ResponseDto(true, "Creado con éxito", this.userService.save(user)),
                    HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error al crear el usuario", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}