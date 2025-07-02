package com.puertodeseado.EntidadesDTO.anticipoderetorno;

public class AsociadosListaLiquidacionDTO {
  private String id;
  private String apellido;
  private String nombre;
  private Long cuil;

  public AsociadosListaLiquidacionDTO() {
  }

  public AsociadosListaLiquidacionDTO(String id, String apellido, String nombre, Long cuil) {
    this.id = id;
    this.apellido = apellido;
    this.nombre = nombre;
    this.cuil = cuil;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public Long getCuil() {
    return cuil;
  }

  public void setCuil(Long cuil) {
    this.cuil = cuil;
  }
}
