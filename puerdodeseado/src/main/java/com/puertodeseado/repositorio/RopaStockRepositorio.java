package com.puertodeseado.repositorio;

import com.puertodeseado.EntidadesDTO.RopaStockDTO;
import com.puertodeseado.entidades.RopaMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

  public interface RopaStockRepositorio extends JpaRepository <RopaMovimiento, Integer> {

    @Query(value = "SELECT id_prenda, talle, nuevo," +
            "SUM(CASE WHEN tipo_movimiento = 'compra' THEN cantidad ELSE 0 END) - " +
            "SUM(CASE WHEN tipo_movimiento = 'entrega' THEN cantidad ELSE 0 END) + " +
            "SUM(CASE WHEN tipo_movimiento = 'devolucion' THEN cantidad ELSE 0 END) + " +
            "SUM(CASE WHEN tipo_movimiento = 'devolusado' THEN cantidad ELSE 0 END) AS stock " +
            "FROM ropa_movimiento " +
            "GROUP BY   id_prenda, talle, nuevo " +
            "HAVING stock > 0 " +
            "ORDER BY id_prenda, talle, nuevo DESC;",
            nativeQuery = true)
    public List<RopaStockDTO> listarRopaStock();
  }
