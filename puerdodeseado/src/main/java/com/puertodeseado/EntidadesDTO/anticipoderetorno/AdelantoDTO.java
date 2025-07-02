package com.puertodeseado.EntidadesDTO.anticipoderetorno;

import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.entidades.anticiporetorno.Adelanto;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.YearMonth;
import java.util.Date;

public class AdelantoDTO {

  private YearMonth descuentaEnLiquidacion;
  @NotEmpty(message = "debe asignar un monto")
  private Double monto;

  public AdelantoDTO() {
  }

  public AdelantoDTO(YearMonth descuentaEnLiquidacion, Double monto) {
    this.descuentaEnLiquidacion = descuentaEnLiquidacion;
    this.monto = monto;
  }

  public YearMonth getDescuentaEnLiquidacion() {
    return descuentaEnLiquidacion;
  }

  public void setDescuentaEnLiquidacion(YearMonth descuentaEnLiquidacion) {
    this.descuentaEnLiquidacion = descuentaEnLiquidacion;
  }

  public Double getMonto() {
    return monto;
  }

  public void setMonto(Double monto) {
    this.monto = monto;
  }
}
