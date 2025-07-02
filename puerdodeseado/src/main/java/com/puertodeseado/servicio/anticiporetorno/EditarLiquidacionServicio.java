package com.puertodeseado.servicio.anticiporetorno;


import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.puertodeseado.EntidadesDTO.RespuestaDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.LiquidacionActualizarDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.LiquidacionesPorPeriodoDTO;
import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.entidades.anticiporetorno.LiquidacionHistorial;
import com.puertodeseado.repositorio.AsociadosMainRepositorio;
import com.puertodeseado.repositorio.anticipoderetorno.LiquidacionHistorialRepositorio;
import com.puertodeseado.servicio.AsociadosMainServicio;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EditarLiquidacionServicio {

  @Autowired
  private LiquidacionHistorialRepositorio liquidacionHistorialRepositorio;

  @Autowired
  private AsociadosMainServicio asociadosMainServicio;

  @Autowired
  private AsociadosMainRepositorio asociadosMainRepositorio;




  @Transactional
  public List<LiquidacionesPorPeriodoDTO> liquidacionesPorPeriodo(YearMonth periodo) {

    String periodoString = periodo.toString();

    List<Object[]> listaLiquidacionesPorPeriodo = liquidacionHistorialRepositorio.buscarLiquidacionPorPeriodo(periodoString);

    List<LiquidacionesPorPeriodoDTO> listaLiquidacionesDTO = new ArrayList<>();


    for (Object[] liquidacion : listaLiquidacionesPorPeriodo) {

      String idAsoc = (String) liquidacion[1];

      AsociadosMain asociado = asociadosMainRepositorio.getReferenceById(idAsoc);

      LiquidacionesPorPeriodoDTO liquiPorPeriodoDTO = new LiquidacionesPorPeriodoDTO();

      liquiPorPeriodoDTO.setIdAsoc(idAsoc);
      liquiPorPeriodoDTO.setIdLiquidacion((String) liquidacion[0]);

      liquiPorPeriodoDTO.setApellido(asociado.getApellido());
      liquiPorPeriodoDTO.setNombre(asociado.getNombre());
      liquiPorPeriodoDTO.setCuil(asociado.getCuil());

      liquiPorPeriodoDTO.setTotalAnticipo((BigDecimal) liquidacion[2]);

      listaLiquidacionesDTO.add(liquiPorPeriodoDTO);
    }
    return listaLiquidacionesDTO;
  }

  @Transactional
  public LiquidacionHistorial buscarLiquidacionPorAsocPeriodo(String idAsoc, String periodo) {

    LiquidacionHistorial resultado = liquidacionHistorialRepositorio.buscarLiquidacionPorAsocPeriodo(idAsoc, periodo);
    //System.out.println("EditarLiquidacionServicio buscarLiquidacionPorAsocPeriodo " + resultado.getHoras6());

    return resultado;
  }




  @Transactional
  public RespuestaDTO editarLiquidacion(LiquidacionActualizarDTO liqAtcDTO) {

    try {
      Optional<AsociadosMain> asociadoOPT = asociadosMainRepositorio.findById(liqAtcDTO.getIdAsociado());
      AsociadosMain asociado = asociadoOPT.get();

      Optional<LiquidacionHistorial> liquiAntesdeActualizar = liquidacionHistorialRepositorio.findById(liqAtcDTO.getIdLiquidacion());


      if (liquiAntesdeActualizar.isPresent()){
        int anticipoNumero = liquiAntesdeActualizar.get().getAnticipoNumero();



        // creo una nueva instancia de liquidacionHistorial y le paso los datos dl dto para que se autocalcule
        LiquidacionHistorial liquiActualizada = new LiquidacionHistorial(
                liqAtcDTO.getHoras1(), liqAtcDTO.getMinutos1(), liqAtcDTO.getValor_hora1(),liqAtcDTO.getObjetivo1(),
                liqAtcDTO.getHoras2(), liqAtcDTO.getMinutos2(), liqAtcDTO.getValor_hora2(),liqAtcDTO.getObjetivo2(),
                liqAtcDTO.getHoras3(), liqAtcDTO.getMinutos3(), liqAtcDTO.getValor_hora3(),liqAtcDTO.getObjetivo3(),
                liqAtcDTO.getHoras4(), liqAtcDTO.getMinutos4(), liqAtcDTO.getValor_hora4(),liqAtcDTO.getObjetivo4(),
                liqAtcDTO.getHoras5(), liqAtcDTO.getMinutos5(), liqAtcDTO.getValor_hora5(), liqAtcDTO.getObjetivo5(),
                liqAtcDTO.getHoras6(), liqAtcDTO.getMinutos6(), liqAtcDTO.getValor_hora6(), liqAtcDTO.getObjetivo6(),
                liqAtcDTO.getHoras7(), liqAtcDTO.getMinutos7(), liqAtcDTO.getValor_hora7(), liqAtcDTO.getObjetivo7(),
                liqAtcDTO.getHoras8(), liqAtcDTO.getMinutos8(), liqAtcDTO.getValor_hora8(),liqAtcDTO.getObjetivo8(),
                liqAtcDTO.getHoras9(), liqAtcDTO.getMinutos9(), liqAtcDTO.getValor_hora9(),liqAtcDTO.getObjetivo9(),
                liqAtcDTO.getHoras10(), liqAtcDTO.getMinutos10(), liqAtcDTO.getValor_hora10(), liqAtcDTO.getObjetivo10(),
                liqAtcDTO.getPresentismo(), liqAtcDTO.getNocturnidad(), liqAtcDTO.getNocturnidad_eventual(),
                liqAtcDTO.getBonificacion_feriados(), liqAtcDTO.getReconocimiento_horas(), liqAtcDTO.getCarpeta_medica(),
                liqAtcDTO.getHs_vacaciones(), liqAtcDTO.getHoras_adeudadas(), liqAtcDTO.getLicencia_maternidad(),
                liqAtcDTO.getHoras_practica(), liqAtcDTO.getReintegro_cuotas_sociales(), liqAtcDTO.getCuota_social(),
                liqAtcDTO.getSeguro_acc_per_vida(), liqAtcDTO.getReten_monotri_per(), liqAtcDTO.getSeguro_vida_oblig(),
                liqAtcDTO.getAdelanto(), liqAtcDTO.getPrestamo(), liqAtcDTO.getInteres(), liqAtcDTO.getCuota_actual(),
                liqAtcDTO.getCosto_habilitacion(), liqAtcDTO.getApto_psi_fi(), liqAtcDTO.getCredisolcs(),
                liqAtcDTO.getCredisolcc(), liqAtcDTO.getCreditogupaserviciosas(), liqAtcDTO.getAlmacen(),
                liqAtcDTO.getEmbargo_judicial(), liqAtcDTO.getDeposito_exceso(), liqAtcDTO.getDescuento_rotura_perdida(),
                liqAtcDTO.getCalzado(), liqAtcDTO.getAdherente_monotributo(), liqAtcDTO.getPeriodo(),asociado);

        // seteo el ID de liquidacion
        liquiActualizada.setId(liqAtcDTO.getIdLiquidacion());
        liquiActualizada.setAprobada(true);
        liquiActualizada.setAnticipoNumero(anticipoNumero);


        liquidacionHistorialRepositorio.save(liquiActualizada);

      }

      return new RespuestaDTO(true, "liquidación actualizada con exito");


    }catch (Exception e){
      return new RespuestaDTO(false, "error al actualizar liquidación");
    }

  }


  private void actualizarCampos(LiquidacionHistorial liquiEditadaHistorial, LiquidacionActualizarDTO liqAtcDTO) {
    // Utilizamos reflexión para asignar valores automáticamente
    Field[] fieldsDTO = liqAtcDTO.getClass().getDeclaredFields();
    Field[] fieldsEntity = liquiEditadaHistorial.getClass().getDeclaredFields();

    for (Field fieldDTO : fieldsDTO) {
      fieldDTO.setAccessible(true);
      for (Field fieldEntity : fieldsEntity) {
        if (fieldEntity.getName().equals(fieldDTO.getName())) {
          fieldEntity.setAccessible(true);
          try {
            fieldEntity.set(liquiEditadaHistorial, fieldDTO.get(liqAtcDTO));
          } catch (IllegalAccessException e) {
            throw new RuntimeException("Error al actualizar los campos", e);
          }
        }
      }
    }
  }


}
