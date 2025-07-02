package com.puertodeseado.EntidadesDTO.seguridad;

import com.puertodeseado.entidades.seguridad.Rol;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {

    @NotEmpty(message = "El email es obligatorio")
    @Email(message = "Debe ser un email válido")
    private String userName; // el usuario es el email

    @NotEmpty(message = "nombre de pila es obligatorio")
    private String pilaName;

    @NotEmpty(message = "contraseña obligatoria")
    private String password;

    private boolean isEnable = true;
    private boolean accountNoExpired = true;
    private boolean accountNoLocked = true;
    private boolean credentialNoExpired = true;

    private Rol rol;
}
