package com.puertodeseado.EntidadesDTO.seguridad;

import com.puertodeseado.entidades.seguridad.Permiso;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RolesDTO {


        @NotEmpty(message = "debe ingresar un permiso")
        private String rol;

        private Set<Permiso> listaPermisos;


}
