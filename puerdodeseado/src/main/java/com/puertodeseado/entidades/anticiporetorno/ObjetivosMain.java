package com.puertodeseado.entidades.anticiporetorno;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ObjetivosMain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String alias;
    private String domicilio;
    private Long telefono;
    private boolean estado;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_baja")
    private Date fechaBaja;

    public ObjetivosMain() {
    }

    public ObjetivosMain(int id, String nombre, String alias, String domicilio, Long telefono) {
        this.id = id;
        this.nombre = nombre;
        this.alias = alias;
        this.domicilio = domicilio;
        this.telefono = telefono;
        this.estado = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }


    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }
}
