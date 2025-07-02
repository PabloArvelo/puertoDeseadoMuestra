package com.puertodeseado.repositorio.anticipoderetorno;

import com.puertodeseado.entidades.anticiporetorno.ObjetivosMain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ObjetivosMainRepositorio extends JpaRepository<ObjetivosMain, Integer> {

// lista todos los objetivos que estan de alta (estado 1)
  @Query(value = "SELECT * " +
          "FROM objetivos_main " +
          "WHERE estado = 1", nativeQuery = true)
  public List<ObjetivosMain> listarObjetivosActivos();

}
