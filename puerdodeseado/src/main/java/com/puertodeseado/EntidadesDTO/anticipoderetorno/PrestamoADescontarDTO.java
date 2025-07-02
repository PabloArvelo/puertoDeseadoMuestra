package com.puertodeseado.EntidadesDTO.anticipoderetorno;

public class PrestamoADescontarDTO {

  private String cuotaActual;
  private Double monto;

  public PrestamoADescontarDTO() {
  }

  public PrestamoADescontarDTO(String cuotaActual, Double monto) {
    this.cuotaActual = cuotaActual;
    this.monto = monto;
  }

  public String getCuotaActual() {
    return cuotaActual;
  }

  public void setCuotaActual(String cuotaActual) {
    this.cuotaActual = cuotaActual;
  }

  public Double getMonto() {
    return monto;
  }

  public void setMonto(Double monto) {
    this.monto = monto;
  }
}
