package com.puertodeseado.EntidadesDTO.anticipoderetorno;

import com.puertodeseado.entidades.AsociadosMain;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


import java.time.YearMonth;

public class PrestamoDTO {

  private Boolean intSiNo;

  private Double interes;
  @NotNull(message = "*")
  private Double montoPrestamo;
  @NotNull(message = "*")
  private Integer cantidadCuotas;
  @NotNull(message = "*")
  private YearMonth primeraCuota;

  public PrestamoDTO() {
  }

  public PrestamoDTO(Boolean intSiNo, AsociadosMain asociadosMain, Double montoPrestamo, Double interes, Integer cantidadCuotas, YearMonth primeraCuota) {
    this.intSiNo = intSiNo;
    this.montoPrestamo = montoPrestamo;
    this.interes = interes;
    this.cantidadCuotas = cantidadCuotas;
    this.primeraCuota = primeraCuota;
  }

  public Boolean getIntSiNo() {
    return intSiNo;
  }

  public void setIntSiNo(Boolean intSiNo) {
    this.intSiNo = intSiNo;
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

  public Integer getCantidadCuotas() {
    return cantidadCuotas;
  }

  public void setCantidadCuotas(Integer cantidadCuotas) {
    this.cantidadCuotas = cantidadCuotas;
  }

  public YearMonth getPrimeraCuota() {
    return primeraCuota;
  }

  public void setPrimeraCuota(YearMonth primeraCuota) {
    this.primeraCuota = primeraCuota;
  }
}
