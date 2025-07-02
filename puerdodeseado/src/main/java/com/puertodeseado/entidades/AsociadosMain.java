package com.puertodeseado.entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;


import java.math.BigInteger;
import java.util.Date;

@Entity
@Table(name = "asociados_main")
public class AsociadosMain {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  private boolean estado;
  private String apellido;
  private String nombre;
  @Temporal(TemporalType.DATE)
  @Column(name = "fecha_ingreso")
  private Date fechaIngreso;
  @Temporal(TemporalType.DATE)
  @Column(name = "fecha_baja")
  private Date fechaBaja;
  @Column(name = "depatamento_laboral")
  private String depatamentoLaboral;
  @Column(name = "fecha_nacimiento")
  private Date fechaNacimiento;
  private String nacionalidad;
  private Integer dni;
  @Column
  private Long cuil;
  @Column(name = "estado_civil")
  private String estadoCivil;
  @Column(name = "grupo_sanguineo")
  private String grupoSanguineo;
  @Column(name = "tel_fijo")
  private Long telFijo;
  @Column(name = "tel_movil")
  private Long telMovil;

  private String email;
  private String calle;

  private Integer numero;
  private String piso;
  private String dpto;
  private String barrio;
  private String distrito;
  private String provincia;
  @Column(name = "codigo_postal")
  private Integer codigoPostal;

  @OneToOne
  private Imagen imagen;

  private Date habilitacion;

  private Double salarioMinimo;
  private Double cuotaSocial;

  private String cbu;



  public AsociadosMain() {
    this.estado = true;
    this.fechaIngreso = new Date(); // Establecer la fecha de ingreso a la fecha actual
  }


  public AsociadosMain(String id, String apellido, String nombre, Long cuil){
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

  public boolean isEstado() {
    return estado;
  }

  public void setEstado(boolean estado) {
    this.estado = estado;
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

  public Date getFechaBaja() {
    return fechaBaja;
  }

  public void setFechaBaja(Date fechaBaja) {
    this.fechaBaja = fechaBaja;
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

  public Imagen getImagen() {
    return imagen;
  }

  public void setImagen(Imagen imagen) {
    this.imagen = imagen;
  }

  public Date getHabilitacion() {
    return habilitacion;
  }
  public void setHabilitacion(Date habilitacion) {
    this.habilitacion = habilitacion;
  }

  public Double getSalarioMinimo() {
    return salarioMinimo;
  }

  public void setSalarioMinimo(Double salarioMinimo) {
    this.salarioMinimo = salarioMinimo;
  }

  public Double getCuotaSocial() {
    return cuotaSocial;
  }

  public void setCuotaSocial(Double cuotaSocial) {
    this.cuotaSocial = cuotaSocial;
  }

  public String getCbu() {
    return cbu;
  }

  public void setCbu(String cbu) {
    this.cbu = cbu;
  }
}
