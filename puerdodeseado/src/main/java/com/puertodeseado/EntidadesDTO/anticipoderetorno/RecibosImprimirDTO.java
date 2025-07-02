package com.puertodeseado.EntidadesDTO.anticipoderetorno;

import com.puertodeseado.entidades.AsociadosMain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class RecibosImprimirDTO {

    private String idLiqui;
    private AsociadosMain asociado;
    private List<ReciboConceptosDTO> conceptos;

    private String anticipoNumero;

    private String fechaDePago;
    private String periodoLiquidacion;

    private String totalTexto;

    public RecibosImprimirDTO() {
    }

    public String getIdLiqui() {
        return idLiqui;
    }

    public void setIdLiqui(String idLiqui) {
        this.idLiqui = idLiqui;
    }

    public AsociadosMain getAsociado() {
        return asociado;
    }

    public void setAsociado(AsociadosMain asociado) {
        this.asociado = asociado;
    }

    public List<ReciboConceptosDTO> getConceptos() {
        return conceptos;
    }

    public void setConceptos(List<ReciboConceptosDTO> conceptos) {
        this.conceptos = conceptos;
    }

    public String getAnticipoNumero() {
        return anticipoNumero;
    }

    public void setAnticipoNumero(String anticipoNumero) {
        this.anticipoNumero = anticipoNumero;
    }

    public String getFechaDePago() {
        return fechaDePago;
    }

    public void setFechaDePago(String fechaDePago) {
        this.fechaDePago = fechaDePago;
    }

    public String getPeriodoLiquidacion() {
        return periodoLiquidacion;
    }

    public void setPeriodoLiquidacion(String periodoLiquidacion) {
        this.periodoLiquidacion = periodoLiquidacion;
    }

    public String getTotalTexto() {
        return totalTexto;
    }

    public void setTotalTexto(String totalTexto) {
        this.totalTexto = totalTexto;
    }
}
