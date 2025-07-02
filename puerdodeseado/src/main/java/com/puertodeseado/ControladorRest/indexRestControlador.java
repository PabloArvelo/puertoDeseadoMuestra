package com.puertodeseado.ControladorRest;

import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.servicio.AsociadosMainServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class indexRestControlador {

  @Autowired
  private AsociadosMainServicio asociadosMainServicio;

  @GetMapping("/asociadoPorApellido/{apellido}")
  public List<AsociadosMain> buscarAsociadoPorCuil(@PathVariable String apellido){
    return asociadosMainServicio.buscarPorApellido(apellido);
  }

}
