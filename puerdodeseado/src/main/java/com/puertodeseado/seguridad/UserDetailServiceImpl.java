package com.puertodeseado.seguridad;

import com.puertodeseado.entidades.seguridad.Rol;
import com.puertodeseado.entidades.seguridad.Usuario;
import com.puertodeseado.repositorio.seguridad.RolRepositorio;
import com.puertodeseado.repositorio.seguridad.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;




    @Override
    public UserDetails loadUserByUsername(String nombreDeUsuario) throws UsernameNotFoundException {

        //System.out.println("estoy en loadUserByUsername");
        //System.out.println("nombre de usuario ingresado: "+ nombreDeUsuario);



        // toma los usuarios de la base de datos
        Usuario usuario = usuarioRepositorio.findUserEntityByUserName(nombreDeUsuario)
                .orElseThrow(()-> new UsernameNotFoundException("el usuario " + nombreDeUsuario + " no exite"));


        // spring security maneja los permisos con grantedAuthority (autorización concedida)
        // entonces creamos una lista de SimpleGrantedAuthority
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();


        // tomamos el roles que posee el usuario
        // y los convertimos a un objeto que entiende spring security, el cual es SimpleGrantedAuthority
        authorityList.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getRol()));

        // tomamos los permisos de ese rol y los pasamos a la lista para que spring security los tenga.
        usuario.getRol().getListaPermisos()
                .forEach(permiso -> authorityList.add(new SimpleGrantedAuthority(permiso.getPermiso())));

        User pablo = new User(usuario.getUserName(),
                usuario.getPassword(),
                usuario.isEnable(),
                usuario.isAccountNoExpired(),
                usuario.isAccountNoLocked(),
                usuario.isCredentialNoExpired(),
                authorityList);
//
//        System.out.println(pablo);


        // no le paso el nombre de pila porque el objeto User que estoy instanciando es de Spring Security
        return new User(usuario.getUserName(),
                usuario.getPassword(),
                usuario.isEnable(),
                usuario.isAccountNoExpired(),
                usuario.isAccountNoLocked(),
                usuario.isCredentialNoExpired(),
                authorityList);
    }
}
