package com.puertodeseado.EntidadesDTO;

import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class AsociadoHabilitacionDTO {
  @NotNull(message = "agregue una fecha")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date habilitacion;

  public AsociadoHabilitacionDTO() {
  }

  public Date getHabilitacion() {
    return habilitacion;
  }

  public void setHabilitacion(Date habilitacion) {
    this.habilitacion = habilitacion;
  }
}
