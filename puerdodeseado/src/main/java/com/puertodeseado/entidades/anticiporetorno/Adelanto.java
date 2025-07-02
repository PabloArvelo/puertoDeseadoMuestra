package com.puertodeseado.entidades.anticiporetorno;

import com.puertodeseado.entidades.AsociadosMain;
import jakarta.persistence.*;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Entity
public class Adelanto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "descuenta_en_liquidacion", columnDefinition = "VARCHAR(7)")
    @Convert(converter = AdelantoYearMonthConverter.class)
    private YearMonth descuentaEnLiquidacion;

    @ManyToOne
    @JoinColumn(name = "id_asociado")
    private AsociadosMain asociadosMain;

    private Double monto;

    @Column(name = "fecha_entrega")
    @Temporal(TemporalType.DATE)
    private Date fechaEntrega;

    public Adelanto() {
    }

    public Adelanto(YearMonth descuentaEnLiquidacion, AsociadosMain asociadosMain, Double monto) {
        this.descuentaEnLiquidacion = descuentaEnLiquidacion;
        this.asociadosMain = asociadosMain;
        this.monto = monto;
        this.fechaEntrega = new Date();
    }



    public YearMonth getDescuentaEnLiquidacion() {
        return descuentaEnLiquidacion;
    }

    public void setDescuentaEnLiquidacion(YearMonth descuentaEnLiquidacion) {
        this.descuentaEnLiquidacion = descuentaEnLiquidacion;
    }

    public AsociadosMain getAsociadosMain() {
        return asociadosMain;
    }

    public void setAsociadosMain(AsociadosMain asociadosMain) {
        this.asociadosMain = asociadosMain;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }



    //  convierte de manera automática el yermonth a string
    @Converter(autoApply = true)
    public static class AdelantoYearMonthConverter implements AttributeConverter<YearMonth, String> {

        private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM");

        @Override
        public String convertToDatabaseColumn(YearMonth yearMonth) {
            return (yearMonth != null) ? yearMonth.format(FORMATTER) : null;
        }

        @Override
        public YearMonth convertToEntityAttribute(String dbData) {
            return (dbData != null) ? YearMonth.parse(dbData, FORMATTER) : null;
        }
    }




}
