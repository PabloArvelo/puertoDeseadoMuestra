package com.puertodeseado.EntidadesDTO;

public class EstadoPrendaDTO {

  private Integer idPrenda;
  private Boolean nuevo;
  private Integer stock;

  public EstadoPrendaDTO() {
  }

  public Integer getIdPrenda() {
    return idPrenda;
  }

  public void setIdPrenda(Integer idPrenda) {
    this.idPrenda = idPrenda;
  }

  public Boolean getNuevo() {
    return nuevo;
  }

  public void setNuevo(Boolean nuevo) {
    this.nuevo = nuevo;
  }

  public Integer getStock() {
    return stock;
  }

  public void setStock(Integer stock) {
    this.stock = stock;
  }
}
