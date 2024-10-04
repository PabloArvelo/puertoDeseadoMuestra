package com.puertodeseado.EntidadesDTO;

import jakarta.validation.constraints.NotEmpty;

public class RopaTalleDTO {

  @NotEmpty(message = "dato obligatorio")
  private String talle;

  public RopaTalleDTO() {
  }

  public String getTalle() {
    return talle;
  }

  public void setTalle(String talle) {
    this.talle = talle;
  }
}
