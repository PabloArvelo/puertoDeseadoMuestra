package com.puertodeseado.ControladorRest;

import com.puertodeseado.clases.RopaEnAsociado;
import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.entidades.Ropa;
import com.puertodeseado.entidades.RopaMovimiento;
import com.puertodeseado.repositorio.AsociadoRepositorio;
import com.puertodeseado.repositorio.RopaMovimientoRepositorio;
import com.puertodeseado.servicio.RopaMovimientoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/fetch")
public class RopaMovimientoRestControlador {
  @Autowired
  private RopaMovimientoRepositorio ropaMovimientoRepositorio;

  @Autowired
  private RopaMovimientoServicio ropaMovimientoServicio;

  @Autowired
  private AsociadoRepositorio asociadoRepositorio;



  @GetMapping("/prueba/{idAsoc}")
  public List<RopaEnAsociado> fetch(@PathVariable String idAsoc){
    List<RopaEnAsociado> ropaEntregada = ropaMovimientoServicio.listarRopaPorAsociado(idAsoc);
    return ropaEntregada;
  }


  // CONSULTA Y CREA LISTA DE LA ROPA CON STOCK SUPERIOR A CERO
  @GetMapping("/ropaEnStock")
  public List<Ropa> listaRopaEnStock (){
    List<Ropa> listaRopaEnStockSelect = new ArrayList<>();
    listaRopaEnStockSelect = ropaMovimientoServicio.listarItemsEnStock();
    return listaRopaEnStockSelect;
  }

  @GetMapping("/listadoEstadosPrendaEnStock/{idPrenda}")
  public List<String> listarEstadosPrendaEnStock (@PathVariable Integer idPrenda){
    return ropaMovimientoServicio.listarEstadosPrendaEnStock(idPrenda);
  }





  // CONSULTA Y CREA LISTA DE LOS TALLES DISPONIBLES DE LA ROPA CON STOCK SUPERIOR A CERO
  @GetMapping("/tallesDisponibles/{idPrenda}/{estado}")
  public List<String> tallesDisponibles (@PathVariable Integer idPrenda, @PathVariable String estado){

    boolean nuevo = true;

    if (estado.equals("usado")){
      nuevo = false;
    }

    List<String> listaTallesDisponibles = new ArrayList<>();
    listaTallesDisponibles = ropaMovimientoRepositorio.tallesDisponiblesPorPrenda(idPrenda, nuevo);

    List<String>  listaFinal = new ArrayList<>();

    for (int i = 0; i < listaTallesDisponibles.size(); i++) {
      String[] talle = listaTallesDisponibles.get(i).split(",");
      listaFinal.add(talle[1]);
    }

    System.out.println(listaFinal);
    return listaFinal;
  }


  // CONSULTA Y DEVUELVE LA CANTIDAD DE STOCK DISPONIBLE DE UNA PRENDA Y TALLE ESPECÍFICO
  @GetMapping("/stockPorPrendaYEstadoYTalle/{idPrenda}/{estado}/{talle}")
  public Integer stockPorPrendaYTalle (@PathVariable Integer idPrenda, @PathVariable String estado, @PathVariable String talle){

    boolean nuevo = true;

    if (estado.equals("usado")){
      nuevo = false;
    }

    Integer stockPorTalle = ropaMovimientoRepositorio.stockPorTalle(idPrenda, nuevo, talle);
    System.out.println(stockPorTalle);
    return stockPorTalle;
  }


  // INSERTA EN LA BASE DE DATOS LA/LAS PRENDAS QUE SE ENTREGAN A LOS ASOCIADOS.
//  @PostMapping("/movimientoPrenda")
//  public void entregarPrenda(@RequestBody List<RopaMovimiento> prendas){
//
//    ropaMovimientoRepositorio.saveAll(prendas);
//  }

  @PostMapping("/movimientoPrenda")
  public ResponseEntity<String> entregarPrenda(@RequestBody List<RopaMovimiento> prendas) {
    if (prendas == null || prendas.isEmpty()) {
      return ResponseEntity.badRequest().body("La lista de prendas no puede estar vacía");
    }

    try {
      ropaMovimientoRepositorio.saveAll(prendas);
      return ResponseEntity.ok("Compra sumada al stock con éxito");
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
              .body("Error al guardar las prendas: " + e.getMessage());
    }
  }

  @GetMapping("/buscaAsociado/{idAsoc}")
  public String buscarAsociado(@PathVariable String idAsoc){

    AsociadosMain asociado = asociadoRepositorio.getReferenceById(idAsoc);
    String nombreAsociado = asociado.getNombre() + " " + asociado.getApellido();

    return nombreAsociado;
  }

}



