package com.puertodeseado.repositorio.anticipoderetorno;

import com.puertodeseado.entidades.anticiporetorno.ImprimirRecibo;
import com.puertodeseado.entidades.anticiporetorno.LiquidacionHistorial;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImprimirRecibosRepositorio extends JpaRepository <ImprimirRecibo, Integer>{



}
