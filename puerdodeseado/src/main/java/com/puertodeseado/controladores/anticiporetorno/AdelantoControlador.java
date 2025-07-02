package com.puertodeseado.controladores.anticiporetorno;

import com.puertodeseado.EntidadesDTO.RespuestaDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.AdelantoDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.AdelantosADescontarDTO;
import com.puertodeseado.servicio.anticiporetorno.AdelantoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/adelanto")
public class AdelantoControlador {


  @Autowired
  private AdelantoServicio adelantoServicio;


  @GetMapping("/vistaAdelanto/{idAsoc}")
  public String vistaAdelanto(@PathVariable String idAsoc, Model modelo) {


    // muestra en la vista de adelantos si ya tenía adelantos para descontar en la próxima liquidación
    List<AdelantosADescontarDTO> adelantos = adelantoServicio.mostrarAdelantosADescontar(idAsoc);

    if (!adelantos.isEmpty()){
      modelo.addAttribute("adelantos",adelantos);
    }else {
      modelo.addAttribute("sinAsignar","no se han asignado adelantos");
    }


    modelo.addAttribute("idAsoc", idAsoc);
    modelo.addAttribute("adelantoDTO", new AdelantoDTO());

    return "adelanto.html";
  }

  @PostMapping("/asignarAdelanto/{idAsoc}")
  public String asignarAdelanto(@PathVariable String idAsoc,
                                @ModelAttribute AdelantoDTO adelantoDTO,
                                BindingResult result,
                                Model modelo) {

    if (adelantoDTO.getMonto() == null) {
      // muestra en la vista de adelantos si ya tenía adelantos para descontar en la próxima liquidación
      List<AdelantosADescontarDTO> adelantos = adelantoServicio.mostrarAdelantosADescontar(idAsoc);

      result.rejectValue("monto", "error.adelantoDTO", "debe ingresar un monto");

      modelo.addAttribute("adelantos",adelantos);
      modelo.addAttribute("idAsoc", idAsoc);
      modelo.addAttribute("adelantoDTO", adelantoDTO);
      return "adelanto.html";
    }


    RespuestaDTO respuesta = adelantoServicio.asignarAdelanto(adelantoDTO.getDescuentaEnLiquidacion(),
            adelantoDTO.getMonto(),
            idAsoc);

    modelo.addAttribute("respuesta", respuesta);
    modelo.addAttribute("idAsoc", idAsoc);
    modelo.addAttribute("adelantoDTO", adelantoDTO);
    modelo.addAttribute("deshabilitar", true);
    return "adelanto.html";
  }
}
