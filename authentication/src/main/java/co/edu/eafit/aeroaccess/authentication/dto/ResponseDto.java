package co.edu.eafit.aeroaccess.authentication.dto;

import lombok.*;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ResponseDto {
    private Boolean status;
    private String detail;
    private Object response;
}
