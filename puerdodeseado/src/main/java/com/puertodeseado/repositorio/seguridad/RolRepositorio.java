package com.puertodeseado.repositorio.seguridad;

import com.puertodeseado.entidades.seguridad.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepositorio extends JpaRepository<Rol, Integer> {
}
