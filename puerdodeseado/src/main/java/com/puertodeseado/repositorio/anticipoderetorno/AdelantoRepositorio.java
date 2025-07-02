package com.puertodeseado.repositorio.anticipoderetorno;

import com.puertodeseado.entidades.anticiporetorno.Adelanto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Repository
public interface AdelantoRepositorio extends JpaRepository<Adelanto, Integer> {

  // obtengo lista de asociados con adelantos a descontar en el período pasado por parámetro
  @Query(value = "select id_asociado, SUM(monto) as total " +
          "FROM adelanto " +
          "where monto is not null and descuenta_en_liquidacion = :descuenta_en_Liquidacion " +
          "group by id_asociado"
          , nativeQuery = true)
  public List<Object[]> buscarAdelantos(@Param("descuenta_en_Liquidacion") String descuentaEnLiquidacion);


  // OBTENGO EL/LOS ADELANTOS A DESCONTAR EN LA PRÓXIMA LIQUIDACION
  // PARA MOSTRAR EN LA VISTA DE ADELANTOS
  @Query(value = "SELECT * " +
          "FROM adelanto " +
          "WHERE id_asociado = :idAsoc AND descuenta_en_liquidacion = :periodo " +
          "ORDER BY fecha_entrega ASC"
          ,nativeQuery = true)
  public List<Object[]> mostrarAdelantosADescontar(@Param("idAsoc") String idAsoc, @Param("periodo") String periodo);





}
