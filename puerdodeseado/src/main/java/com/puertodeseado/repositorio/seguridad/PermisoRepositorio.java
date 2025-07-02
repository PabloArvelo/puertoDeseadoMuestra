package com.puertodeseado.repositorio.seguridad;

import com.puertodeseado.entidades.seguridad.Permiso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermisoRepositorio extends JpaRepository<Permiso, Integer> {
}
