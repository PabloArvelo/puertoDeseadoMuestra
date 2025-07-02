package com.puertodeseado.controladores;

import com.puertodeseado.clases.RopaStockGeneral;
import com.puertodeseado.servicio.CrearPdfServicio;
import com.puertodeseado.servicio.RopaStockServicio;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/stock")
public class RopaStockControlador {

  @Autowired
  private RopaStockServicio ropaStockServicio;
  @Autowired
  private CrearPdfServicio crearPdfServicio;

  @GetMapping("/uniformes")
  public String stockUniformes(ModelMap modelo){

    List<RopaStockGeneral> stockGeneral = ropaStockServicio.ropaStock();
    modelo.addAttribute("stockPrendas", stockGeneral);

    return "ropaStock.html";
  }

  @GetMapping("/pdf")
  public void crearPDF(HttpServletResponse response) throws IOException {
    List<RopaStockGeneral> stockGeneral = ropaStockServicio.ropaStock();

    // Configurar la respuesta HTTP
    response.setContentType("application/pdf");
    response.setHeader("Content-Disposition","attachment; filename=stockUniformes.pdf");


    // Crear el PDF y escribirlo en la respuesta
    crearPdfServicio.crearPdf(response.getOutputStream(),stockGeneral);

  }

}
