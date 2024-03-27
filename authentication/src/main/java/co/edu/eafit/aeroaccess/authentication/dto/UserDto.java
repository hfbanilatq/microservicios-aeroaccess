package co.edu.eafit.aeroaccess.authentication.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Component
public class UserDto {
    private Long id;
    private String name;
    private String lastname;
    @NotBlank
    private String username;
    private String email;
    @NotBlank
    private String password;
    private Set<String> roles = new HashSet<>();
}
