package com.puertodeseado.controladores;

import com.puertodeseado.EntidadesDTO.AsociadoDTO;
import com.puertodeseado.EntidadesDTO.RopaDTO;
import com.puertodeseado.EntidadesDTO.RopaTalleDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexControlador {
  @GetMapping("/")
  public String index(Model modelo) {

    // para poder manejar con thymeleaf los objetos DTO, hay que agregarlos a la vista como se muestra a continuación
    // si no se agrada, la vista no carga y da error que el objeto DTO no existe.
    RopaDTO ropaDTO = new RopaDTO();
    modelo.addAttribute("ropaDTO", ropaDTO);

    RopaTalleDTO ropaTalleDTO = new RopaTalleDTO();
    modelo.addAttribute("ropaTalleDTO", ropaTalleDTO);

    return "index.html";
  }

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
