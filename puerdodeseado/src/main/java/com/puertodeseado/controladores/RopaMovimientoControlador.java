package com.puertodeseado.controladores;

import com.puertodeseado.clases.RopaEnAsociado;
import com.puertodeseado.entidades.Ropa;
import com.puertodeseado.servicio.RopaMovimientoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/ropaMovimiento")
public class RopaMovimientoControlador {
  @Autowired
  private RopaMovimientoServicio ropaMovimientoServicio;


  @GetMapping("/listarRopaPorAsociado/{idAsoc}/{cuil}")
  public String listarRopaPorAsociado(@PathVariable String idAsoc, @PathVariable String cuil, ModelMap modelo) {

    modelo.put("idAsoc",idAsoc);
    modelo.put("cuil",cuil);

    List<RopaEnAsociado> ropaEntregada = ropaMovimientoServicio.listarRopaPorAsociado(idAsoc);


//    probar bien estas 3 lineas  que está comentado abajo
//            si no borrar

//    List<Ropa> itemsEnStockSelect = new ArrayList<>();
//    itemsEnStockSelect = ropaMovimientoServicio.listarItemsEnStock();
//    modelo.addAttribute("itemsEnStock", itemsEnStockSelect);


    if (ropaEntregada.isEmpty()){
      modelo.put("sinAsignar","Aun no se asignó uniforme");
    }else {
      modelo.addAttribute("prendas", ropaEntregada);
    }

    return "ropamovimiento_2.html";
  }


  @GetMapping("/ropaIngresoCompra")
  public String ropaIngresoCompra(){
    return "ropaIngresoCompra.html";
  }

  @GetMapping("/ropaIngresoUsado")
  public String ropaIngresoUsado(){
    return "ropaIngresoUsado.html";
  }



  @GetMapping("/ropaIngresoDevolucion/{idAsoc}/{cuil}")
  public String ropaIngresoDevolucion(@PathVariable String idAsoc, @PathVariable Long cuil, ModelMap modelo){
    modelo.put("idAsoc",idAsoc);
    modelo.put("cuil",cuil);
    return "ropaIngresoDevolucion.html";
  }

  @GetMapping("/editarUnifYTalle")
  public String editarUniYTalle(){
    return "ropaYTalleEditar.html";
  }

}
