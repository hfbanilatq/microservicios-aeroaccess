package co.edu.eafit.aeroaccess.authentication.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "usuario")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "nombre")
    private String name;

    @Column(name = "apellido")
    private String lastname;

    @NotNull
    @Column(unique = true, name = "nombre_usuario")
    private String username;

    @Column(unique = true, name = "correo")
    private String email;

    @NotNull
    @Column(name = "contrasenia")
    private String password;

    @Column(name = "es_activo")
    private boolean active;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol_usuario",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "rol_usuario_id"))
    @Singular
    private Set<UserRole> roles;
}
