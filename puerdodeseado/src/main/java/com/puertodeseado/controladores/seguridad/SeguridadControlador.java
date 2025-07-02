package com.puertodeseado.controladores.seguridad;

import com.puertodeseado.EntidadesDTO.seguridad.PermisoDTO;
import com.puertodeseado.EntidadesDTO.seguridad.RolesDTO;
import com.puertodeseado.EntidadesDTO.seguridad.UsuarioDTO;
import com.puertodeseado.entidades.seguridad.Usuario;
import com.puertodeseado.repositorio.seguridad.PermisoRepositorio;
import com.puertodeseado.servicio.seguridad.PermisoServicio;
import com.puertodeseado.servicio.seguridad.RolServicio;
import com.puertodeseado.servicio.seguridad.UsuarioServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seguridad")
public class SeguridadControlador {

    @Autowired
    private RolServicio rolServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private PermisoServicio permisoServicio;

    @Autowired
    private PermisoRepositorio permisoRepositorio;

    @GetMapping("/")
    public String vistaSeguridad(){
        return "seguridad/vistaSeguridad.html";
    }

    //////////////////////////////////////////////// USUARIOS ////////////////////////////////////////////////

    @GetMapping("/usuarios")
    public String vistaUsuarios(){
        return "seguridad/usuariosVista.html";
    }
    @GetMapping("/usuarios/alta")
    public String usuariosAlta(Model model){
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        model.addAttribute("rol", rolServicio.obtenerTodosLosRoles());
        return "seguridad/usuarioAlta.html";
    }
    @PostMapping("/usuarioGuardar")
    public String crearUsuario(@Valid @ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO,
                               BindingResult result,
                               Model model){
        if (result.hasErrors()) {
            model.addAttribute("usuarioDTO", new UsuarioDTO());
            model.addAttribute("rol", rolServicio.obtenerTodosLosRoles());
            return "seguridad/usuarioAlta.html";
        }

        usuarioServicio.guardarUsuarioDesdeDTO(usuarioDTO);
        model.addAttribute("usuarioDTO", new UsuarioDTO());
        model.addAttribute("rol", rolServicio.obtenerTodosLosRoles());
        return "seguridad/usuariosVista";

    }

    //////////////////////////////////////////////// ROLES ////////////////////////////////////////////////

    @GetMapping("/roles")
    public String vistaRoles(){
        return "seguridad/rolesVista.html";
    }

    @GetMapping("/roles/alta")
    public String rolesAlta(Model model){
        model.addAttribute("rolesDTO", new RolesDTO());
        model.addAttribute("listaPermisos", permisoRepositorio.findAll());
        return "seguridad/rolesAlta.html";
    }

    @PostMapping("/rolGuardar")
    public String crearRol(@Valid @ModelAttribute("rolesDTO") RolesDTO rolesDTO,
                               BindingResult result,
                               Model model){

        if (result.hasErrors()) {
            model.addAttribute("rolesDTO", new RolesDTO());
            model.addAttribute("listaPermisos", permisoRepositorio.findAll());
            return "seguridad/rolesAlta";
        }

        //permisoServicio.guardarPermisoDesdeDTO(permisoDTO);
        rolServicio.guardarRolDesdeDTO(rolesDTO);
        model.addAttribute("listaPermisos", permisoRepositorio.findAll());
        return "seguridad/rolesAlta";
    }




    //////////////////////////////////////////////// PERMISOS ////////////////////////////////////////////////

    @GetMapping("/permisos")
    public String vistaPermisos(){
        return "seguridad/permisosVista.html";
    }

    @GetMapping("/permisos/alta")
    public String permisosAlta(Model model){
        model.addAttribute("permisoDTO", new PermisoDTO());
        return "seguridad/permisosAlta.html";
    }

    @PostMapping("/permisoGuardar")
    public String crearPermiso(@Valid @ModelAttribute("permisoDTO") PermisoDTO permisoDTO,
                               BindingResult result,
                               Model model){

        if (result.hasErrors()) {
            return "seguridad/permisosAlta";
        }

        permisoServicio.guardarPermisoDesdeDTO(permisoDTO);
        return "seguridad/permisosAlta";
    }




}
