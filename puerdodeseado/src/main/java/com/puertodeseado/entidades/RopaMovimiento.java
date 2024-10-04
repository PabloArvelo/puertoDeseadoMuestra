package com.puertodeseado.entidades;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class RopaMovimiento {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @JsonProperty("tipoMovimiento")
  @Column(name = "tipo_movimiento")
  private String tipoMovimiento ;

  @JsonProperty("asociadosMain")
  @ManyToOne
  @JoinColumn(name = "id_asociado", nullable = true)
  private AsociadosMain asociadosMain;

  @JsonProperty("ropa")
  @ManyToOne
  @JoinColumn(name = "id_prenda")

  private Ropa ropa;

  @JsonProperty("talle")
  private String talle;

  @JsonProperty("nuevo")
  private boolean nuevo;

  @JsonProperty("cantidad")
  private Integer cantidad;

  //@Temporal(TemporalType.DATE)
  @JsonProperty("fechaEntrega")
  private Date fecha; // fecha en que se registra el movimineto

  public RopaMovimiento() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTipoMovimiento() {
    return tipoMovimiento;
  }

  public void setTipoMovimiento(String tipoMovimiento) {
    this.tipoMovimiento = tipoMovimiento;
  }

  public AsociadosMain getAsociadosMain() {
    return asociadosMain;
  }

  public void setAsociadosMain(AsociadosMain asociadosMain) {
    this.asociadosMain = asociadosMain;
  }

  public Ropa getRopa() {
    return ropa;
  }

  public void setRopa(Ropa ropa) {
    this.ropa = ropa;
  }

  public String getTalle() {
    return talle;
  }

  public void setTalle(String talle) {
    this.talle = talle;
  }

  public boolean isNuevo() {
    return nuevo;
  }

  public void setNuevo(boolean nuevo) {
    this.nuevo = nuevo;
  }

  public Integer getCantidad() {
    return cantidad;
  }

  public void setCantidad(Integer cantidad) {
    this.cantidad = cantidad;
  }

  public Date getFecha() {
    return fecha;
  }

  public void setFecha(Date fecha) {
    this.fecha = fecha;
  }
}
