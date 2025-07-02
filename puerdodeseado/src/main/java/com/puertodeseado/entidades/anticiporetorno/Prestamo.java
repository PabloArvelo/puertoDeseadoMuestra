package com.puertodeseado.entidades.anticiporetorno;

import com.puertodeseado.entidades.AsociadosMain;
import jakarta.persistence.*;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Entity
public class Prestamo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "primera_cuota", columnDefinition = "VARCHAR(7)")
  @Convert(converter = Prestamo.PrestamoYearMonthConverter.class)
  private YearMonth primeraCuota;

  @ManyToOne
  @JoinColumn(name = "id_asociado")
  private AsociadosMain asociadosMain;

  private Integer cantidadCuotas;

  private Double montoPrestamo;
  private Double interes;

  private String cuotaActual;

  private Double montoCuota;

  public Prestamo() {
  }

  public Prestamo(YearMonth primeraCuota, AsociadosMain asociadosMain, Integer cantidadCuotas, Double montoPrestamo, String cuotaActual) {
    this.primeraCuota = primeraCuota;
    this.asociadosMain = asociadosMain;
    this.cantidadCuotas = cantidadCuotas;
    this.montoPrestamo = montoPrestamo;
    this.interes = 0.0;
    this.cuotaActual = cuotaActual;
    this.montoCuota = montoPrestamo/cantidadCuotas;
  }

  public Prestamo(YearMonth primeraCuota, AsociadosMain asociadosMain, Integer cantidadCuotas, Double montoPrestamo, Double interes, String cuotaActual, Double montoCuota) {
    this.primeraCuota = primeraCuota;
    this.asociadosMain = asociadosMain;
    this.cantidadCuotas = cantidadCuotas;
    this.montoPrestamo = montoPrestamo;
    this.interes = interes;
    this.cuotaActual = cuotaActual;
    this.montoCuota = montoCuota;
  }


  public YearMonth getPrimeraCuota() {
    return primeraCuota;
  }

  public void setPrimeraCuota(YearMonth primeraCuota) {
    this.primeraCuota = primeraCuota;
  }

  public AsociadosMain getAsociadosMain() {
    return asociadosMain;
  }

  public void setAsociadosMain(AsociadosMain asociadosMain) {
    this.asociadosMain = asociadosMain;
  }

  public Integer getCantidadCuotas() {
    return cantidadCuotas;
  }

  public void setCantidadCuotas(Integer cantidadCuotas) {
    this.cantidadCuotas = cantidadCuotas;
  }

  public Double getMontoPrestamo() {
    return montoPrestamo;
  }

  public void setMontoPrestamo(Double montoPrestamo) {
    this.montoPrestamo = montoPrestamo;
  }

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

  public Double getMontoCuota() {
    return montoCuota;
  }

  public void setMontoCuota(Double montoCuota) {
    this.montoCuota = montoCuota;
  }

  @Converter(autoApply = true)
  public static class PrestamoYearMonthConverter implements AttributeConverter<YearMonth, String> {

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
