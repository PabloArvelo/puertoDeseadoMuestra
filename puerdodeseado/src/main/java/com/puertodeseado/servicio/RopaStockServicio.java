package com.puertodeseado.servicio;

import com.puertodeseado.EntidadesDTO.RopaStockDTO;
import com.puertodeseado.clases.RopaStockGeneral;
import com.puertodeseado.entidades.Ropa;
import com.puertodeseado.repositorio.RopaRepositorio;
import com.puertodeseado.repositorio.RopaStockRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RopaStockServicio {

  @Autowired
  private RopaStockRepositorio ropaStockRepositorio;

  @Autowired
  private RopaRepositorio ropaRepositorio;


  public List<RopaStockGeneral> ropaStock() {
    // lista que va a recibir la consulta de la base de datos
    List<RopaStockDTO> listaRopaStock = ropaStockRepositorio.listarRopaStock();

    List<RopaStockGeneral> stockGeneral = new ArrayList<>();


    for (int i = 0; i < listaRopaStock.size(); i++) {
      // obtengo el id de la prenda
      int idPrenda = Integer.parseInt(listaRopaStock.get(i).getId_Prenda());


      // busco la prenda por ID
      Ropa prenda = new Ropa();
      prenda = ropaRepositorio.getReferenceById(idPrenda);

      RopaStockGeneral item = new RopaStockGeneral();

      item.setPrenda(prenda.getPrenda());
      item.setTalle(listaRopaStock.get(i).getTalle());

      if (listaRopaStock.get(i).getNuevo()){
        item.setEstado("NUEVO");
      }else {
        item.setEstado("USADO");
      }

      item.setStock(listaRopaStock.get(i).getStock());

      stockGeneral.add(item);
    }

    return stockGeneral;
  }

}


