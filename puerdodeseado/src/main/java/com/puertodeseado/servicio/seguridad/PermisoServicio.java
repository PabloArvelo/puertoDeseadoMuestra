package com.puertodeseado.servicio.seguridad;

import com.puertodeseado.EntidadesDTO.seguridad.PermisoDTO;
import com.puertodeseado.entidades.seguridad.Permiso;
import com.puertodeseado.repositorio.seguridad.PermisoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermisoServicio {

    @Autowired
    private PermisoRepositorio permisoRepositorio;

    public void guardarPermiso(Permiso permiso) {
        permisoRepositorio.save(permiso);
    }

    public List<Permiso> obtenerTodosLosPermisos() {
        return permisoRepositorio.findAll();
    }

    public void guardarPermisoDesdeDTO(PermisoDTO permisoDTO){



        Permiso permiso = Permiso.builder()
                .permiso(permisoDTO.getPermiso().toUpperCase().trim())
                .build();

        permisoRepositorio.save(permiso);
    }





}
