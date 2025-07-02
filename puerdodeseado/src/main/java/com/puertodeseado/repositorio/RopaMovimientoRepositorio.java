package com.puertodeseado.repositorio;

import com.puertodeseado.EntidadesDTO.EstadoPrendaDTO;
import com.puertodeseado.clases.RopaEnAsociado;
import com.puertodeseado.entidades.RopaMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RopaMovimientoRepositorio extends JpaRepository<RopaMovimiento, Integer> {


  // nueva consulta con la restructuración de la tabla
  // usando en lugar de jpql, sintaxis de mysql nativa

  // EN LA VISTA DE ropaMovimiento, ME MUESTRA LA ROPA QUE TIENE EL ASOCIADO.
  @Query(value =
          "SELECT id_prenda, talle, " +
                  "SUM(CASE WHEN tipo_movimiento = 'entrega'  THEN cantidad ELSE 0 END) - " +
                  "SUM(CASE WHEN tipo_movimiento = 'devolucion'  THEN cantidad ELSE 0 END) AS cantidad " +
                  "FROM ropa_movimiento " +
                  "WHERE id_asociado = :idAsociado " +
                  "GROUP BY id_prenda, talle " +
                  "HAVING cantidad > 0 " +
                  "ORDER BY id_prenda, talle;"
          , nativeQuery = true)
  public List<Object> buscarRopaPorIdAsoc(@Param("idAsociado") String idAsociado);


  // CONSULTA PARA LISTAR LA ROPAR QUE HAY EN SOTCK
  // me devuelve una lista con los ID de la ropa con stock > 0
  @Query(value = "SELECT id_prenda," +
          "SUM(CASE WHEN tipo_movimiento = 'compra' THEN cantidad ELSE 0 END) - " +
          "SUM(CASE WHEN tipo_movimiento = 'entrega' THEN cantidad ELSE 0 END) + " +
          "SUM(CASE WHEN tipo_movimiento = 'devolucion' THEN cantidad ELSE 0 END) + " +
          "SUM(CASE WHEN tipo_movimiento = 'devolusado' THEN cantidad ELSE 0 END) AS stock " +
          "FROM ropa_movimiento " +
          "GROUP BY id_prenda " +
          "having stock > 0",
          nativeQuery = true)
  public List<Integer> buscarRopaEnStock();

  // busco el listado de estados (nuevo-usado) que tengo de una prenda segun id de prenda
  @Query(value = "SELECT id_prenda, nuevo," +
          "SUM(CASE WHEN tipo_movimiento = 'compra' THEN cantidad ELSE 0 END) - " +
          "SUM(CASE WHEN tipo_movimiento = 'entrega' THEN cantidad ELSE 0 END) + " +
          "SUM(CASE WHEN tipo_movimiento = 'devolucion' THEN cantidad ELSE 0 END) + " +
          "SUM(CASE WHEN tipo_movimiento = 'devolusado' THEN cantidad ELSE 0 END) AS stock " +
          "FROM ropa_movimiento " +
          "WHERE id_prenda= :idPrenda  " +
          "GROUP BY nuevo " +
          "having stock > 0",
          nativeQuery = true)
  public List<Object[]> buscarEstadosPrendaEnStock(@Param("idPrenda") Integer idPrenda);


  // busco el listado de talles que tengo de una prenda segun id de prenda y estado (nuevo - usado)
  @Query(value = "SELECT id_prenda, talle, " +
          "SUM(CASE WHEN tipo_movimiento = 'compra' THEN cantidad ELSE 0 END) - " +
          "SUM(CASE WHEN tipo_movimiento = 'entrega' THEN cantidad ELSE 0 END) + " +
          "SUM(CASE WHEN tipo_movimiento = 'devolucion' THEN cantidad ELSE 0 END) + " +
          "SUM(CASE WHEN tipo_movimiento = 'devolusado' THEN cantidad ELSE 0 END) AS stock " +
          "FROM ropa_movimiento " +
          "WHERE nuevo= :nuevo " +
          "GROUP BY   id_prenda, talle " +
          "having stock > 0 AND id_prenda = :idPrenda " +
          "ORDER BY talle",
          nativeQuery = true)
  public List<String> tallesDisponiblesPorPrenda(@Param("idPrenda") Integer idPrenda, @Param("nuevo") Boolean nuevo);


  // busco el stock filtrando por prenda estado y talle
  @Query(value = "SELECT stock " +
          "from ( " +
          "SELECT id_prenda, talle, " +
          "SUM(CASE WHEN tipo_movimiento = 'compra' THEN cantidad ELSE 0 END) - " +
          "SUM(CASE WHEN tipo_movimiento = 'entrega' THEN cantidad ELSE 0 END) + " +
          "SUM(CASE WHEN tipo_movimiento = 'devolucion' THEN cantidad ELSE 0 END) + " +
          "SUM(CASE WHEN tipo_movimiento = 'devolusado' THEN cantidad ELSE 0 END) AS stock " +
          "FROM ropa_movimiento " +
          "WHERE nuevo = :nuevo " +
          "GROUP BY   id_prenda, talle " +
          "having stock > 0 AND id_prenda = :idPrenda AND talle = :talle" +
          ") as subconsulta " +
          "where stock > 0",
          nativeQuery = true)
  public Integer stockPorTalle(@Param("idPrenda") Integer idPrenda, @Param("nuevo") Boolean nuevo, @Param("talle") String talle);


}
