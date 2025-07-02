package com.puertodeseado.controladores.anticiporetorno;

import com.puertodeseado.EntidadesDTO.RespuestaDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.CrearLiquidacionDTO;
import com.puertodeseado.entidades.anticiporetorno.LiquidacionHistorial;
import com.puertodeseado.repositorio.anticipoderetorno.LiquidacionHistorialRepositorio;
import com.puertodeseado.servicio.CrearExcelFileServicio;
import com.puertodeseado.servicio.anticiporetorno.LiquidacionHistorialServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/liquidaciones")
public class CrearLiquidacionControlador {

  @Autowired
  private LiquidacionHistorialServicio liquidacionHistorialServicio;

  @Autowired
  private LiquidacionHistorialRepositorio liquidacionHistorialRepositorio;

  @Autowired
  private CrearExcelFileServicio crearExcelFileServicio;

  @GetMapping("/vistaLiquidaciones")
  public String vistaCrearLiquidacion(Model modelo) {


    //System.out.println("hola desde controlador");

    modelo.addAttribute("crearLiquidacionDTO", new CrearLiquidacionDTO());

    RespuestaDTO resuesta = new RespuestaDTO(false, "");
    modelo.addAttribute("respuesta", resuesta);
    return "crearLiquidacion.html";
  }


  @PostMapping("/crearNuevaLiquidacion")
  public String crearNuevaLiquidacion(@Valid CrearLiquidacionDTO crearLiquidacionDTO,
                                      BindingResult result,
                                      Model modelo) throws Exception {

    if (!crearLiquidacionDTO.getArchivoCSV().getOriginalFilename().endsWith(".csv")) {
      result.rejectValue("archivoCSV", "error.archivoCSV", "el archivo debe ser un csv válido");
    }


    if (result.hasErrors()) {
      return "crearLiquidacion.html";
    }


    RespuestaDTO respuesta = liquidacionHistorialServicio.importarLiquidacionDesdeCSV(crearLiquidacionDTO.getPeriodoNuevaLiquidacion(),
            crearLiquidacionDTO.getArchivoCSV());

    modelo.addAttribute("respuesta", respuesta);
    modelo.addAttribute("crearLiquidacionDTO", new CrearLiquidacionDTO());

    return "crearLiquidacion.html";
  }


  @GetMapping("/vistaAprobar")
  public String vistaAprbarLiquidacion(@RequestParam String periodo, Model model) {

    System.out.println("el periodo es "+periodo);

    model.addAttribute("periodo", periodo);
    return "liquidacionAprobar";
  }

  @ResponseBody
  @GetMapping("/buscarLiqSinAproPorPer")
  public List<LiquidacionHistorial> buscarLiquidacionSinAprobarPorPeriodo(@RequestParam("periodo") String periodo) {
    System.out.println(periodo);
    return liquidacionHistorialRepositorio.buscarLiquidacionSinAprobarPorPeriodo(periodo);
  }

  @PostMapping("/aprobarLiquidacion")
  public ResponseEntity<String> aprobarLiquidacion(@RequestParam("periodo") String periodo) {

    try {
      int resultado = liquidacionHistorialRepositorio.aprobarLiquidacion(periodo);

      if (resultado>0){
        return ResponseEntity.ok("liqui aprobada ok");
      }else {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ni ahi se aprobó");
      }
    }catch (Exception e){
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error interno "+ e.getMessage());
    }

  }


  @ResponseBody
  @GetMapping("/totalAPagar")
  public BigDecimal totalPagarLiquidacion(@RequestParam String periodo){
    BigDecimal total = liquidacionHistorialRepositorio.totalPagarLiquidacion(periodo);
    System.out.println(total);
    return total;
  }


  @GetMapping("/exportarPadronAsociadosActivos")
  public ResponseEntity<byte[]> descargarExcel() {
    return crearExcelFileServicio.listadoAsociadosLiquidacionWeb();
  }

}
