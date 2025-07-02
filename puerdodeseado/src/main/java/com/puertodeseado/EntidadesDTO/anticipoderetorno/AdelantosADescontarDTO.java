package com.puertodeseado.EntidadesDTO.anticipoderetorno;

public class AdelantosADescontarDTO {

  private String fechaEntregado;
  private Double monto;

  public AdelantosADescontarDTO() {
  }

  public AdelantosADescontarDTO(String fechaEntregado, Double monto) {
    this.fechaEntregado = fechaEntregado;
    this.monto = monto;
  }

  public String getFechaEntregado() {
    return fechaEntregado;
  }

  public void setFechaEntregado(String fechaEntregado) {
    this.fechaEntregado = fechaEntregado;
  }

  public Double getMonto() {
    return monto;
  }

  public void setMonto(Double monto) {
    this.monto = monto;
  }
}
