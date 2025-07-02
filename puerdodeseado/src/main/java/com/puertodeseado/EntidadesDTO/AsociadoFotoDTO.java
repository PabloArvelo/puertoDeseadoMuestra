package com.puertodeseado.EntidadesDTO;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public class AsociadoFotoDTO {

  private String idAsoc;
  private String cuil;

  @NotNull(message = "seleccione una imagen")
  private MultipartFile archivo;



  public AsociadoFotoDTO() {
  }

  public String getIdAsoc() {
    return idAsoc;
  }

  public void setIdAsoc(String idAsoc) {
    this.idAsoc = idAsoc;
  }

  public String getCuil() {
    return cuil;
  }

  public void setCuil(String cuil) {
    this.cuil = cuil;
  }

  public MultipartFile getArchivo() {
    return archivo;
  }

  public void setArchivo(MultipartFile archivo) {
    this.archivo = archivo;
  }
}
