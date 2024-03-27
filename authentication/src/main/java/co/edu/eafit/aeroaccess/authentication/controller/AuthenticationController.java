package co.edu.eafit.aeroaccess.authentication.controller;

import co.edu.eafit.aeroaccess.authentication.constants.HttpConstant;
import co.edu.eafit.aeroaccess.authentication.dto.LoginUserDto;
import co.edu.eafit.aeroaccess.authentication.dto.ResponseDto;
import co.edu.eafit.aeroaccess.authentication.dto.TokenJwtDto;
import co.edu.eafit.aeroaccess.authentication.entities.User;
import co.edu.eafit.aeroaccess.authentication.jwt.JwtProvider;
import co.edu.eafit.aeroaccess.authentication.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping(HttpConstant.PATH_AUTHENTICATION)
@CrossOrigin("*")
public class AuthenticationController {
    private UserService userService;
    private JwtProvider jwtProvider;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setJwtProvider(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }



    @Autowired
    private PasswordEncoder passwordEncoder;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@Valid @RequestBody LoginUserDto loginUserDto, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors())
                return new ResponseEntity<>(new ResponseDto(false, "Campo inválido", bindingResult.getAllErrors()),
                        HttpStatus.BAD_REQUEST);

            if (!this.userService.existsByUserName(loginUserDto.getUsername()))
                return new ResponseEntity<>(new ResponseDto(false, "No existe el usuario", null),
                        HttpStatus.FORBIDDEN);

            User user = this.userService.getByUserName(loginUserDto.getUsername()).orElseThrow();

            if (user.getRoles().isEmpty())
                return new ResponseEntity<>(new ResponseDto(false, "No roles", null),
                        HttpStatus.FORBIDDEN);

            if (!passwordEncoder.matches(loginUserDto.getPassword(), user.getPassword()))
                return new ResponseEntity<>(new ResponseDto(false, "Contraseña incorrecta", null),
                        HttpStatus.FORBIDDEN);

            if (!user.isActive())
                return new ResponseEntity<>(new ResponseDto(false, "Acceso denegado", null),
                        HttpStatus.FORBIDDEN);

            TokenJwtDto tokenJwtDto = TokenJwtDto.builder()
                    .token(jwtProvider.generate(user))
                    .build();

            return new ResponseEntity<>(new ResponseDto(true, "Inicio de sesión correcto", tokenJwtDto),
                    HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ResponseDto(false, "Error al iniciar la sesión", null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping(value = "/validate")
    public ResponseEntity<TokenJwtDto> validate(@RequestParam String token) {
        if (!this.jwtProvider.validate(token))
            return ResponseEntity.badRequest().build();
        String username = jwtProvider.getUserNameFromToken(token);
        if (!this.userService.getByUserName(username).isPresent())
            return ResponseEntity.badRequest().build();

        TokenJwtDto tokenJwtDto = new TokenJwtDto(token);
        return ResponseEntity.ok(tokenJwtDto);
    }
}
