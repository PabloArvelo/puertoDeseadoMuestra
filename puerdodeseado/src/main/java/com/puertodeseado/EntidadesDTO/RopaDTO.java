package com.puertodeseado.EntidadesDTO;

import jakarta.validation.constraints.NotEmpty;

public class RopaDTO {

  @NotEmpty(message = "dato obligatorio")
  private String prenda;

  public RopaDTO() {
  }

  public String getPrenda() {
    return prenda;
  }

  public void setPrenda(String prenda) {
    this.prenda = prenda;
  }
}
