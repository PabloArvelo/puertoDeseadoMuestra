package com.puertodeseado;

import com.puertodeseado.controladores.anticiporetorno.EditarLiquidacionControlador;
import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.repositorio.anticipoderetorno.AdelantoRepositorio;
import com.puertodeseado.repositorio.anticipoderetorno.LiquidacionHistorialRepositorio;
import com.puertodeseado.servicio.AsociadosMainServicio;
import com.puertodeseado.servicio.CrearExcelFileServicio;
import com.puertodeseado.servicio.anticiporetorno.AdelantoServicio;
import com.puertodeseado.servicio.anticiporetorno.EditarLiquidacionServicio;
import com.puertodeseado.servicio.anticiporetorno.LiquidacionHistorialServicio;
import com.puertodeseado.servicio.anticiporetorno.PrestamoServicio;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Date;
import java.util.List;


@SpringBootApplication
@EnableScheduling
public class PuerdoDeseadoApplication {

  @Autowired
  LiquidacionHistorialServicio liquidacionHistorialServicio;
  @Autowired
  CrearExcelFileServicio crearExcelFileServicio;

  @Autowired
  AdelantoServicio adelantoServicio;

  @Autowired
  AsociadosMainServicio asociadosMainServicio;

  @Autowired
  PrestamoServicio prestamoServicio;

  @Autowired
  EditarLiquidacionControlador editarLiquidacionControlador;


  @PersistenceContext
  private EntityManager entityManager;



  public static void main(String[] args) throws Exception {

    System.setProperty("com.sun.xml.bind.v2.bytecode.ClassTailor.noOptimize", "true");

    SpringApplication.run(PuerdoDeseadoApplication.class, args);


  }

  @PostConstruct
  public void init() throws Exception {

    //String miRuta = "C:/paBLO/zz Versiones/PuertoDeseado3/importarLiquidacion.csv";
    //String miRuta = "J:/Carrera Programación/zz Trabajos/PuertoDeseado3/importarLiquidacion.csv";

    //liquidacionHistorialServicio.importarLiquidacionDesdeCSV(miRuta);

    //crearExcelFileServicio.listadoAsociadosLiquidacion();


    // crearExcelFileServicio.listadoAsociadosLiquidacion();

    //liquidacionHistorialServicio.importarLiquidacionDesdeCSV(miRuta);


//    YearMonth primeraCuota = YearMonth.of(2025, 1);
    //AsociadosMain pablo = new AsociadosMain();
    //Long cuil = 20244482989L;
//    Double monto = 30000.00;
//    Boolean intSiNO = true;
//    Double interes = 25.0;
//    Integer cantCuotas = 5;
    //pablo = asociadosMainServicio.buscarAsocPorCuil(cuil);
//
//    prestamoServicio.asignarPrestamo(intSiNO,primeraCuota,pablo,cantCuotas,monto,interes);

//    YearMonth periodo = YearMonth.of(2024, 11);
    //String idAsoc = "73d0d5a5-472d-4e16-ac91-d750cdc50553";
//
//    List<Object[]> misAdelantos = adelantoServicio.mostrarAdelantosADescontar(idAsoc);
//    System.out.println("hola");

//    for (Object[] adelanto : misAdelantos){
//      String
//    }

    // prestamoServicio.mostrarPrestamosADescontar(pablo.getId());








//    String idAsoc = "787cdbbf-ee0d-4185-8f3c-b82bcc1c9660";
//    AsociadosMain asociado = asociadosMainServicio.getOne(idAsoc);

//    String idAsoc = "787cdbbf-ee0d-4185-8f3c-b82bcc1c9660";
//    AsociadosMain asociado = entityManager.find(AsociadosMain.class, idAsoc); // Carga completamente
//    String apellido = asociado.getApellido();
//    System.out.println(apellido);
//
    //YearMonth periodo = YearMonth.of(2024,11);
    //editarLiquidacionControlador.liquidacionesPorPeriodo(periodo);

//    String idAsoc = "5993ed22-f0bc-4266-9429-278e40a9f4ac";
//    YearMonth per = YearMonth.of(2024, 11);
//
//
//    editarLiquidacionControlador.buscarLiquidacionPorAsocPeriodo(idAsoc,per);






  }

}
