package com.puertodeseado.servicio.anticiporetorno;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.puertodeseado.EntidadesDTO.RespuestaDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.CrearLiquidacionDTO;
import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.entidades.anticiporetorno.LiquidacionHistorial;
import com.puertodeseado.repositorio.AsociadosMainRepositorio;
import com.puertodeseado.repositorio.anticipoderetorno.AdelantoRepositorio;
import com.puertodeseado.repositorio.anticipoderetorno.LiquidacionHistorialRepositorio;
import com.puertodeseado.repositorio.anticipoderetorno.PrestamoRepositorio;
import jakarta.mail.Multipart;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.*;

@Service
public class LiquidacionHistorialServicio {


  @Autowired
  private LiquidacionHistorialRepositorio liquidacionHistorialRepositorio;

  @Autowired
  private AdelantoRepositorio adelantoRepositorio;

  @Autowired
  private AsociadosMainRepositorio asociadosMainRepositorio;

  @Autowired
  private PrestamoRepositorio prestamoRepositorio;


  @Transactional
  public RespuestaDTO importarLiquidacionDesdeCSV(YearMonth periodo, MultipartFile archivoCSV) {


    // obtengo el período a liquidar
    String periodoString = periodo.toString();

    String ultimaLiquidacion = liquidacionHistorialRepositorio.consultaUltimaLiquidacion();


    // valido que no se vuelva a liquidar el mismo período
    if (periodoString.equals(ultimaLiquidacion)){
      return new RespuestaDTO(false, "el período "+periodoString+ " ya fué liquidado");
    }


    // valido que no se cargue una liquidación anterior al último período liquidado
    YearMonth ultimaLiquidacionYM = YearMonth.parse(ultimaLiquidacion);

    if (periodo.isBefore(ultimaLiquidacionYM)){
      return new RespuestaDTO(false, "el período "+periodoString+ " es anterior al último período liquidado");
    }


    // aquí guardaré la lista de todos los asociados
    List<AsociadosMain> listaAsociadosAlta = new ArrayList<>();

    // aquí guardaré la lista de los asociados que tienen adelantos.
    List<Object[]> listaAdelantos =  new ArrayList<>();

    // aquí guardaré la lista de los asociados que tienen prestamos.
    List<Object[]> listaPrestamos = new ArrayList<>();

    int filaNumero = 0;


    try (Reader reader = new InputStreamReader(archivoCSV.getInputStream(), StandardCharsets.UTF_8)) {


      listaAsociadosAlta = asociadosMainRepositorio.listaAsociadosAlta();

      // obtengo la lista de los asociados que tienen adelantos.
      listaAdelantos = adelantoRepositorio.buscarAdelantos(periodoString);

      // obtengo la lista de los asociados que tienen prestamos.
      listaPrestamos = prestamoRepositorio.buscarPrestamo(periodoString);

      CSVParser parser = new CSVParserBuilder().withSeparator(';').build(); // PD
      //CSVParser parser = new CSVParserBuilder().withSeparator(',').build(); // CASA
      CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).withSkipLines(1).build();


      //Creo y lleno la lista donde se almacenará el contenido del CSV
      List<String[]> liquidaciones = new ArrayList<>();
      liquidaciones = csvReader.readAll();


      // creo la lista que se recorrerá al final con las liquidaciones que voy a persistir
      List<LiquidacionHistorial> listaLiquidacionesHistEnviar = new ArrayList<>();





      salir:
      for (String[] liquidacion : liquidaciones) {  // recorro cada fila del CSV

        if (!liquidacion[0].isEmpty()) {  // evaluo que la fila exista (apunto al campo 0 de la primer fila)

          for (int j = 0; j < liquidacion.length; j++) {  // recorro cada campo de la fila

            String[] fila = liquidacion;
            if (fila[j].isEmpty()) {  // evaluo que no exitan campos vacios
              System.out.println("error en fila " + (filaNumero + 1));
              listaLiquidacionesHistEnviar = null;

              break salir;
            }
          }
          AsociadosMain asociado = new AsociadosMain();

          // ---------------------------------------------------
          // -------------  CONSUME DESDE EL CSV  --------------
          // ---------------------------------------------------

          //acá podría usar un findByID     para buscar el asociado que voy a ir liquidando y agregando a la lista listaLiquidacionesHistEnviar
          for (AsociadosMain asoc : listaAsociadosAlta) {
            if (asoc.getId().equals(liquidacion[0])) {
              asociado = asoc;
            }
          }

          // asigno los valores a las variables  | esto tambien podría hacerlo con un FOR

          int horas1 = Integer.parseInt(liquidacion[4]);
          int minutos1 = Integer.parseInt(liquidacion[5]);
          BigDecimal valorHora1 = new BigDecimal(liquidacion[6]);
          int objetivo1 = Integer.parseInt(liquidacion[7]);

          int horas2 = Integer.parseInt(liquidacion[8]);
          int minutos2 = Integer.parseInt(liquidacion[9]);
          BigDecimal valorHora2 = new BigDecimal(liquidacion[10]);
          int objetivo2 = Integer.parseInt(liquidacion[11]);

          int horas3 = Integer.parseInt(liquidacion[12]);
          int minutos3 = Integer.parseInt(liquidacion[13]);
          BigDecimal valorHora3 = new BigDecimal(liquidacion[14]);
          int objetivo3 = Integer.parseInt(liquidacion[15]);

          int horas4 = Integer.parseInt(liquidacion[16]);
          int minutos4 = Integer.parseInt(liquidacion[17]);
          BigDecimal valorHora4 = new BigDecimal(liquidacion[18]);
          int objetivo4 = Integer.parseInt(liquidacion[19]);

          int horas5 = Integer.parseInt(liquidacion[20]);
          int minutos5 = Integer.parseInt(liquidacion[21]);
          BigDecimal valorHora5 = new BigDecimal(liquidacion[22]);
          int objetivo5 = Integer.parseInt(liquidacion[23]);

          int horas6 = Integer.parseInt(liquidacion[24]);
          int minutos6 = Integer.parseInt(liquidacion[25]);
          BigDecimal valorHora6 = new BigDecimal(liquidacion[26]);
          int objetivo6 = Integer.parseInt(liquidacion[27]);

          int horas7 = Integer.parseInt(liquidacion[28]);
          int minutos7 = Integer.parseInt(liquidacion[29]);
          BigDecimal valorHora7 = new BigDecimal(liquidacion[30]);
          int objetivo7 = Integer.parseInt(liquidacion[31]);

          int horas8 = Integer.parseInt(liquidacion[32]);
          int minutos8 = Integer.parseInt(liquidacion[33]);
          BigDecimal valorHora8 = new BigDecimal(liquidacion[34]);
          int objetivo8 = Integer.parseInt(liquidacion[35]);

          int horas9 = Integer.parseInt(liquidacion[36]);
          int minutos9 = Integer.parseInt(liquidacion[37]);
          BigDecimal valorHora9 = new BigDecimal(liquidacion[38]);
          int objetivo9 = Integer.parseInt(liquidacion[39]);

          int horas10 = Integer.parseInt(liquidacion[40]);
          int minutos10 = Integer.parseInt(liquidacion[41]);
          BigDecimal valorHora10 = new BigDecimal(liquidacion[42]);
          int objetivo10 = Integer.parseInt(liquidacion[43]);

          Double presentismo = Double.valueOf(liquidacion[44]);
          Double nocturnidad = Double.valueOf(liquidacion[45]);
          Double nocturnidadEventual = Double.valueOf(liquidacion[46]);
          Double bonificacionFeriados = Double.valueOf(liquidacion[47]);
          Double reconocimientoHoras = Double.valueOf(liquidacion[48]);
          Double carpetaMedica = Double.valueOf(liquidacion[49]);
          Double hsVacaciones = Double.valueOf(liquidacion[50]);
          Double horasAdeudadas = Double.valueOf(liquidacion[51]);
          Double licienciaMaternidad = Double.valueOf(liquidacion[52]);
          Double horasPractica = Double.valueOf(liquidacion[53]);
          Double reintegroCuotasSociales = Double.valueOf(liquidacion[54]);
          Double seguroAccPer_Vida = Double.valueOf(liquidacion[55]);
          Double retenMonotriPer = Double.valueOf(liquidacion[56]);
          Double seguroVidaOblig = Double.valueOf(liquidacion[57]);
          Double costoHabilitacion = Double.valueOf(liquidacion[58]);
          Double aptoPsiFi = Double.valueOf(liquidacion[59]);
          Double credisolCS = Double.valueOf(liquidacion[60]);
          Double credisolCC = Double.valueOf(liquidacion[61]);
          Double creditoGUPAServicioSAS = Double.valueOf(liquidacion[62]);
          Double almacen = Double.valueOf(liquidacion[63]);
          Double embargoJudicial = Double.valueOf(liquidacion[64]);
          Double depositoExceso = Double.valueOf(liquidacion[65]);
          Double descuentoRoturaPerdida = Double.valueOf(liquidacion[66]);
          Double calzado = Double.valueOf(liquidacion[67]);
          Double adherenteMonotributo = Double.valueOf(liquidacion[68]);


          // ---------------------------------------------------
          // -------------  CONSUME DESDE LA DDBB --------------
          // ---------------------------------------------------


          //                         ----------  adelantos --------------

          Double adelanto = 0.00;

          // recorro la lista de los adelantos y si el id corresponde con el asociado de turno, se lo asigno
          for (Object[] res : listaAdelantos) {
            String idAsoc = (String) res[0];

            if (asociado.getId().equals(idAsoc)) {
              adelanto = (Double) res[1];
            }
          }


          //                         ----------  prestamos --------------
          Double prestamo = 0.00;
          double interes = 0.00;
          String cuotaActual = "";

          // recorro la lista de los prestamos y si el id corresponde con el asociado de turno, se lo asigno
          for (Object[] res : listaPrestamos) {
            String idAsoc = (String) res[1];

            if (asociado.getId().equals(idAsoc)) {
              prestamo = (Double) res[7];
              interes = (Double) res[4];
              cuotaActual = (String) res[6];
            }
          }


          //                         ----------  Cuota Social --------------

          Double cuotaSocial = null;

          Date fechaIngresoDate = asociado.getFechaIngreso();

          LocalDate fechaIngreso = LocalDate.ofEpochDay(fechaIngresoDate.getTime() / 86400000);

          //convierto el príodo a LocalDate
          LocalDate fechaActual = periodo.atDay(1);

          Long periodosEntre = ChronoUnit.MONTHS.between(fechaIngreso.withDayOfMonth(1), fechaActual.withDayOfMonth(1)) + 1;

          // evalúo si integró la cantidad de cuotas sociales (60 cuotas) para detener el descuento
          if (periodosEntre > 60) {
            cuotaSocial = 0.00;
          } else {
            cuotaSocial = asociado.getCuotaSocial();
          }


          // ------------------------------------------------------------
          // -------------  instancio LiquidacionHistorial --------------
          // ------------------------------------------------------------


          LiquidacionHistorial miLiquiH = new LiquidacionHistorial(horas1, minutos1, valorHora1, objetivo1,
                  horas2, minutos2, valorHora2, objetivo2,
                  horas3, minutos3, valorHora3, objetivo3,
                  horas4, minutos4, valorHora4, objetivo4,
                  horas5, minutos5, valorHora5, objetivo5,
                  horas6, minutos6, valorHora6, objetivo6,
                  horas7, minutos7, valorHora7, objetivo7,
                  horas8, minutos8, valorHora8, objetivo8,
                  horas9, minutos9, valorHora9, objetivo9,
                  horas10, minutos10, valorHora10, objetivo10,
                  presentismo, nocturnidad, nocturnidadEventual,
                  bonificacionFeriados, reconocimientoHoras, carpetaMedica,
                  hsVacaciones, horasAdeudadas, licienciaMaternidad,
                  horasPractica, reintegroCuotasSociales,
                  cuotaSocial,            // por tabla
                  seguroAccPer_Vida, retenMonotriPer, seguroVidaOblig,
                  adelanto,             // por tabla
                  prestamo,             // por tabla
                  interes, cuotaActual, costoHabilitacion,
                  aptoPsiFi, credisolCS, credisolCC,
                  creditoGUPAServicioSAS, almacen, embargoJudicial,
                  depositoExceso, descuentoRoturaPerdida, calzado,
                  adherenteMonotributo, periodo, asociado);

          // la agrego a la lista
          listaLiquidacionesHistEnviar.add(miLiquiH);

        }

        filaNumero++;
      }


      // si la lisa no está vacía, persisto en base de datos
      // tengo que hacer esta comprobación, porque si dió error y salio del bucle anterior con el break salir:
      // listaLiquidacionesHistEnviar será nulo y no persistirá nada en DB
      if (listaLiquidacionesHistEnviar !=  null) {

        // aca comparo que la cantidad de la lista de liquidaciones a enviar sea igual
        // que la cantidad de asociados de alta
        if (listaLiquidacionesHistEnviar.size()==listaAsociadosAlta.size()){
          for (LiquidacionHistorial liquiEnviar : listaLiquidacionesHistEnviar) {
            System.out.println("hola");
            liquidacionHistorialRepositorio.save(liquiEnviar);
          }
          return new RespuestaDTO(true, "liquidación creada con éxito");
        }else {
          return new RespuestaDTO(false, "la cantidad de asociados no coincide:<br>" +
                  "- Padron asociados activos en Base de Datos: "+ listaAsociadosAlta.size()+"<br>" +
                  "- Cantidad de liquidaciones importadas: "+ listaLiquidacionesHistEnviar.size()+"<br>" +
                  "Revisar CSV.");
        }
      }

      return new RespuestaDTO(false, "existen campos vacios en el CSV<br>" +
              "revisar fila "+(filaNumero+1));


    } catch (Exception e) {
      return new RespuestaDTO(false, "error al crear liquidación " + e.getMessage());
    }
  }
}




