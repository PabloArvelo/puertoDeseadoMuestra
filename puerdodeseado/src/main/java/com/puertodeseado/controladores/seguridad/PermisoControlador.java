package com.puertodeseado.controladores.seguridad;

import com.puertodeseado.entidades.seguridad.Permiso;
import com.puertodeseado.servicio.seguridad.PermisoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/permisos")
public class PermisoControlador {

    @Autowired
    private PermisoServicio permisoServicio;

    // Mostrar el formulario de alta de permiso
    @GetMapping("/nuevo")
    public String crearPermisoForm(Model model) {
        model.addAttribute("permiso", new Permiso());
        return "alta_permiso";
    }

    // Guardar permiso
    @PostMapping("/guardar")
    public String guardarPermiso(@ModelAttribute Permiso permiso) {
        permisoServicio.guardarPermiso(permiso);
        return "redirect:/permisos/nuevo";  // Redirige después de guardar
    }

}
