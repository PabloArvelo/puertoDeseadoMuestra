package com.puertodeseado.repositorio.seguridad;

import com.puertodeseado.entidades.seguridad.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findUserEntityByUserName (String nombreDeUsuario);
    // tambien puedeo reemplazar esto con el query native


    Optional<Usuario> findByResetToken(String resetToken);

}
