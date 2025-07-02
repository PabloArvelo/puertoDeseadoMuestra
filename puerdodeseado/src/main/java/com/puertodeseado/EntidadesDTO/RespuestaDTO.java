package com.puertodeseado.EntidadesDTO;

public class RespuestaDTO {
  private boolean exito;
  private String mensaje;

  public RespuestaDTO(boolean exito, String mensaje) {
    this.exito = exito;
    this.mensaje = mensaje;
  }

  public boolean isExito() {
    return exito;
  }

  public void setExito(boolean exito) {
    this.exito = exito;
  }

  public String getMensaje() {
    return mensaje;
  }

  public void setMensaje(String mensaje) {
    this.mensaje = mensaje;
  }
}
