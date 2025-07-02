package com.puertodeseado.controladores.seguridad;

import com.puertodeseado.EntidadesDTO.RopaDTO;
import com.puertodeseado.EntidadesDTO.RopaTalleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthControlador {

  @GetMapping("/login")
  public String mostrarLogin(@RequestParam(value = "error", required = false) String error, Model model) {
    if (error != null) {
      model.addAttribute("errorMessage", "Credenciales no válidas");
    }
    return "login";
  }


  @GetMapping("/finSesion")
  public String finSesion() {
    return "finSesion";
  }


  @GetMapping("/")
  //@PreAuthorize("hasRole('ADMIN')")
  public String home(Model modelo) {

    // para poder manejar con thymeleaf los objetos DTO, hay que agregarlos a la vista como se muestra a continuación
    // si no se agrada, la vista no carga y da error que el objeto DTO no existe.
    RopaDTO ropaDTO = new RopaDTO();
    modelo.addAttribute("ropaDTO", ropaDTO);

    RopaTalleDTO ropaTalleDTO = new RopaTalleDTO();
    modelo.addAttribute("ropaTalleDTO", ropaTalleDTO);

    return "index";
  }

}
