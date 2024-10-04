package com.puertodeseado.enums;

public enum RopaTipo {
  REMERA("remera"),
  CAMISA("camisa"),
  TRICOTA("tricota"),
  PANTALON("pantalon"),
  PANTALON_CARGO("pantalon Cargo"),
  CAMPERA("campera"),
  CAZADO("calzado"),
  CORBATA("corbata"),
  GORRA("gorra");

    private final String descripcion;

  RopaTipo(String descripcion) {
    this.descripcion = descripcion;
  }
  public String getDescripcion() {
    return descripcion;
  }
}
