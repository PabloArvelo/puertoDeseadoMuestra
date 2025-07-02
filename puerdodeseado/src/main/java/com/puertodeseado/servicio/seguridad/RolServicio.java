package com.puertodeseado.servicio.seguridad;

import com.puertodeseado.EntidadesDTO.seguridad.RolesDTO;
import com.puertodeseado.entidades.seguridad.Rol;
import com.puertodeseado.repositorio.seguridad.RolRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServicio {
    @Autowired
    private RolRepositorio rolRepositorio;


    public List<Rol> obtenerTodosLosRoles(){
        return rolRepositorio.findAll();
    }

    public void guardarRolDesdeDTO(RolesDTO rolesDTO){

        Rol rol = Rol.builder()
                .rol(rolesDTO.getRol().toUpperCase().trim())
                .listaPermisos(rolesDTO.getListaPermisos())
                .build();
        rolRepositorio.save(rol);
    }

}
