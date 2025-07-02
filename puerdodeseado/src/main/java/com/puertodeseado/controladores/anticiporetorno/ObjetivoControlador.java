package com.puertodeseado.controladores.anticiporetorno;

import com.puertodeseado.entidades.anticiporetorno.ObjetivosMain;
import com.puertodeseado.repositorio.anticipoderetorno.ObjetivosMainRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/objetivos")
public class ObjetivoControlador {
  @Autowired
  private ObjetivosMainRepositorio objetivosMainRepositorio;

  @GetMapping("/vistaObjetivos")
  public String vistaObjetivosActivos(){
    return "objetivos";
  }

  @GetMapping("/listar")
  @ResponseBody
  public List<ObjetivosMain> listarObjetivosActivos(){
    List<ObjetivosMain> lista = objetivosMainRepositorio.listarObjetivosActivos();
    System.out.println("hola");

    return lista;
  }


}
