package com.puertodeseado.repositorio;

import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImagenRepositorio extends JpaRepository<Imagen, String> {


  @Modifying
  @Query(value = "UPDATE imagen SET " +
          "contenido = :contenido," +
          "mime = :mime," +
          "nombre = :nombre " +
          "WHERE id = :id", nativeQuery = true)
  public void actualizarImagen(@Param("contenido") byte[] contenido,
                                              @Param("mime") String mime,
                                              @Param("nombre") String nombre,
                                              @Param("id") String id);



}
