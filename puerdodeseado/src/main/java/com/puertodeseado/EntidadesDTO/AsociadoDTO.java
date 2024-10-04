package com.puertodeseado.EntidadesDTO;


import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class AsociadoDTO {

  private String idAsoc;


  @NotEmpty(message = "dato obligatorio")

  private String apellido;
  @NotEmpty(message = "dato obligatorio")
  private String nombre;

  private Date fechaIngreso;
  @NotEmpty(message = "dato obligatorio")
  private String depatamentoLaboral;
  @NotNull(message = "dato obligatorio")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private Date fechaNacimiento;
  @NotEmpty(message = "dato obligatorio")
  private String nacionalidad;
  @NotNull(message = "dato obligatorio")
  private Integer dni;
  @NotNull(message = "dato obligatorio")
  @Min(value = 10000000000L, message = "El CUIL debe tener 11 dígitos")
  @Max(value = 99999999999L, message = "El CUIL debe tener 11 dígitos")
  private Long cuil;
  @NotEmpty(message = "dato obligatorio")
  private String estadoCivil;

  private String grupoSanguineo;
  private Long telFijo;
  @NotNull(message = "dato obligatorio")
  private Long telMovil;
  @Email
  @NotEmpty(message = "dato obligatorio")
  private String email;
  @NotEmpty(message = "dato obligatorio")
  private String calle;
  @NotNull(message = "dato obligatorio")
  private Integer numero;
  private String piso;
  private String dpto;
  @NotEmpty(message = "dato obligatorio")
  private String barrio;
  @NotEmpty(message = "dato obligatorio")
  private String distrito;
  @NotEmpty(message = "dato obligatorio")
  private String provincia;
  @NotNull(message = "dato obligatorio")
  private Integer codigoPostal;

  private MultipartFile archivo;


  private String rutaImagen;


  public AsociadoDTO() {
  }

  public String getIdAsoc() {
    return idAsoc;
  }

  public void setIdAsoc(String idAsoc) {
    this.idAsoc = idAsoc;
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

  public Date getFechaIngreso() {
    return fechaIngreso;
  }

  public void setFechaIngreso(Date fechaIngreso) {
    this.fechaIngreso = fechaIngreso;
  }

  public String getDepatamentoLaboral() {
    return depatamentoLaboral;
  }

  public void setDepatamentoLaboral(String depatamentoLaboral) {
    this.depatamentoLaboral = depatamentoLaboral;
  }

  public Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }

  public String getNacionalidad() {
    return nacionalidad;
  }

  public void setNacionalidad(String nacionalidad) {
    this.nacionalidad = nacionalidad;
  }

  public Integer getDni() {
    return dni;
  }

  public void setDni(Integer dni) {
    this.dni = dni;
  }

  public Long getCuil() {
    return cuil;
  }

  public void setCuil(Long cuil) {
    this.cuil = cuil;
  }

  public String getEstadoCivil() {
    return estadoCivil;
  }

  public void setEstadoCivil(String estadoCivil) {
    this.estadoCivil = estadoCivil;
  }

  public String getGrupoSanguineo() {
    return grupoSanguineo;
  }

  public void setGrupoSanguineo(String grupoSanguineo) {
    this.grupoSanguineo = grupoSanguineo;
  }

  public Long getTelFijo() {
    return telFijo;
  }

  public void setTelFijo(Long telFijo) {
    this.telFijo = telFijo;
  }

  public Long getTelMovil() {
    return telMovil;
  }

  public void setTelMovil(Long telMovil) {
    this.telMovil = telMovil;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCalle() {
    return calle;
  }

  public void setCalle(String calle) {
    this.calle = calle;
  }

  public Integer getNumero() {
    return numero;
  }

  public void setNumero(Integer numero) {
    this.numero = numero;
  }

  public String getPiso() {
    return piso;
  }

  public void setPiso(String piso) {
    this.piso = piso;
  }

  public String getDpto() {
    return dpto;
  }

  public void setDpto(String dpto) {
    this.dpto = dpto;
  }

  public String getBarrio() {
    return barrio;
  }

  public void setBarrio(String barrio) {
    this.barrio = barrio;
  }

  public String getDistrito() {
    return distrito;
  }

  public void setDistrito(String distrito) {
    this.distrito = distrito;
  }

  public String getProvincia() {
    return provincia;
  }

  public void setProvincia(String provincia) {
    this.provincia = provincia;
  }

  public Integer getCodigoPostal() {
    return codigoPostal;
  }

  public void setCodigoPostal(Integer codigoPostal) {
    this.codigoPostal = codigoPostal;
  }

  public MultipartFile getArchivo() {
    return archivo;
  }

  public void setArchivo(MultipartFile archivo) {
    this.archivo = archivo;
  }

  public String getRutaImagen() {
    return rutaImagen;
  }

  public void setRutaImagen(String rutaImagen) {
    this.rutaImagen = rutaImagen;
  }
}
