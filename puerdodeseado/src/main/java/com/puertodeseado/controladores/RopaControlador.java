package com.puertodeseado.controladores;

import com.puertodeseado.EntidadesDTO.RopaDTO;
import com.puertodeseado.EntidadesDTO.RopaTalleDTO;
import com.puertodeseado.excepciones.PrendaRepetidaException;
import com.puertodeseado.excepciones.TalleRepetidoException;
import com.puertodeseado.servicio.RopaServicio;
import com.puertodeseado.servicio.RopaTallesServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/ropa")
public class RopaControlador {
  @Autowired
  private RopaServicio ropaServicio;
  @Autowired
  private RopaTallesServicio ropaTallesServicio;



  @PostMapping("/altaPrenda")
  public String guardarNuevaPrenda(@Valid RopaDTO ropaDTO, BindingResult result, Model modelo) throws PrendaRepetidaException {

    if (result.hasErrors()){
      modelo.addAttribute("ropaTalleDTO", new RopaTalleDTO());
      return "index.html";
    }

    try {
      ropaServicio.crearNuevaPrenda(ropaDTO.getPrenda());

    }catch (PrendaRepetidaException e){
      modelo.addAttribute("prendaDuplicada","la prenda ya existe");
      modelo.addAttribute("ropaTalleDTO", new RopaTalleDTO());
      return "index.html";
    }

    modelo.addAttribute("ropaTalleDTO", new RopaTalleDTO());
    return "redirect:/";
  }




  @PostMapping("/altaTalle")
  public String guardarNuevoTalle(@Valid RopaTalleDTO ropaTalleDTO, BindingResult result, Model modelo){

    if (result.hasErrors()){
      modelo.addAttribute("ropaDTO", new RopaDTO());
      return "index.html";
    }

    try {
      ropaTallesServicio.crearNuevoTalle(ropaTalleDTO.getTalle());
    }catch (TalleRepetidoException e){
      modelo.addAttribute("talleDuplicado","el talle ya existe");
      modelo.addAttribute("ropaDTO", new RopaDTO());
      return "index.html";
    }

    modelo.addAttribute("ropaDTO", new RopaDTO());
    return "redirect:/";
  }



  @ExceptionHandler(PrendaRepetidaException.class)
  public String handlePrendaRepetidaException(PrendaRepetidaException ex, Model model) {
    model.addAttribute("prendaDuplicada", ex.getMessage());
    return "index.html";
  }


}
