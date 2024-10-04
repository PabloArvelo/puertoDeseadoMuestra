package com.puertodeseado.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/anticipos")
public class Anticipos {
  @GetMapping("/enConstruccion")
  public String enConstruccion(){
    return "anticipos.html";
  }
}
