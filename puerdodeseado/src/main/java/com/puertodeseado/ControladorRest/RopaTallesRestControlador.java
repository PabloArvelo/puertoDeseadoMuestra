package com.puertodeseado.ControladorRest;

import com.puertodeseado.entidades.RopaTalles;
import com.puertodeseado.repositorio.RopaTallesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fetchTalles")
public class RopaTallesRestControlador {

  @Autowired
  private RopaTallesRepositorio ropaTallesRepositorio;


  // trae todos los talles de la tabla ropa_talles
  @GetMapping("/listarTodos")
  public List<RopaTalles> listaTodosLosTalles (){
    return ropaTallesRepositorio.findAll();
  }

}
