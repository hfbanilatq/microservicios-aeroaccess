package co.edu.eafit.aeroaccess.authentication.dto;

import lombok.*;

import jakarta.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginUserDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
