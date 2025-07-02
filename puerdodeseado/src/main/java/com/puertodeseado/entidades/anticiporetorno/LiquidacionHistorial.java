package com.puertodeseado.entidades.anticiporetorno;

import com.puertodeseado.clases.anticipoderetorno.Liquidacion;
import com.puertodeseado.entidades.AsociadosMain;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class LiquidacionHistorial extends Liquidacion {

  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;


  @Column(nullable = false, unique = true) // Garantiza unicidad a nivel de la base de datos.
  private int anticipoNumero;

  @Column(name = "periodo", columnDefinition = "VARCHAR(7)")
  @Convert(converter = LiquiHistYearMonthConverter.class)
  private YearMonth periodo;


  @ManyToOne
  @JoinColumn(name = "id_asociado")
  private AsociadosMain asociadosMain;




  //atributos no heredados préstamo
  private Double interes;
  @Column(name = "cuota_actual")
  private String cuotaActual;


  private Boolean impreso = false;

  private Boolean aprobada = false;




  public LiquidacionHistorial() {
  }

  public LiquidacionHistorial(int horas1, int minutos1, BigDecimal valorHora1, int objetivo1,
                              int horas2, int minutos2, BigDecimal valorHora2, int objetivo2,
                              int horas3, int minutos3, BigDecimal valorHora3, int objetivo3,
                              int horas4, int minutos4, BigDecimal valorHora4, int objetivo4,
                              int horas5, int minutos5, BigDecimal valorHora5, int objetivo5,
                              int horas6, int minutos6, BigDecimal valorHora6, int objetivo6,
                              int horas7, int minutos7, BigDecimal valorHora7, int objetivo7,
                              int horas8, int minutos8, BigDecimal valorHora8, int objetivo8,
                              int horas9, int minutos9, BigDecimal valorHora9, int objetivo9,
                              int horas10, int minutos10, BigDecimal valorHora10, int objetivo10,
                              double presentismo,
                              double nocturnidad,
                              double nocturnidadEventual,
                              double bonificacionFeriados,
                              double reconocimientoHoras,
                              double carpetaMedica,
                              double hsVacaciones,
                              double horasAdeudadas,
                              double licenciaMaternidad,
                              double horasPractica,
                              double reintegroCuotasSociales,
                              double cuotaSocial,
                              double seguroAccPer_Vida,
                              double retenMonotriPer,
                              double seguroVidaOblig,
                              double adelanto,
                              double prestamo,
                              double interes,
                              String cuotaActual,
                              double costoHabilitacion,
                              double aptoPsiFi,
                              double credisolCS,
                              double credisolCC,
                              double creditoGUPAServicioSAS,
                              double almacen,
                              double embargoJudicial,
                              double depositoExceso,
                              double descuentoRoturaPerdida,
                              double calzado,
                              double adherenteMonotributo,
                              YearMonth periodo,
                              AsociadosMain asociadosMain) {

    super(horas1, minutos1, valorHora1, objetivo1,
            horas2, minutos2, valorHora2, objetivo2,
            horas3, minutos3, valorHora3, objetivo3,
            horas4, minutos4, valorHora4, objetivo4,
            horas5, minutos5, valorHora5, objetivo5,
            horas6, minutos6, valorHora6, objetivo6,
            horas7, minutos7, valorHora7, objetivo7,
            horas8, minutos8, valorHora8, objetivo8,
            horas9, minutos9, valorHora9, objetivo9,
            horas10, minutos10, valorHora10, objetivo10,
            presentismo,
            nocturnidad,
            nocturnidadEventual,
            bonificacionFeriados,
            reconocimientoHoras,
            carpetaMedica,
            hsVacaciones,
            horasAdeudadas,
            licenciaMaternidad,
            horasPractica,
            reintegroCuotasSociales,
            cuotaSocial,         // por tabla
            seguroAccPer_Vida,
            retenMonotriPer,
            seguroVidaOblig,
            adelanto,            // por tabla
            prestamo,            // por tabla
            costoHabilitacion,
            aptoPsiFi,
            credisolCS,            // por csv
            credisolCC,            // por csv
            creditoGUPAServicioSAS,            // por csv
            almacen,
            embargoJudicial,
            depositoExceso,
            descuentoRoturaPerdida,
            calzado,
            adherenteMonotributo);

    this.periodo = periodo;
    this.asociadosMain = asociadosMain;
    this.interes = interes;
    this.cuotaActual = cuotaActual;
  }



  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public int getAnticipoNumero() {
    return anticipoNumero;
  }

  public void setAnticipoNumero(int anticipoNumero) {
    this.anticipoNumero = anticipoNumero;
  }

  public YearMonth getPeriodo() {
    return periodo;
  }

  public void setPeriodo(YearMonth periodo) {
    this.periodo = periodo;
  }

  public AsociadosMain getAsociadosMain() {
    return asociadosMain;
  }

  public void setAsociadosMain(AsociadosMain asociadosMain) {
    this.asociadosMain = asociadosMain;
  }



  //atributos no heredados préstamo
  public Double getInteres() {
    return interes;
  }

  public void setInteres(Double interes) {
    this.interes = interes;
  }

  public String getCuotaActual() {
    return cuotaActual;
  }

  public void setCuotaActual(String cuotaActual) {
    this.cuotaActual = cuotaActual;
  }

  public Boolean getImpreso() {
    return impreso;
  }

  public void setImpreso(Boolean impreso) {
    this.impreso = impreso;
  }

  public void setAprobada(Boolean aprobada) {
    this.aprobada = aprobada;
  }

  //  convierte de manera automática el yearmonth a string
  @Converter(autoApply = true)
  public static class LiquiHistYearMonthConverter implements AttributeConverter<YearMonth, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

    @Override
    public String convertToDatabaseColumn(YearMonth yearMonth) {
      return (yearMonth != null) ? yearMonth.format(FORMATTER) : null;
    }

    @Override
    public YearMonth convertToEntityAttribute(String dbData) {
      return (dbData != null) ? YearMonth.parse(dbData, FORMATTER) : null;
    }
  }


}
