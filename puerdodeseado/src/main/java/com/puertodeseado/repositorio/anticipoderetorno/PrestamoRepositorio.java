package com.puertodeseado.repositorio.anticipoderetorno;

import com.puertodeseado.entidades.anticiporetorno.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface PrestamoRepositorio extends JpaRepository<Prestamo, Integer> {


  // obtengo lista de asociadoos con cuotas a descontar en el período pasado por parámetro
  @Query(value = "SELECT * " +
          "FROM prestamo " +
          "WHERE primera_cuota = :primeraCuota "
          ,nativeQuery = true)
  public List<Object[]> buscarPrestamo(@Param("primeraCuota") String primeraCuota);


  @Query(value = "SELECT * " +
          "FROM prestamo " +
          "WHERE id_asociado = :idAsoc AND primera_cuota = :periodo"
          ,nativeQuery = true)
  public List<Object[]> mostrarPrestamosADescontar(@Param("idAsoc") String idAsoc, @Param("periodo") String periodo);






}
