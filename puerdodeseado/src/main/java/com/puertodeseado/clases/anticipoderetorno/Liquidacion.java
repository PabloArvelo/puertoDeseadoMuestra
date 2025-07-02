package com.puertodeseado.clases.anticipoderetorno;

import com.puertodeseado.clases.RopaStock;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@MappedSuperclass
public class Liquidacion {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;

    private int horas1;
    private int minutos1;
    private BigDecimal valorHora1;
    private int objetivo1;

    private int horas2;
    private int minutos2;
    private BigDecimal valorHora2;
    private int objetivo2;

    private int horas3;
    private int minutos3;
    private BigDecimal valorHora3;
    private int objetivo3;

    private int horas4;
    private int minutos4;
    private BigDecimal valorHora4;
    private int objetivo4;

    private int horas5;
    private int minutos5;
    private BigDecimal valorHora5;
    private int objetivo5;

    private int horas6;
    private int minutos6;
    private BigDecimal valorHora6;
    private int objetivo6;

    private int horas7;
    private int minutos7;
    private BigDecimal valorHora7;
    private int objetivo7;

    private int horas8;
    private int minutos8;
    private BigDecimal valorHora8;
    private int objetivo8;

    private int horas9;
    private int minutos9;
    private BigDecimal valorHora9;
    private int objetivo9;

    private int horas10;
    private int minutos10;
    private BigDecimal valorHora10;
    private int objetivo10;

    // suman
    private double bonificaciones;
    private double presentismo;
    private double nocturnidad;
    private double nocturnidadEventual;
    private double bonificacionFeriados;
    private double reconocimientoHoras;
    private double carpetaMedica;
    private double hsVacaciones;
    private double horasAdeudadas;
    private double licenciaMaternidad;
    private double horasPractica;
    private double reintegroCuotasSociales;

    // restan
    private double cuotaSocial;
    private double seguroAccPer_Vida;
    private double retenMonotriPer;
    private double seguroVidaOblig;
    private double adelanto;
    private double prestamo;
    private double costoHabilitacion;
    private double aptoPsiFi;
    private double credisolCS;
    private double credisolCC;
    private double creditoGUPAServicioSAS;
    private double almacen;
    private double embargoJudicial;
    private double depositoExceso;
    private double descuentoRoturaPerdida;
    private double calzado;
    private double adherenteMonotributo;

    // Totales
    private BigDecimal totalBruto;
    private BigDecimal totalDeducciones;

    private BigDecimal totalAnticipo;


    public Liquidacion() {
    }

    public Liquidacion(int horas1,
                       int minutos1,
                       BigDecimal valorHora1,
                       int objetivo1,
                       int horas2,
                       int minutos2,
                       BigDecimal valorHora2,
                       int objetivo2,
                       int horas3,
                       int minutos3,
                       BigDecimal valorHora3,
                       int objetivo3,
                       int horas4,
                       int minutos4,
                       BigDecimal valorHora4,
                       int objetivo4,
                       int horas5,
                       int minutos5,
                       BigDecimal valorHora5,
                       int objetivo5,
                       int horas6,
                       int minutos6,
                       BigDecimal valorHora6,
                       int objetivo6,
                       int horas7,
                       int minutos7,
                       BigDecimal valorHora7,
                       int objetivo7,
                       int horas8,
                       int minutos8,
                       BigDecimal valorHora8,
                       int objetivo8,
                       int horas9,
                       int minutos9,
                       BigDecimal valorHora9,
                       int objetivo9,
                       int horas10,
                       int minutos10,
                       BigDecimal valorHora10,
                       int objetivo10,
                       //double bonificaciones,
                       double presentismo, double nocturnidad,
                       double nocturnidadEventual, double bonificacionFeriados,
                       double reconocimientoHoras, double carpetaMedica,
                       double hsVacaciones, double horasAdeudadas,
                       double licenciaMaternidad, double horasPractica,
                       double reintegroCuotasSociales, double cuotaSocial,
                       double seguroAccPer_Vida, double retenMonotriPer,
                       double seguroVidaOblig, double adelanto,
                       double prestamo, double costoHabilitacion,
                       double aptoPsiFi, double credisolCS,
                       double credisolCC, double creditoGUPAServicioSAS,
                       double almacen, double embargoJudicial,
                       double depositoExceso, double descuentoRoturaPerdida,
                       double calzado, double adherenteMonotributo
    ) {
        this.horas1 = horas1;
        this.minutos1 = minutos1;
        this.valorHora1 = valorHora1;
        this.objetivo1 = objetivo1;
        this.horas2 = horas2;
        this.minutos2 = minutos2;
        this.valorHora2 = valorHora2;
        this.objetivo2 = objetivo2;
        this.horas3 = horas3;
        this.minutos3 = minutos3;
        this.valorHora3 = valorHora3;
        this.objetivo3 = objetivo3;
        this.horas4 = horas4;
        this.minutos4 = minutos4;
        this.valorHora4 = valorHora4;
        this.objetivo4 = objetivo4;
        this.horas5 = horas5;
        this.minutos5 = minutos5;
        this.valorHora5 = valorHora5;
        this.objetivo5 = objetivo5;
        this.horas6 = horas6;
        this.minutos6 = minutos6;
        this.valorHora6 = valorHora6;
        this.objetivo6 = objetivo6;
        this.horas7 = horas7;
        this.minutos7 = minutos7;
        this.valorHora7 = valorHora7;
        this.objetivo7 = objetivo7;
        this.horas8 = horas8;
        this.minutos8 = minutos8;
        this.valorHora8 = valorHora8;
        this.objetivo8 = objetivo8;
        this.horas9 = horas9;
        this.minutos9 = minutos9;
        this.valorHora9 = valorHora9;
        this.objetivo9 = objetivo9;
        this.horas10 = horas10;
        this.minutos10 = minutos10;
        this.valorHora10 = valorHora10;
        this.objetivo10 = objetivo10;
        this.bonificaciones = seguroAccPer_Vida + seguroVidaOblig + retenMonotriPer;
        this.presentismo = presentismo;
        this.nocturnidad = nocturnidad; // es un monto en plata
        this.nocturnidadEventual = nocturnidadEventual;
        this.bonificacionFeriados = bonificacionFeriados;
        this.reconocimientoHoras = reconocimientoHoras;
        this.carpetaMedica = carpetaMedica;
        this.hsVacaciones = hsVacaciones;
        this.horasAdeudadas = horasAdeudadas;
        this.licenciaMaternidad = licenciaMaternidad;
        this.horasPractica = horasPractica;
        this.reintegroCuotasSociales = reintegroCuotasSociales;
        this.cuotaSocial = cuotaSocial;
        this.seguroAccPer_Vida = seguroAccPer_Vida;
        this.retenMonotriPer = retenMonotriPer;
        this.seguroVidaOblig = seguroVidaOblig;
        this.adelanto = adelanto;
        this.prestamo = prestamo;
        this.costoHabilitacion = costoHabilitacion;
        this.aptoPsiFi = aptoPsiFi;
        this.credisolCS = credisolCS;
        this.credisolCC = credisolCC;
        this.creditoGUPAServicioSAS = creditoGUPAServicioSAS;
        this.almacen = almacen;
        this.embargoJudicial = embargoJudicial;
        this.depositoExceso = depositoExceso;
        this.descuentoRoturaPerdida = descuentoRoturaPerdida;
        this.calzado = calzado;
        this.adherenteMonotributo = adherenteMonotributo;
        this.totalBruto = totalBruto(bonificaciones,
                presentismo,
                nocturnidad,
                nocturnidadEventual,
                bonificacionFeriados,
                reconocimientoHoras,
                carpetaMedica,
                hsVacaciones,
                horasAdeudadas,
                licenciaMaternidad,
                horasPractica,
                reintegroCuotasSociales);
        this.totalDeducciones = calculaDeducciones(cuotaSocial,
                seguroAccPer_Vida,
                retenMonotriPer,
                seguroVidaOblig,
                adelanto,
                prestamo,
                costoHabilitacion,
                aptoPsiFi,
                credisolCS,
                credisolCC,
                creditoGUPAServicioSAS,
                almacen,
                embargoJudicial,
                depositoExceso,
                descuentoRoturaPerdida,
                calzado,
                adherenteMonotributo);
        this.totalAnticipo = totalGeneral();

    }

    public int getHoras1() {
        return horas1;
    }

    public void setHoras1(int horas1) {
        this.horas1 = horas1;
    }

    public int getMinutos1() {
        return minutos1;
    }

    public void setMinutos1(int minutos1) {
        this.minutos1 = minutos1;
    }

    public BigDecimal getValorHora1() {
        return valorHora1;
    }

    public void setValorHora1(BigDecimal valorHora1) {
        this.valorHora1 = valorHora1;
    }

    public int getObjetivo1() {
        return objetivo1;
    }

    public void setObjetivo1(int objetivo1) {
        this.objetivo1 = objetivo1;
    }

    public int getHoras2() {
        return horas2;
    }

    public void setHoras2(int horas2) {
        this.horas2 = horas2;
    }

    public int getMinutos2() {
        return minutos2;
    }

    public void setMinutos2(int minutos2) {
        this.minutos2 = minutos2;
    }

    public BigDecimal getValorHora2() {
        return valorHora2;
    }

    public void setValorHora2(BigDecimal valorHora2) {
        this.valorHora2 = valorHora2;
    }

    public int getObjetivo2() {
        return objetivo2;
    }

    public void setObjetivo2(int objetivo2) {
        this.objetivo2 = objetivo2;
    }

    public int getHoras3() {
        return horas3;
    }

    public void setHoras3(int horas3) {
        this.horas3 = horas3;
    }

    public int getMinutos3() {
        return minutos3;
    }

    public void setMinutos3(int minutos3) {
        this.minutos3 = minutos3;
    }

    public BigDecimal getValorHora3() {
        return valorHora3;
    }

    public void setValorHora3(BigDecimal valorHora3) {
        this.valorHora3 = valorHora3;
    }

    public int getObjetivo3() {
        return objetivo3;
    }

    public void setObjetivo3(int objetivo3) {
        this.objetivo3 = objetivo3;
    }

    public int getHoras4() {
        return horas4;
    }

    public void setHoras4(int horas4) {
        this.horas4 = horas4;
    }

    public int getMinutos4() {
        return minutos4;
    }

    public void setMinutos4(int minutos4) {
        this.minutos4 = minutos4;
    }

    public BigDecimal getValorHora4() {
        return valorHora4;
    }

    public void setValorHora4(BigDecimal valorHora4) {
        this.valorHora4 = valorHora4;
    }

    public int getObjetivo4() {
        return objetivo4;
    }

    public void setObjetivo4(int objetivo4) {
        this.objetivo4 = objetivo4;
    }

    public int getHoras5() {
        return horas5;
    }

    public void setHoras5(int horas5) {
        this.horas5 = horas5;
    }

    public int getMinutos5() {
        return minutos5;
    }

    public void setMinutos5(int minutos5) {
        this.minutos5 = minutos5;
    }

    public BigDecimal getValorHora5() {
        return valorHora5;
    }

    public void setValorHora5(BigDecimal valorHora5) {
        this.valorHora5 = valorHora5;
    }

    public int getObjetivo5() {
        return objetivo5;
    }

    public void setObjetivo5(int objetivo5) {
        this.objetivo5 = objetivo5;
    }

    public int getHoras6() {
        return horas6;
    }

    public void setHoras6(int horas6) {
        this.horas6 = horas6;
    }

    public int getMinutos6() {
        return minutos6;
    }

    public void setMinutos6(int minutos6) {
        this.minutos6 = minutos6;
    }

    public BigDecimal getValorHora6() {
        return valorHora6;
    }

    public void setValorHora6(BigDecimal valorHora6) {
        this.valorHora6 = valorHora6;
    }

    public int getObjetivo6() {
        return objetivo6;
    }

    public void setObjetivo6(int objetivo6) {
        this.objetivo6 = objetivo6;
    }

    public int getHoras7() {
        return horas7;
    }

    public void setHoras7(int horas7) {
        this.horas7 = horas7;
    }

    public int getMinutos7() {
        return minutos7;
    }

    public void setMinutos7(int minutos7) {
        this.minutos7 = minutos7;
    }

    public BigDecimal getValorHora7() {
        return valorHora7;
    }

    public void setValorHora7(BigDecimal valorHora7) {
        this.valorHora7 = valorHora7;
    }

    public int getObjetivo7() {
        return objetivo7;
    }

    public void setObjetivo7(int objetivo7) {
        this.objetivo7 = objetivo7;
    }

    public int getHoras8() {
        return horas8;
    }

    public void setHoras8(int horas8) {
        this.horas8 = horas8;
    }

    public int getMinutos8() {
        return minutos8;
    }

    public void setMinutos8(int minutos8) {
        this.minutos8 = minutos8;
    }

    public BigDecimal getValorHora8() {
        return valorHora8;
    }

    public void setValorHora8(BigDecimal valorHora8) {
        this.valorHora8 = valorHora8;
    }

    public int getObjetivo8() {
        return objetivo8;
    }

    public void setObjetivo8(int objetivo8) {
        this.objetivo8 = objetivo8;
    }

    public int getHoras9() {
        return horas9;
    }

    public void setHoras9(int horas9) {
        this.horas9 = horas9;
    }

    public int getMinutos9() {
        return minutos9;
    }

    public void setMinutos9(int minutos9) {
        this.minutos9 = minutos9;
    }

    public BigDecimal getValorHora9() {
        return valorHora9;
    }

    public void setValorHora9(BigDecimal valorHora9) {
        this.valorHora9 = valorHora9;
    }

    public int getObjetivo9() {
        return objetivo9;
    }

    public void setObjetivo9(int objetivo9) {
        this.objetivo9 = objetivo9;
    }

    public int getHoras10() {
        return horas10;
    }

    public void setHoras10(int horas10) {
        this.horas10 = horas10;
    }

    public int getMinutos10() {
        return minutos10;
    }

    public void setMinutos10(int minutos10) {
        this.minutos10 = minutos10;
    }

    public BigDecimal getValorHora10() {
        return valorHora10;
    }

    public void setValorHora10(BigDecimal valorHora10) {
        this.valorHora10 = valorHora10;
    }

    public int getObjetivo10() {
        return objetivo10;
    }

    public void setObjetivo10(int objetivo10) {
        this.objetivo10 = objetivo10;
    }

    public double getBonificaciones() {
        return bonificaciones;
    }

//    public void setBonificaciones(double bonificaciones) {
//        this.bonificaciones = bonificaciones;
//    }

    public double getPresentismo() {
        return presentismo;
    }

    public void setPresentismo(double presentismo) {
        this.presentismo = presentismo;
    }

    public double getNocturnidad() {
        return nocturnidad;
    }

    public void setNocturnidad(double nocturnidad) {
        this.nocturnidad = nocturnidad;
    }

    public double getNocturnidadEventual() {
        return nocturnidadEventual;
    }

    public void setNocturnidadEventual(double nocturnidadEventual) {
        this.nocturnidadEventual = nocturnidadEventual;
    }

    public double getBonificacionFeriados() {
        return bonificacionFeriados;
    }

    public void setBonificacionFeriados(double bonificacionFeriados) {
        this.bonificacionFeriados = bonificacionFeriados;
    }

    public double getReconocimientoHoras() {
        return reconocimientoHoras;
    }

    public void setReconocimientoHoras(double reconocimientoHoras) {
        this.reconocimientoHoras = reconocimientoHoras;
    }

    public double getCarpetaMedica() {
        return carpetaMedica;
    }

    public void setCarpetaMedica(double carpetaMedica) {
        this.carpetaMedica = carpetaMedica;
    }

    public double getHsVacaciones() {
        return hsVacaciones;
    }

    public void setHsVacaciones(double hsVacaciones) {
        this.hsVacaciones = hsVacaciones;
    }

    public double getHorasAdeudadas() {
        return horasAdeudadas;
    }

    public void setHorasAdeudadas(double horasAdeudadas) {
        this.horasAdeudadas = horasAdeudadas;
    }

    public double getLicenciaMaternidad() {
        return licenciaMaternidad;
    }

    public void setLicenciaMaternidad(double licenciaMaternidad) {
        this.licenciaMaternidad = licenciaMaternidad;
    }

    public double getHorasPractica() {
        return horasPractica;
    }

    public void setHorasPractica(double horasPractica) {
        this.horasPractica = horasPractica;
    }

    public double getReintegroCuotasSociales() {
        return reintegroCuotasSociales;
    }

    public void setReintegroCuotasSociales(double reintegroCuotasSociales) {
        this.reintegroCuotasSociales = reintegroCuotasSociales;
    }

    public double getCuotaSocial() {
        return cuotaSocial;
    }

    public void setCuotaSocial(double cuotaSocial) {
        this.cuotaSocial = cuotaSocial;
    }

    public double getSeguroAccPer_Vida() {
        return seguroAccPer_Vida;
    }

    public void setSeguroAccPer_Vida(double seguroAccPer_Vida) {
        this.seguroAccPer_Vida = seguroAccPer_Vida;
    }

    public double getRetenMonotriPer() {
        return retenMonotriPer;
    }

    public void setRetenMonotriPer(double retenMonotriPer) {
        this.retenMonotriPer = retenMonotriPer;
    }

    public double getSeguroVidaOblig() {
        return seguroVidaOblig;
    }

    public void setSeguroVidaOblig(double seguroVidaOblig) {
        this.seguroVidaOblig = seguroVidaOblig;
    }

    public double getAdelanto() {
        return adelanto;
    }

    public void setAdelanto(double adelanto) {
        this.adelanto = adelanto;
    }

    public double getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(double prestamo) {
        this.prestamo = prestamo;
    }

    public double getCostoHabilitacion() {
        return costoHabilitacion;
    }

    public void setCostoHabilitacion(double costoHabilitacion) {
        this.costoHabilitacion = costoHabilitacion;
    }

    public double getAptoPsiFi() {
        return aptoPsiFi;
    }

    public void setAptoPsiFi(double aptoPsiFi) {
        this.aptoPsiFi = aptoPsiFi;
    }

    public double getCredisolCS() {
        return credisolCS;
    }

    public void setCredisolCS(double credisolCS) {
        this.credisolCS = credisolCS;
    }

    public double getCredisolCC() {
        return credisolCC;
    }

    public void setCredisolCC(double credisolCC) {
        this.credisolCC = credisolCC;
    }

    public double getCreditoGUPAServicioSAS() {
        return creditoGUPAServicioSAS;
    }

    public void setCreditoGUPAServicioSAS(double creditoGUPAServicioSAS) {
        this.creditoGUPAServicioSAS = creditoGUPAServicioSAS;
    }

    public double getAlmacen() {
        return almacen;
    }

    public void setAlmacen(double almacen) {
        this.almacen = almacen;
    }

    public double getEmbargoJudicial() {
        return embargoJudicial;
    }

    public void setEmbargoJudicial(double embargoJudicial) {
        this.embargoJudicial = embargoJudicial;
    }

    public double getDepositoExceso() {
        return depositoExceso;
    }

    public void setDepositoExceso(double depositoExceso) {
        this.depositoExceso = depositoExceso;
    }

    public double getDescuentoRoturaPerdida() {
        return descuentoRoturaPerdida;
    }

    public void setDescuentoRoturaPerdida(double descuentoRoturaPerdida) {
        this.descuentoRoturaPerdida = descuentoRoturaPerdida;
    }

    public double getCalzado() {
        return calzado;
    }

    public void setCalzado(double calzado) {
        this.calzado = calzado;
    }

    public double getAdherenteMonotributo() {
        return adherenteMonotributo;
    }

    public void setAdherenteMonotributo(double adherenteMonotributo) {
        this.adherenteMonotributo = adherenteMonotributo;
    }

    public BigDecimal getTotalAnticipo() {
        return totalAnticipo;
    }

    public BigDecimal getTotalBruto(){
        return totalBruto;
    }

    public BigDecimal getTotalDeducciones() {
        return totalDeducciones;
    }


    // calcula el total por horas trabajadas
    public BigDecimal calculaPercepcionPorHoras1() {
        // Convierte las horas y minutos trabajados a horas decimales
        BigDecimal horasTrabajadas = new BigDecimal(horas1)
                .add(new BigDecimal(minutos1).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP));

        // Calcula el total del pago multiplicando horas trabajadas por valor hora
        return horasTrabajadas.multiply(valorHora1).setScale(2, RoundingMode.HALF_UP);  // Redondea a dos decimales
    }

    public BigDecimal calculaPercepcionPorHoras2() {
        // Convierte las horas y minutos trabajados a horas decimales
        BigDecimal horasTrabajadas = new BigDecimal(horas2)
                .add(new BigDecimal(minutos2).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP));



        // Calcula el total del pago multiplicando horas trabajadas por valor hora
        return horasTrabajadas.multiply(valorHora2).setScale(2, RoundingMode.HALF_UP);  // Redondea a dos decimales
    }

    public BigDecimal calculaPercepcionPorHoras3() {
        // Convierte las horas y minutos trabajados a horas decimales
        BigDecimal horasTrabajadas = new BigDecimal(horas3)
                .add(new BigDecimal(minutos3).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP));

        // Calcula el total del pago multiplicando horas trabajadas por valor hora
        return horasTrabajadas.multiply(valorHora3).setScale(2, RoundingMode.HALF_UP);  // Redondea a dos decimales
    }

    public BigDecimal calculaPercepcionPorHoras4() {
        // Convierte las horas y minutos trabajados a horas decimales
        BigDecimal horasTrabajadas = new BigDecimal(horas4)
                .add(new BigDecimal(minutos4).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP));

        // Calcula el total del pago multiplicando horas trabajadas por valor hora
        return horasTrabajadas.multiply(valorHora4).setScale(2, RoundingMode.HALF_UP);  // Redondea a dos decimales
    }

    public BigDecimal calculaPercepcionPorHoras5() {
        // Convierte las horas y minutos trabajados a horas decimales
        BigDecimal horasTrabajadas = new BigDecimal(horas5)
                .add(new BigDecimal(minutos5).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP));

        // Calcula el total del pago multiplicando horas trabajadas por valor hora
        return horasTrabajadas.multiply(valorHora5).setScale(2, RoundingMode.HALF_UP);  // Redondea a dos decimales
    }

    public BigDecimal calculaPercepcionPorHoras6() {
        // Convierte las horas y minutos trabajados a horas decimales
        BigDecimal horasTrabajadas = new BigDecimal(horas6)
                .add(new BigDecimal(minutos6).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP));

        // Calcula el total del pago multiplicando horas trabajadas por valor hora
        return horasTrabajadas.multiply(valorHora6).setScale(2, RoundingMode.HALF_UP);  // Redondea a dos decimales
    }

    public BigDecimal calculaPercepcionPorHoras7() {
        // Convierte las horas y minutos trabajados a horas decimales
        BigDecimal horasTrabajadas = new BigDecimal(horas7)
                .add(new BigDecimal(minutos7).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP));

        // Calcula el total del pago multiplicando horas trabajadas por valor hora
        return horasTrabajadas.multiply(valorHora7).setScale(2, RoundingMode.HALF_UP);  // Redondea a dos decimales
    }

    public BigDecimal calculaPercepcionPorHoras8() {
        // Convierte las horas y minutos trabajados a horas decimales
        BigDecimal horasTrabajadas = new BigDecimal(horas8)
                .add(new BigDecimal(minutos8).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP));

        // Calcula el total del pago multiplicando horas trabajadas por valor hora
        return horasTrabajadas.multiply(valorHora8).setScale(2, RoundingMode.HALF_UP);  // Redondea a dos decimales
    }

    public BigDecimal calculaPercepcionPorHoras9() {
        // Convierte las horas y minutos trabajados a horas decimales
        BigDecimal horasTrabajadas = new BigDecimal(horas9)
                .add(new BigDecimal(minutos9).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP));

        // Calcula el total del pago multiplicando horas trabajadas por valor hora
        return horasTrabajadas.multiply(valorHora9).setScale(2, RoundingMode.HALF_UP);  // Redondea a dos decimales
    }

    public BigDecimal calculaPercepcionPorHoras10() {
        // Convierte las horas y minutos trabajados a horas decimales
        BigDecimal horasTrabajadas = new BigDecimal(horas10)
                .add(new BigDecimal(minutos10).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP));

        // Calcula el total del pago multiplicando horas trabajadas por valor hora
        return horasTrabajadas.multiply(valorHora10).setScale(2, RoundingMode.HALF_UP);  // Redondea a dos decimales
    }


    // calcula el total de las deducciones
    public BigDecimal calculaDeducciones(double cuotaSocial,
                                         double seguroAccPer_Vida,
                                         double retenMonotriPer,
                                         double seguroVidaOblig,
                                         double adelanto,
                                         double prestamo,
                                         double costoHabilitacion,
                                         double aptoPsiFi,
                                         double credisolCS,
                                         double credisolCC,
                                         double creditoGUPAServicioSAS,
                                         double almacen,
                                         double embargoJudicial,
                                         double depositoExceso,
                                         double descuentoRoturaPerdida,
                                         double calzado,
                                         double adherenteMonotributo) {


        double subTotal = cuotaSocial +
                seguroAccPer_Vida +
                retenMonotriPer +
                seguroVidaOblig +
                adelanto +
                prestamo +
                costoHabilitacion +
                aptoPsiFi +
                credisolCS +
                credisolCC +
                creditoGUPAServicioSAS +
                almacen +
                embargoJudicial +
                depositoExceso +
                descuentoRoturaPerdida +
                calzado +
                adherenteMonotributo;


        BigDecimal totalDescuentos = new BigDecimal(subTotal);
        return totalDescuentos;
    }

    // calcula el total bruto
    public BigDecimal totalBruto(double bonificaciones,
                                 double presentismo,
                                 double nocturnidad,
                                 double nocturnidadEventual,
                                 double bonificacionFeriados,
                                 double reconocimientoHoras,
                                 double carpetaMedica,
                                 double hsVacaciones,
                                 double horasAdeudadas,
                                 double licenciaMaternidad,
                                 double horasPractica,
                                 double reintegroCuotasSociales) {

        double subTotal = bonificaciones +
                presentismo +
                nocturnidad +
                nocturnidadEventual +
                bonificacionFeriados +
                reconocimientoHoras +
                carpetaMedica +
                hsVacaciones +
                horasAdeudadas +
                licenciaMaternidad +
                horasPractica +
                reintegroCuotasSociales;

        BigDecimal[] totalesPorHora = {
                new BigDecimal(String.valueOf(calculaPercepcionPorHoras1())),
                new BigDecimal(String.valueOf(calculaPercepcionPorHoras2())),
                new BigDecimal(String.valueOf(calculaPercepcionPorHoras3())),
                new BigDecimal(String.valueOf(calculaPercepcionPorHoras4())),
                new BigDecimal(String.valueOf(calculaPercepcionPorHoras5())),
                new BigDecimal(String.valueOf(calculaPercepcionPorHoras6())),
                new BigDecimal(String.valueOf(calculaPercepcionPorHoras7())),
                new BigDecimal(String.valueOf(calculaPercepcionPorHoras8())),
                new BigDecimal(String.valueOf(calculaPercepcionPorHoras9())),
                new BigDecimal(String.valueOf(calculaPercepcionPorHoras10())),
        };

        // Inicializamos el total de todas las horas trabajadas en BigDecimal.ZERO
        BigDecimal totalPorTodasLasHoras = BigDecimal.ZERO;

        for (BigDecimal valor : totalesPorHora) {
            totalPorTodasLasHoras = totalPorTodasLasHoras.add(valor);
        }

        // subTotal contiene el monto de todas las variables que suman
        BigDecimal sumaSubtotal = new BigDecimal(subTotal);

        BigDecimal totalBruto = totalPorTodasLasHoras.add(sumaSubtotal);
        return totalBruto;
    }


    // calcula el total general
    public BigDecimal totalGeneral() {


        BigDecimal totalBrutoBig = totalBruto(bonificaciones,
                presentismo,
                nocturnidad,
                nocturnidadEventual,
                bonificacionFeriados,
                reconocimientoHoras,
                carpetaMedica,
                hsVacaciones,
                horasAdeudadas,
                licenciaMaternidad,
                horasPractica,
                reintegroCuotasSociales);


        // BigDecimal debe = calculaDescuentos(new BigDecimal(String.valueOf(adelanto)), new BigDecimal(String.valueOf(prestamo)));

        BigDecimal deducciones = calculaDeducciones(cuotaSocial,
                seguroAccPer_Vida,
                retenMonotriPer,
                seguroVidaOblig,
                adelanto,
                prestamo,
                costoHabilitacion,
                aptoPsiFi,
                credisolCS,
                credisolCC,
                creditoGUPAServicioSAS,
                almacen,
                embargoJudicial,
                depositoExceso,
                descuentoRoturaPerdida,
                calzado,
                adherenteMonotributo);

        BigDecimal totalLiquidacion = totalBrutoBig.subtract(deducciones);
        return totalLiquidacion.setScale(2, RoundingMode.HALF_UP);
    }


    public String mostrarTotalPago() {
        return "Total a pagar: $" + totalGeneral();
    }


}
