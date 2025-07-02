package com.puertodeseado.servicio.anticiporetorno;

import com.puertodeseado.EntidadesDTO.RespuestaDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.AdelantosADescontarDTO;
import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.entidades.anticiporetorno.Adelanto;
import com.puertodeseado.repositorio.anticipoderetorno.AdelantoRepositorio;
import com.puertodeseado.repositorio.anticipoderetorno.LiquidacionHistorialRepositorio;
import com.puertodeseado.servicio.AsociadosMainServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AdelantoServicio {
  @Autowired
  private AdelantoRepositorio adelantoRepositorio;
  @Autowired
  private AsociadosMainServicio asociadosMainServicio;
  @Autowired
  private LiquidacionHistorialRepositorio liquidacionHistorialRepositorio;

  @Transactional
  public RespuestaDTO asignarAdelanto(YearMonth descuentaEnLiquidacionFutura,
                                      Double monto,
                                      String idAsoc){

    try {

      Adelanto adelanto = new Adelanto();

      if (descuentaEnLiquidacionFutura == null){
        // si no se seteó una fecha para el descuento, se decontará en la próxima liquidación
        LocalDate fechaActual = LocalDate.now();

        //la liquidación se toma a mes vencido, EJ: si estoy en noviembre, liquido octubre
        YearMonth descuentaEnLiqui = YearMonth.from(fechaActual);
        AsociadosMain asociado = asociadosMainServicio.getOne(idAsoc);
        adelanto = new Adelanto(descuentaEnLiqui,asociado,monto);
      }else {
        // si se seteó una fecha para el descuento, se decontará en laliquidación que se indicó
        AsociadosMain asociado = asociadosMainServicio.getOne(idAsoc);
        adelanto = new Adelanto(descuentaEnLiquidacionFutura,asociado,monto);
      }

      adelantoRepositorio.save(adelanto);

      return new RespuestaDTO(true,"adelanto asignado con éxito");

    }catch (Exception e){

      return new RespuestaDTO(false,"error al asignar adelanto");

    }
  }


  // encuentra los adelantos a descontar en la próxima liquidación de un asociado específico
  // los muestra en la vista de adelantos
  @Transactional
  public List<AdelantosADescontarDTO> mostrarAdelantosADescontar(String idAsoc){

    String periodoUltimaLiquidacion = liquidacionHistorialRepositorio.consultaUltimaLiquidacion();

    YearMonth periodoUltimaLiquidacionYM = YearMonth.parse(periodoUltimaLiquidacion);
    periodoUltimaLiquidacionYM = YearMonth.from(periodoUltimaLiquidacionYM.plusMonths(1));

    periodoUltimaLiquidacion = periodoUltimaLiquidacionYM.toString();

    List<Object[]> adelantosOBJ = adelantoRepositorio.mostrarAdelantosADescontar(idAsoc,periodoUltimaLiquidacion);

    List<AdelantosADescontarDTO> adelantos = new ArrayList<>();


    for (Object[] adelOBJ : adelantosOBJ){
      AdelantosADescontarDTO adelanto = new AdelantosADescontarDTO();

      String fechaOriginal = adelOBJ[2].toString();
      DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      LocalDate fecha = LocalDate.parse(fechaOriginal,formatoEntrada);
      DateTimeFormatter formatosalida = DateTimeFormatter.ofPattern("dd-MM-yyyy");
      String fechaFormateada = fecha.format(formatosalida);
      adelanto.setFechaEntregado(fechaFormateada);


      Double monto = (Double) adelOBJ[4];
      adelanto.setMonto(monto);

      adelantos.add(adelanto);

    }



    return adelantos;
  }

}
