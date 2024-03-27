package co.edu.eafit.aeroaccess.authentication.entities;

import co.edu.eafit.aeroaccess.authentication.enums.UserRoleEnum;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rol_usuario")
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "nombre_rol")
    private UserRoleEnum roleName;

    public UserRole(@NotNull UserRoleEnum userRoleEnum) {
        this.roleName = userRoleEnum;
    }
}
