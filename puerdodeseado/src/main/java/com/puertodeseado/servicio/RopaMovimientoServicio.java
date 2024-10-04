package com.puertodeseado.servicio;

import com.puertodeseado.EntidadesDTO.EstadoPrendaDTO;
import com.puertodeseado.clases.RopaEnAsociado;
import com.puertodeseado.entidades.Ropa;
import com.puertodeseado.entidades.RopaMovimiento;
import com.puertodeseado.repositorio.RopaMovimientoRepositorio;
import com.puertodeseado.repositorio.RopaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class RopaMovimientoServicio {
  @Autowired
  private RopaMovimientoRepositorio ropaMovimientoRepositorio;
  @Autowired
  private RopaRepositorio ropaRepositorio;


  public List<RopaEnAsociado> listarRopaPorAsociado(String idAsoc) {


    // en este listado guardo cada uno de los items de la tabla ROPA
    List<Ropa> listadoRopaEnDB = new ArrayList<>();
    listadoRopaEnDB = listarItems();



    List<Object> desdeBase = ropaMovimientoRepositorio.buscarRopaPorIdAsoc(idAsoc);

    List<RopaEnAsociado> resultado = new ArrayList<>();

    for (Object obj : desdeBase){

      // Cada fila de la consulta es un Object[], donde cada elemento es una columna.
      Object[] fila = (Object[]) obj;

      RopaEnAsociado ropaEnAsociado =  new RopaEnAsociado();

      for (int i = 0; i < listadoRopaEnDB.size(); i++) {
        if (listadoRopaEnDB.get(i).getId() == (Integer) fila[0]){
          RopaEnAsociado prenda = new RopaEnAsociado();
          prenda.setPrenda(listadoRopaEnDB.get(i).getPrenda());
          ropaEnAsociado.setPrenda(prenda.getPrenda());
        }
      }

      ropaEnAsociado.setTalle((String) fila[1] );
      ropaEnAsociado.setCantidad(((BigDecimal) fila[2]).intValue()); // porque no admite el casteo directo de BigDecimal a Integer

      resultado.add(ropaEnAsociado);
    }

    //List<RopaEnAsociado> listadoRopaEnAsoc = ropaMovimientoRepositorio.buscarRopaPorIdAsocOK(idAsoc);
    return resultado;

  }



  // guarda en una lista de tipo Ropa de todos los items de la DB ropa
  public List<Ropa> listarItems() {
    List<Ropa> itemsUniforme = new ArrayList<>();
    itemsUniforme = ropaRepositorio.findAll();

    return itemsUniforme;
  }


  // CONSULTA Y CREA LISTA DE LA ROPA CON STOCK SUPERIOR A CERO
  public List<Ropa> listarItemsEnStock(){

    //me guarda una lista de los ID de ropa que hay en Stock
    List<Integer> listaIdRopaEnStock = new ArrayList<>();
    listaIdRopaEnStock = ropaMovimientoRepositorio.buscarRopaEnStock();

    //  guardo cada uno de los items de la tabla ROPA
    List<Ropa> listadoRopaEnDB = new ArrayList<>();
    listadoRopaEnDB = listarItems();


    List<Ropa> listadoRopaEnStock = new ArrayList<>();


    for (int i = 0; i < listaIdRopaEnStock.size(); i++) {

      for (int j = 0; j < listadoRopaEnDB.size() ; j++) {

        if (listaIdRopaEnStock.get(i) == listadoRopaEnDB.get(j).getId()){
          Ropa ropaEnStock = new Ropa();
          ropaEnStock.setId(listadoRopaEnDB.get(j).getId());
          ropaEnStock.setPrenda(listadoRopaEnDB.get(j).getPrenda());
          listadoRopaEnStock.add(ropaEnStock);
        }
      }
    }

    return listadoRopaEnStock;
  }


  // obtengo una ista de los estados disponibles de la prenda segunid de prenda
  public List<String> listarEstadosPrendaEnStock(Integer idPrenda){

    // para la consulta en la base de datos, es necesaro un objeto de tipo Object
    // esto es porque la consulta no devuelve toda la entidad ropa movimiento, sino que solo algunos campos
    // entonces guardo cada campo en un array de tipo Object[]
    List<Object[]> resultados = ropaMovimientoRepositorio.buscarEstadosPrendaEnStock(idPrenda);


    // creo una instanca de tipo EstadoPrendaDTO para luego mapear el array de objetos con la clase EstadoPrendaDTO
    List<EstadoPrendaDTO> estadosPrenda = new ArrayList<>();

    // mapeo
    for (Object[] fila : resultados) {
      EstadoPrendaDTO dto = new EstadoPrendaDTO();
      dto.setIdPrenda((Integer) fila[0]);
      dto.setNuevo((Boolean) fila[1]);
      // en la agregación de la query AS stock, aqui devuelve un BigDecimal, por eso el casteo
      dto.setStock(((BigDecimal) fila[2]).intValue());

      estadosPrenda.add(dto);
    }

    List<String> estados = new ArrayList<>();

    // asigno nuevo o usado segun si es true o false
    for (int i = 0; i < estadosPrenda.size(); i++) {
      if (estadosPrenda.get(i).getNuevo()){
        estados.add("nuevo");
      }else {
        estados.add("usado");
      }
    }

    return estados;
  }









  // guarda en una lista de tipo RopaMovimiento de todos los movimientos de ropa de la DB ropa_movimiento
  public List<RopaMovimiento> listarMovimientos() {
    List<RopaMovimiento> movimientos = new ArrayList<>();
    movimientos = ropaMovimientoRepositorio.findAll();
    return movimientos;
  }




}
