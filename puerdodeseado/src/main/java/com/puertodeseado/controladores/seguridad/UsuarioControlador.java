package com.puertodeseado.controladores.seguridad;

import com.puertodeseado.entidades.seguridad.Usuario;
import com.puertodeseado.servicio.seguridad.RolServicio;
import com.puertodeseado.servicio.seguridad.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/usuarios")
public class UsuarioControlador {

    @Autowired
    private RolServicio rolServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    // Mostrar el formulario de alta de usuario
    @GetMapping("/nuevo")
    public String crearUsuarioForm(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolServicio.obtenerTodosLosRoles());
        return "alta_usuario";
    }

    // Guardar usuario
//    @PostMapping("/guardar")
//    public String guardarUsuario(@ModelAttribute Usuario usuario) {
//        usuarioServicio.guardarUsuario(usuario);
//        return "redirect:/usuarios/nuevo";  // Redirige a la página de alta de usuario después de guardar
//    }

}
