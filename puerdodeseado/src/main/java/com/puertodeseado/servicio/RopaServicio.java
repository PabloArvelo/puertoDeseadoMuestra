package com.puertodeseado.servicio;

import com.puertodeseado.entidades.Ropa;
import com.puertodeseado.excepciones.PrendaRepetidaException;
import com.puertodeseado.repositorio.RopaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class RopaServicio {
  @Autowired
  private RopaRepositorio ropaRepositorio;

  @Transactional(rollbackFor = Exception.class)
  public void crearNuevaPrenda(String prenda) throws PrendaRepetidaException {
    Ropa nuevaPrenda = new Ropa();
    nuevaPrenda.setPrenda(prenda.toUpperCase());

    try {
      ropaRepositorio.save(nuevaPrenda);
    }catch (DataIntegrityViolationException e){
        throw new PrendaRepetidaException("la prenda ya existe");
    }
  }

}
