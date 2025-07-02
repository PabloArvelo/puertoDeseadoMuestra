package com.puertodeseado.entidades.seguridad;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Usuario  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String userName; // el usuario es el email

    private String pilaName;

    private String password;

    @Column(name = "is_enable")
    private boolean isEnable;

    @Column(name = "account_No_Expired")
    private boolean accountNoExpired;

    @Column(name = "account_No_Locked")
    private boolean accountNoLocked;

    @Column(name = "credential_No_Expired")
    private boolean credentialNoExpired;

    @ManyToOne(targetEntity = Rol.class)
    private Rol rol;

    // Nuevos atributos para recuperación de contraseña
    private String resetToken; // Para el token de recuperación
    private Date resetTokenExpiry; // Expiración del token

}
