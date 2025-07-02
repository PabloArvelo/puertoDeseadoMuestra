package com.puertodeseado.controladores.anticiporetorno;

import com.puertodeseado.EntidadesDTO.RespuestaDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.PrestamoADescontarDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.PrestamoDTO;
import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.servicio.AsociadosMainServicio;
import com.puertodeseado.servicio.anticiporetorno.AdelantoServicio;
import com.puertodeseado.servicio.anticiporetorno.PrestamoServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.YearMonth;
import java.util.List;

@Controller
@RequestMapping("/prestamo")
public class PrestamoControlador {

  @Autowired
  private PrestamoServicio prestamoServicio;
  @Autowired
  private AsociadosMainServicio asociadosMainServicio;

  @GetMapping("/vistaPrestamo/{idAsoc}")
  public String vistaPrestamo(@PathVariable String idAsoc, Model modelo) {

    // muestra en la vista de prestamos si ya tenía cuotas para descontar en la próxima liquidación
    List<PrestamoADescontarDTO> prestamos = prestamoServicio.mostrarPrestamosADescontar(idAsoc);

    if (!prestamos.isEmpty()) {
      modelo.addAttribute("prestamos", prestamos);
    } else {
      modelo.addAttribute("sinAsignar", "no posee prestamos vigentes");
    }


    modelo.addAttribute("idAsoc", idAsoc);
    modelo.addAttribute("prestamoDTO", new PrestamoDTO());
    return "prestamo.html";
  }



  @PostMapping("/asignarPrestamo/{idAsoc}")
  public String asignarPrestamo(@PathVariable String idAsoc,
                                @Valid PrestamoDTO prestamoDTO,
                                BindingResult result,
                                Model modelo) {


    //primero valido la parte de interes, por si tildó la casilla de interés pero no asigno un interes en el input
    if (prestamoDTO.getIntSiNo() && prestamoDTO.getInteres() == null) {
      modelo.addAttribute("interes", "*");
      return "prestamo.html";
    } else {

      if (result.hasErrors()) {
        return "prestamo.html";
      } else {

        AsociadosMain asociado = asociadosMainServicio.getOne(idAsoc);


        RespuestaDTO respuesta = prestamoServicio.asignarPrestamo(prestamoDTO.getIntSiNo(), //
                prestamoDTO.getPrimeraCuota(), //
                asociado,  //
                prestamoDTO.getCantidadCuotas(), //
                prestamoDTO.getMontoPrestamo(),  //
                prestamoDTO.getInteres());  //

        modelo.addAttribute("respuesta", respuesta);
        modelo.addAttribute("idAsoc", idAsoc);
        modelo.addAttribute("prestamoDTO", prestamoDTO);
        modelo.addAttribute("deshabilitar", true);

        return "prestamo.html";

      }

    }

  }


}
