package com.puertodeseado.entidades.anticiporetorno;

import com.puertodeseado.entidades.AsociadosMain;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ImprimirRecibo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_liquidacon")
    private LiquidacionHistorial liquidacionHistorial;

    @ManyToOne
    @JoinColumn(name = "id_asociado")
    private AsociadosMain asociadosMain;

    @Column(name = "fecha_emision")
    private Date fechaEmision;

    public ImprimirRecibo() {
    }

    public ImprimirRecibo(Integer id, LiquidacionHistorial liquidacionHistorial, AsociadosMain asociadosMain, Date fechaEmision) {
        this.id = id;
        this.liquidacionHistorial = liquidacionHistorial;
        this.asociadosMain = asociadosMain;
        this.fechaEmision = new Date();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LiquidacionHistorial getLiquidacionHistorial() {
        return liquidacionHistorial;
    }

    public void setLiquidacionHistorial(LiquidacionHistorial liquidacionHistorial) {
        this.liquidacionHistorial = liquidacionHistorial;
    }

    public AsociadosMain getAsociadosMain() {
        return asociadosMain;
    }

    public void setAsociadosMain(AsociadosMain asociadosMain) {
        this.asociadosMain = asociadosMain;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

}
