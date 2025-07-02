package com.puertodeseado.EntidadesDTO.anticipoderetorno;

public class DatosSesionDTO {

  private String idAsoc;
  private String periodo;

  public DatosSesionDTO() {
  }

  public DatosSesionDTO(String idAsoc, String periodo) {
    this.idAsoc = idAsoc;
    this.periodo = periodo;
  }

  public String getIdAsoc() {
    return idAsoc;
  }

  public void setIdAsoc(String idAsoc) {
    this.idAsoc = idAsoc;
  }

  public String getPeriodo() {
    return periodo;
  }

  public void setPeriodo(String periodo) {
    this.periodo = periodo;
  }
}
