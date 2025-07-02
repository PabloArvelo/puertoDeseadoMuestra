package com.puertodeseado.servicio.anticiporetorno;

import com.puertodeseado.EntidadesDTO.RespuestaDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.AdelantosADescontarDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.PrestamoADescontarDTO;
import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.entidades.anticiporetorno.Prestamo;
import com.puertodeseado.repositorio.anticipoderetorno.LiquidacionHistorialRepositorio;
import com.puertodeseado.repositorio.anticipoderetorno.PrestamoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
public class PrestamoServicio {

  @Autowired
  private PrestamoRepositorio prestamoRepositorio;

  @Autowired
  private LiquidacionHistorialRepositorio liquidacionHistorialRepositorio;


  //CONTROLA EL FLUJO SI EL CALCULO SE HARÁ CON O SIN INTERES
  @Transactional
  public RespuestaDTO asignarPrestamo(Boolean intSiNo,
                                      YearMonth primeraCuota,
                                      AsociadosMain asociadosMain,
                                      Integer cantidadCuotas,
                                      Double montoPrestamo,
                                      Double interes) {

    // pregunta si tiene o no tiene interés
    if (intSiNo) {
      try {
        conInteres(primeraCuota, asociadosMain, cantidadCuotas, montoPrestamo, interes);

        return new RespuestaDTO(true,"Préstamo asignado con éxito");

      } catch (Exception e) {
        System.out.println("error al asignar prestamo con interes: " + e.getMessage());

        return new RespuestaDTO(false,"error al asignar préstamo");
      }

    } else {
      try {
        sinInteres(primeraCuota, asociadosMain, cantidadCuotas, montoPrestamo);

        return new RespuestaDTO(true,"Préstamo asignado con éxito");
      } catch (Exception e) {
        System.out.println("error al asignar prestamo sin interes: " + e.getMessage());

        return new RespuestaDTO(false,"error al asignar préstamo");
      }

    }

  }


  //CALCULA PRESTAMO SIN INTERES - el cálculo de la cuota lo hace en la clase
  @Transactional
  private void sinInteres(YearMonth primeraCuota,
                                 AsociadosMain asociadosMain,
                                 Integer cantidadCuotas,
                                 Double montoPrestamo) {

    int cuotaNumero = 1;
    String cuotaActualString = "";


    //el bucle tiene tantas vueltas como cantidad de cuotas
    for (int i = 0; i < cantidadCuotas; i++) {
      cuotaActualString = cuotaNumero + "-" + cantidadCuotas;

      Prestamo prestamo = new Prestamo(primeraCuota, asociadosMain, cantidadCuotas, montoPrestamo, cuotaActualString);


      prestamoRepositorio.save(prestamo);

      primeraCuota = primeraCuota.plusMonths(1);
      cuotaNumero++;

    }
  }


  //CALCULA PRESTAMO CON INTERES
  // asigna al monto total del prestamo, un interes de X%
  // luego divide el monto total con interes dividido el total de cuotas
  // ese resultado será el valor a descontar por mes
  @Transactional
  private void conInteres(YearMonth primeraCuota,
                                 AsociadosMain asociadosMain,
                                 Integer cantidadCuotas,
                                 Double montoPrestamo,
                                 Double interes) {

    int cuotaNumero = 1;
    String cuotaActualString = "";
    Double montoCuota = (((montoPrestamo * interes) / 100) + montoPrestamo) / cantidadCuotas;


    //el bucle tiene tantas vueltas como cantidad de cuotas
    for (int i = 0; i < cantidadCuotas; i++) {
      cuotaActualString = cuotaNumero + "-" + cantidadCuotas;

      Prestamo prestamo = new Prestamo(primeraCuota, asociadosMain, cantidadCuotas, montoPrestamo, interes, cuotaActualString, montoCuota);

      prestamoRepositorio.save(prestamo);

      primeraCuota = primeraCuota.plusMonths(1);
      cuotaNumero++;

    }
  }


  @Transactional
  public List<PrestamoADescontarDTO> mostrarPrestamosADescontar(String idAsoc){

    String periodoUltimaLiquidacion = liquidacionHistorialRepositorio.consultaUltimaLiquidacion();

    YearMonth periodoUltimaLiquidacionYM = YearMonth.parse(periodoUltimaLiquidacion);
    periodoUltimaLiquidacionYM = YearMonth.from(periodoUltimaLiquidacionYM.plusMonths(1));

    periodoUltimaLiquidacion = periodoUltimaLiquidacionYM.toString();

    List<Object[]> prestamosOBJ = prestamoRepositorio.mostrarPrestamosADescontar(idAsoc,periodoUltimaLiquidacion);


    List<PrestamoADescontarDTO> prestamos = new ArrayList<>();

    for (Object[] prestaOBJ : prestamosOBJ){
      PrestamoADescontarDTO prestamo = new PrestamoADescontarDTO();

      String cuotaActual = prestaOBJ[6].toString();
      Double monto = (Double) prestaOBJ[7];

      prestamo.setCuotaActual(cuotaActual);
      prestamo.setMonto(monto);

      prestamos.add(prestamo);
    }


    return prestamos;
  }




}
