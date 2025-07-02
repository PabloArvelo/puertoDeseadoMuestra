package com.puertodeseado.EntidadesDTO.anticipoderetorno;

public class ReciboConceptosDTO {

    private String concepto;
    private String horas;
    private Double unitario;
    private Double haberes;
    private Double deducciones;
    private Double totalBruto;
    private Double totalDeducciones;
    private Double totalNeto;


    public ReciboConceptosDTO() {
    }

    public ReciboConceptosDTO(String concepto, String horas, Double unitario, Double haberes, Double deducciones, Double totalBruto, Double totalDeducciones, Double totalNeto) {
        this.concepto = concepto;
        this.horas = horas;
        this.unitario = unitario;
        this.haberes = haberes;
        this.deducciones = deducciones;
        this.totalBruto = totalBruto;
        this.totalDeducciones = totalDeducciones;
        this.totalNeto = totalNeto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getHoras() {
        return horas;
    }

    public void setHoras(String horas) {
        this.horas = horas;
    }

    public Double getUnitario() {
        return unitario;
    }

    public void setUnitario(Double unitario) {
        this.unitario = unitario;
    }

    public Double getHaberes() {
        return haberes;
    }

    public void setHaberes(Double haberes) {
        this.haberes = haberes;
    }

    public Double getDeducciones() {
        return deducciones;
    }

    public void setDeducciones(Double deducciones) {
        this.deducciones = deducciones;
    }

    public Double getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(Double totalBruto) {
        this.totalBruto = totalBruto;
    }

    public Double getTotalDeducciones() {
        return totalDeducciones;
    }

    public void setTotalDeducciones(Double totalDeducciones) {
        this.totalDeducciones = totalDeducciones;
    }

    public Double getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(Double totalNeto) {
        this.totalNeto = totalNeto;
    }
}


//        "Presentismo",
//        "Nocturnidad",
//        "Nocturnidad Eventual",
//        "Bonificaciones",
//        "Bonificación Feriado",
//        "Reconocimiento Horas",
//        "Carpeta Médica",
//        "Horas Vacaciones",
//        "horas Adeudadas",
//        "Licencia por Maternidad",
//        "Horas Práctica",
//        "Reintegro Cuotas Sociales",

//
//        "Cuota Social",
//        "Retención Monotributo",
//        "Seguro Acc. Per. + Vida",
//        "Seguro Vida Obligatorio",
//        "Costo Habilitación",
//        "Apto Psicológico",
//        "Cuota Cocial Credisol",
//        "Credisol cuota crédito",
//        "Almacén",
//        "Cuota Alimentaria",
//        "Depósito en Exceso",
//        "Descuento rotura/perdida",
//        "Calzado",
//        "Adherente monotributo",
//        "Adelantos",
//        "Préstamo cuota Nº: ",
//        "Credito Gupa SAS");