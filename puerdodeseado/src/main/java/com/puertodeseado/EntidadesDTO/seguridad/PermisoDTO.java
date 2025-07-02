package com.puertodeseado.EntidadesDTO.seguridad;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PermisoDTO {

    @NotEmpty(message = "debe ingresar un permiso")
    private String permiso;
}
