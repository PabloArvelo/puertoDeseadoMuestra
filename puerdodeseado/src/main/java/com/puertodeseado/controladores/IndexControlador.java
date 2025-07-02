package com.puertodeseado.controladores;

import com.puertodeseado.EntidadesDTO.AsociadoDTO;
import com.puertodeseado.EntidadesDTO.RopaDTO;
import com.puertodeseado.EntidadesDTO.RopaTalleDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexControlador {

  //@PreAuthorize("hasAuthority('CREATE')")
  @GetMapping("/alta_asoc")
  public String altaAsoc(Model modelo) {

    AsociadoDTO asociadoDTO = new AsociadoDTO();
    modelo.addAttribute("asociadoDTO", asociadoDTO);

    return "altaAsoc.html";
  }


  @GetMapping("/importarCSV")
  public String importarCSV(){
    return "importarCSV.html";
  }

}
