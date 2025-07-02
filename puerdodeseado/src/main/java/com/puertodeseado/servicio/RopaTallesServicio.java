package com.puertodeseado.servicio;

import com.puertodeseado.entidades.RopaTalles;
import com.puertodeseado.excepciones.TalleRepetidoException;
import com.puertodeseado.repositorio.RopaTallesRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RopaTallesServicio {
  @Autowired
  private RopaTallesRepositorio ropaTallesRepositorio;

  @Transactional(rollbackFor = Exception.class)
  public void crearNuevoTalle(String talle) throws TalleRepetidoException {
    RopaTalles ropaTalles = new RopaTalles();
    ropaTalles.setTalle(talle.toUpperCase());

    try{
      ropaTallesRepositorio.save(ropaTalles);
    }catch (DataIntegrityViolationException e){
      throw new TalleRepetidoException("el talle ya existe");
    }
  }

}
