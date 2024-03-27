package co.edu.eafit.aeroaccess.authentication.jwt;

import co.edu.eafit.aeroaccess.authentication.entities.User;
import co.edu.eafit.aeroaccess.authentication.services.UserService;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private UserService userService;

    @Value("${jwt.secret}")
    private String secret;

    private String errorMessage;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String generate(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName().name())).collect(Collectors.toList());
        List<String> roles = authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        User optionalUser = this.userService.getByUserName(user.getUsername()).orElse(null);
        assert optionalUser != null;
        return Jwts.builder()
                .setSubject(optionalUser.getUsername())
                .claim("id", optionalUser.getId())
                .claim("name", optionalUser.getName())
                .claim("lastname", optionalUser.getLastname())
                .claim("username", optionalUser.getUsername())
                .claim("email", optionalUser.getEmail())
                .claim("roles", roles)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + 3600000)) //new Date(new Date().getTime() + expiration * 1000L)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }


    public boolean validate(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException e) {
            this.errorMessage = "Token mal formado";
            logger.error(errorMessage);
        } catch (UnsupportedJwtException e) {
            this.errorMessage = "Token no soportado";
            logger.error(errorMessage);
        } catch (ExpiredJwtException e) {
            this.errorMessage = "El token ya ha expirado";
            logger.error(errorMessage);
        } catch (IllegalArgumentException e) {
            this.errorMessage = "Valor ilegal en el token o se encuentra vacio";
            logger.error(errorMessage);
        } catch (SignatureException e) {
            this.errorMessage = "Error en la firma";
            logger.error(errorMessage);
        }
        return false;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }

    public String getUserNameFromToken(String token) {
        try {
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
        } catch (Exception e) {
            return "bad token";
        }
    }
}
