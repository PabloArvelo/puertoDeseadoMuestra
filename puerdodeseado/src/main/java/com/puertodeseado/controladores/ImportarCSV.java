package com.puertodeseado.controladores;

import com.puertodeseado.EntidadesDTO.RopaDTO;
import com.puertodeseado.EntidadesDTO.RopaTalleDTO;
import com.puertodeseado.servicio.AsociadosMainServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/importar")
public class ImportarCSV {
  @Autowired
  private AsociadosMainServicio asociadosMainServicio;

  @PostMapping("/importar-csv")
  public String importarCSV(@RequestParam("archivoCSV") MultipartFile archivoCSV, RedirectAttributes redirectAttributes, Model modelo) {
    try {
      asociadosMainServicio.importarDesdeCSV(archivoCSV);
      modelo.addAttribute("ropaDTO", new RopaDTO());
      modelo.addAttribute("ropaTalleDTO", new RopaTalleDTO());
      System.out.println("estoy en try");
      redirectAttributes.addFlashAttribute("mensaje", "Archivo importado exitosamente");
    } catch (Exception e) {
      modelo.addAttribute("ropaDTO", new RopaDTO());
      modelo.addAttribute("ropaTalleDTO", new RopaTalleDTO());
      System.out.println("estoy en catch");
      System.out.println(e.getMessage());
      redirectAttributes.addFlashAttribute("mensaje", "Error al importar archivo: " + e.getMessage());
      e.printStackTrace();
    }
    return "redirect:/";
  }
}
