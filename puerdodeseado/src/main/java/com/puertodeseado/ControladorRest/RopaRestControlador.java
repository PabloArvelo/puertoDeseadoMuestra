package com.puertodeseado.ControladorRest;

import com.puertodeseado.entidades.Ropa;
import com.puertodeseado.repositorio.RopaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fetchRopa")
public class RopaRestControlador {
  @Autowired
  private RopaRepositorio ropaRepositorio;

  @GetMapping("/listarTodos")
  public List<Ropa> listaTodosLasPrendas (){
    return ropaRepositorio.findAll();
  }
}
