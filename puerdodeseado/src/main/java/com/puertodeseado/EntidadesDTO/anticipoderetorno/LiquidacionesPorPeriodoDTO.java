package com.puertodeseado.EntidadesDTO.anticipoderetorno;

import java.math.BigDecimal;
import java.math.BigInteger;

public class LiquidacionesPorPeriodoDTO {
    private String idLiquidacion;
    private String idAsoc;
    private String apellido;
    private String nombre;
    private Long cuil;
    private BigDecimal totalAnticipo;

    public LiquidacionesPorPeriodoDTO() {
    }

    public LiquidacionesPorPeriodoDTO(String idLiquidacion, String idAsoc, String apellido, String nombre, Long cuil, BigDecimal totalAnticipo) {
        this.idLiquidacion = idLiquidacion;
        this.idAsoc = idAsoc;
        this.apellido = apellido;
        this.nombre = nombre;
        this.cuil = cuil;
        this.totalAnticipo = totalAnticipo;
    }

    public String getIdLiquidacion() {
        return idLiquidacion;
    }

    public void setIdLiquidacion(String idLiquidacion) {
        this.idLiquidacion = idLiquidacion;
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

    public Long getCuil() {
        return cuil;
    }

    public void setCuil(Long cuil) {
        this.cuil = cuil;
    }

    public BigDecimal getTotalAnticipo() {
        return totalAnticipo;
    }

    public void setTotalAnticipo(BigDecimal totalAnticipo) {
        this.totalAnticipo = totalAnticipo;
    }
}
