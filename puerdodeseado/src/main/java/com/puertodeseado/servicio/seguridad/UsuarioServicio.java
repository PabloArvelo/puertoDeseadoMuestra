package com.puertodeseado.servicio.seguridad;

import com.puertodeseado.EntidadesDTO.seguridad.UsuarioDTO;
import com.puertodeseado.entidades.seguridad.Rol;
import com.puertodeseado.entidades.seguridad.Usuario;
import com.puertodeseado.repositorio.seguridad.RolRepositorio;
import com.puertodeseado.repositorio.seguridad.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;
    @Autowired
    private RolRepositorio rolRepositorio;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void guardarUsuario(Usuario usuario) {
        usuarioRepositorio.save(usuario);
    }

    public void guardarUsuarioDesdeDTO(UsuarioDTO usuarioDTO) {

        String passEncriptado = passwordEncoder.encode(usuarioDTO.getPassword());

        Usuario usuario = Usuario.builder()
                .userName(usuarioDTO.getUserName())
                .pilaName(usuarioDTO.getPilaName())
                .password(passEncriptado)
                .isEnable(usuarioDTO.isEnable())
                .accountNoExpired(usuarioDTO.isAccountNoExpired())
                .accountNoLocked(usuarioDTO.isAccountNoLocked())
                .credentialNoExpired(usuarioDTO.isCredentialNoExpired())
                .rol(usuarioDTO.getRol())
                .build();

        usuarioRepositorio.save(usuario);
    }
}
