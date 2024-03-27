package co.edu.eafit.aeroaccess.authentication.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TokenJwtDto {
    private String token;
}
