package com.puertodeseado.EntidadesDTO.anticipoderetorno;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.YearMonth;

public class LiquidacionActualizarDTO {


  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer horas1;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer minutos1;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private BigDecimal valor_hora1;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer objetivo1;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer horas2;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer minutos2;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private BigDecimal valor_hora2;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer objetivo2;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer horas3;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer minutos3;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private BigDecimal valor_hora3;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer objetivo3;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer horas4;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer minutos4;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private BigDecimal valor_hora4;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer objetivo4;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer horas5;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer minutos5;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private BigDecimal valor_hora5;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer objetivo5;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer horas6;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer minutos6;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private BigDecimal valor_hora6;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer objetivo6;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer horas7;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer minutos7;
  //@NotNull(message = "completar con 0 o valor correcto")

  //@PositiveOrZero(message = "Debe ser un número positivo o cero")
  @NotNull(message = "completar con 0 o valor correcto")
  @DecimalMin(value = "0",inclusive = true, message = "Debe ser un número positivo o cero")
  private BigDecimal valor_hora7;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer objetivo7;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer horas8;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer minutos8;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private BigDecimal valor_hora8;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer objetivo8;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer horas9;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer minutos9;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private BigDecimal valor_hora9;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer objetivo9;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer horas10;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer minutos10;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private BigDecimal valor_hora10;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Integer objetivo10;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double presentismo;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double nocturnidad;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double nocturnidad_eventual;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double bonificacion_feriados;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double reconocimiento_horas;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double carpeta_medica;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double hs_vacaciones;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double horas_adeudadas;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double licencia_maternidad;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double horas_practica;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double reintegro_cuotas_sociales;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double seguro_acc_per_vida;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double reten_monotri_per;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double seguro_vida_oblig;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double costo_habilitacion;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double apto_psi_fi;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double credisolcs;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double credisolcc;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double almacen;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double embargo_judicial;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double deposito_exceso;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double descuento_rotura_perdida;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double calzado;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double adherente_monotributo;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double cuota_social;

  private Double bonificaciones;
  private Double adelanto;

  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double prestamo;
  private String cuota_actual;
  private Double interes;
  @NotNull(message = "completar con 0 o valor correcto")
  @PositiveOrZero(message = "Debe ser un número positivo o cero")
  private Double creditogupaserviciosas;

  private String idLiquidacion;
  private String idAsociado;
  private YearMonth periodo;

  public LiquidacionActualizarDTO() {
  }

  public LiquidacionActualizarDTO(Integer horas1, Integer minutos1, BigDecimal valor_hora1, Integer objetivo1, Integer horas2, Integer minutos2, BigDecimal valor_hora2, Integer objetivo2, Integer horas3, Integer minutos3, BigDecimal valor_hora3, Integer objetivo3, Integer horas4, Integer minutos4, BigDecimal valor_hora4, Integer objetivo4, Integer horas5, Integer minutos5, BigDecimal valor_hora5, Integer objetivo5, Integer horas6, Integer minutos6, BigDecimal valor_hora6, Integer objetivo6, Integer horas7, Integer minutos7, BigDecimal valor_hora7, Integer objetivo7, Integer horas8, Integer minutos8, BigDecimal valor_hora8, Integer objetivo8, Integer horas9, Integer minutos9, BigDecimal valor_hora9, Integer objetivo9, Integer horas10, Integer minutos10, BigDecimal valor_hora10, Integer objetivo10, Double presentismo, Double nocturnidad, Double nocturnidad_eventual, Double bonificacion_feriados, Double reconocimiento_horas, Double carpeta_medica, Double hs_vacaciones, Double horas_adeudadas, Double licencia_maternidad, Double horas_practica, Double reintegro_cuotas_sociales, Double seguro_acc_per_vida, Double reten_monotri_per, Double seguro_vida_oblig, Double costo_habilitacion, Double apto_psi_fi, Double credisolcs, Double credisolcc, Double almacen, Double embargo_judicial, Double deposito_exceso, Double descuento_rotura_perdida, Double calzado, Double adherente_monotributo, Double cuota_social, Double bonificaciones, Double adelanto, Double prestamo, String cuota_actual, Double interes, Double creditogupaserviciosas, String idLiquidacion, String idAsociado, YearMonth periodo) {
    this.horas1 = horas1;
    this.minutos1 = minutos1;
    this.valor_hora1 = valor_hora1;
    this.objetivo1 = objetivo1;
    this.horas2 = horas2;
    this.minutos2 = minutos2;
    this.valor_hora2 = valor_hora2;
    this.objetivo2 = objetivo2;
    this.horas3 = horas3;
    this.minutos3 = minutos3;
    this.valor_hora3 = valor_hora3;
    this.objetivo3 = objetivo3;
    this.horas4 = horas4;
    this.minutos4 = minutos4;
    this.valor_hora4 = valor_hora4;
    this.objetivo4 = objetivo4;
    this.horas5 = horas5;
    this.minutos5 = minutos5;
    this.valor_hora5 = valor_hora5;
    this.objetivo5 = objetivo5;
    this.horas6 = horas6;
    this.minutos6 = minutos6;
    this.valor_hora6 = valor_hora6;
    this.objetivo6 = objetivo6;
    this.horas7 = horas7;
    this.minutos7 = minutos7;
    this.valor_hora7 = valor_hora7;
    this.objetivo7 = objetivo7;
    this.horas8 = horas8;
    this.minutos8 = minutos8;
    this.valor_hora8 = valor_hora8;
    this.objetivo8 = objetivo8;
    this.horas9 = horas9;
    this.minutos9 = minutos9;
    this.valor_hora9 = valor_hora9;
    this.objetivo9 = objetivo9;
    this.horas10 = horas10;
    this.minutos10 = minutos10;
    this.valor_hora10 = valor_hora10;
    this.objetivo10 = objetivo10;
    this.presentismo = presentismo;
    this.nocturnidad = nocturnidad;
    this.nocturnidad_eventual = nocturnidad_eventual;
    this.bonificacion_feriados = bonificacion_feriados;
    this.reconocimiento_horas = reconocimiento_horas;
    this.carpeta_medica = carpeta_medica;
    this.hs_vacaciones = hs_vacaciones;
    this.horas_adeudadas = horas_adeudadas;
    this.licencia_maternidad = licencia_maternidad;
    this.horas_practica = horas_practica;
    this.reintegro_cuotas_sociales = reintegro_cuotas_sociales;
    this.seguro_acc_per_vida = seguro_acc_per_vida;
    this.reten_monotri_per = reten_monotri_per;
    this.seguro_vida_oblig = seguro_vida_oblig;
    this.costo_habilitacion = costo_habilitacion;
    this.apto_psi_fi = apto_psi_fi;
    this.credisolcs = credisolcs;
    this.credisolcc = credisolcc;
    this.almacen = almacen;
    this.embargo_judicial = embargo_judicial;
    this.deposito_exceso = deposito_exceso;
    this.descuento_rotura_perdida = descuento_rotura_perdida;
    this.calzado = calzado;
    this.adherente_monotributo = adherente_monotributo;
    this.cuota_social = cuota_social;
    this.bonificaciones = bonificaciones;
    this.adelanto = adelanto;
    this.prestamo = prestamo;
    this.cuota_actual = cuota_actual;
    this.interes = interes;
    this.creditogupaserviciosas = creditogupaserviciosas;
    this.idLiquidacion = idLiquidacion;
    this.idAsociado = idAsociado;
    this.periodo = periodo;
  }

  public Integer getHoras1() {
    return horas1;
  }

  public void setHoras1(Integer horas1) {
    this.horas1 = horas1;
  }

  public Integer getMinutos1() {
    return minutos1;
  }

  public void setMinutos1(Integer minutos1) {
    this.minutos1 = minutos1;
  }

  public BigDecimal getValor_hora1() {
    return valor_hora1;
  }

  public void setValor_hora1(BigDecimal valor_hora1) {
    this.valor_hora1 = valor_hora1;
  }

  public Integer getObjetivo1() {
    return objetivo1;
  }

  public void setObjetivo1(Integer objetivo1) {
    this.objetivo1 = objetivo1;
  }

  public Integer getHoras2() {
    return horas2;
  }

  public void setHoras2(Integer horas2) {
    this.horas2 = horas2;
  }

  public Integer getMinutos2() {
    return minutos2;
  }

  public void setMinutos2(Integer minutos2) {
    this.minutos2 = minutos2;
  }

  public BigDecimal getValor_hora2() {
    return valor_hora2;
  }

  public void setValor_hora2(BigDecimal valor_hora2) {
    this.valor_hora2 = valor_hora2;
  }

  public Integer getObjetivo2() {
    return objetivo2;
  }

  public void setObjetivo2(Integer objetivo2) {
    this.objetivo2 = objetivo2;
  }

  public Integer getHoras3() {
    return horas3;
  }

  public void setHoras3(Integer horas3) {
    this.horas3 = horas3;
  }

  public Integer getMinutos3() {
    return minutos3;
  }

  public void setMinutos3(Integer minutos3) {
    this.minutos3 = minutos3;
  }

  public BigDecimal getValor_hora3() {
    return valor_hora3;
  }

  public void setValor_hora3(BigDecimal valor_hora3) {
    this.valor_hora3 = valor_hora3;
  }

  public Integer getObjetivo3() {
    return objetivo3;
  }

  public void setObjetivo3(Integer objetivo3) {
    this.objetivo3 = objetivo3;
  }

  public Integer getHoras4() {
    return horas4;
  }

  public void setHoras4(Integer horas4) {
    this.horas4 = horas4;
  }

  public Integer getMinutos4() {
    return minutos4;
  }

  public void setMinutos4(Integer minutos4) {
    this.minutos4 = minutos4;
  }

  public BigDecimal getValor_hora4() {
    return valor_hora4;
  }

  public void setValor_hora4(BigDecimal valor_hora4) {
    this.valor_hora4 = valor_hora4;
  }

  public Integer getObjetivo4() {
    return objetivo4;
  }

  public void setObjetivo4(Integer objetivo4) {
    this.objetivo4 = objetivo4;
  }

  public Integer getHoras5() {
    return horas5;
  }

  public void setHoras5(Integer horas5) {
    this.horas5 = horas5;
  }

  public Integer getMinutos5() {
    return minutos5;
  }

  public void setMinutos5(Integer minutos5) {
    this.minutos5 = minutos5;
  }

  public BigDecimal getValor_hora5() {
    return valor_hora5;
  }

  public void setValor_hora5(BigDecimal valor_hora5) {
    this.valor_hora5 = valor_hora5;
  }

  public Integer getObjetivo5() {
    return objetivo5;
  }

  public void setObjetivo5(Integer objetivo5) {
    this.objetivo5 = objetivo5;
  }

  public Integer getHoras6() {
    return horas6;
  }

  public void setHoras6(Integer horas6) {
    this.horas6 = horas6;
  }

  public Integer getMinutos6() {
    return minutos6;
  }

  public void setMinutos6(Integer minutos6) {
    this.minutos6 = minutos6;
  }

  public BigDecimal getValor_hora6() {
    return valor_hora6;
  }

  public void setValor_hora6(BigDecimal valor_hora6) {
    this.valor_hora6 = valor_hora6;
  }

  public Integer getObjetivo6() {
    return objetivo6;
  }

  public void setObjetivo6(Integer objetivo6) {
    this.objetivo6 = objetivo6;
  }

  public Integer getHoras7() {
    return horas7;
  }

  public void setHoras7(Integer horas7) {
    this.horas7 = horas7;
  }

  public Integer getMinutos7() {
    return minutos7;
  }

  public void setMinutos7(Integer minutos7) {
    this.minutos7 = minutos7;
  }

  public BigDecimal getValor_hora7() {
    return valor_hora7;
  }

  public void setValor_hora7(BigDecimal valor_hora7) {
    this.valor_hora7 = valor_hora7;
  }

  public Integer getObjetivo7() {
    return objetivo7;
  }

  public void setObjetivo7(Integer objetivo7) {
    this.objetivo7 = objetivo7;
  }

  public Integer getHoras8() {
    return horas8;
  }

  public void setHoras8(Integer horas8) {
    this.horas8 = horas8;
  }

  public Integer getMinutos8() {
    return minutos8;
  }

  public void setMinutos8(Integer minutos8) {
    this.minutos8 = minutos8;
  }

  public BigDecimal getValor_hora8() {
    return valor_hora8;
  }

  public void setValor_hora8(BigDecimal valor_hora8) {
    this.valor_hora8 = valor_hora8;
  }

  public Integer getObjetivo8() {
    return objetivo8;
  }

  public void setObjetivo8(Integer objetivo8) {
    this.objetivo8 = objetivo8;
  }

  public Integer getHoras9() {
    return horas9;
  }

  public void setHoras9(Integer horas9) {
    this.horas9 = horas9;
  }

  public Integer getMinutos9() {
    return minutos9;
  }

  public void setMinutos9(Integer minutos9) {
    this.minutos9 = minutos9;
  }

  public BigDecimal getValor_hora9() {
    return valor_hora9;
  }

  public void setValor_hora9(BigDecimal valor_hora9) {
    this.valor_hora9 = valor_hora9;
  }

  public Integer getObjetivo9() {
    return objetivo9;
  }

  public void setObjetivo9(Integer objetivo9) {
    this.objetivo9 = objetivo9;
  }

  public Integer getHoras10() {
    return horas10;
  }

  public void setHoras10(Integer horas10) {
    this.horas10 = horas10;
  }

  public Integer getMinutos10() {
    return minutos10;
  }

  public void setMinutos10(Integer minutos10) {
    this.minutos10 = minutos10;
  }

  public BigDecimal getValor_hora10() {
    return valor_hora10;
  }

  public void setValor_hora10(BigDecimal valor_hora10) {
    this.valor_hora10 = valor_hora10;
  }

  public Integer getObjetivo10() {
    return objetivo10;
  }

  public void setObjetivo10(Integer objetivo10) {
    this.objetivo10 = objetivo10;
  }

  public Double getPresentismo() {
    return presentismo;
  }

  public void setPresentismo(Double presentismo) {
    this.presentismo = presentismo;
  }

  public Double getNocturnidad() {
    return nocturnidad;
  }

  public void setNocturnidad(Double nocturnidad) {
    this.nocturnidad = nocturnidad;
  }

  public Double getNocturnidad_eventual() {
    return nocturnidad_eventual;
  }

  public void setNocturnidad_eventual(Double nocturnidad_eventual) {
    this.nocturnidad_eventual = nocturnidad_eventual;
  }

  public Double getBonificacion_feriados() {
    return bonificacion_feriados;
  }

  public void setBonificacion_feriados(Double bonificacion_feriados) {
    this.bonificacion_feriados = bonificacion_feriados;
  }

  public Double getReconocimiento_horas() {
    return reconocimiento_horas;
  }

  public void setReconocimiento_horas(Double reconocimiento_horas) {
    this.reconocimiento_horas = reconocimiento_horas;
  }

  public Double getCarpeta_medica() {
    return carpeta_medica;
  }

  public void setCarpeta_medica(Double carpeta_medica) {
    this.carpeta_medica = carpeta_medica;
  }

  public Double getHs_vacaciones() {
    return hs_vacaciones;
  }

  public void setHs_vacaciones(Double hs_vacaciones) {
    this.hs_vacaciones = hs_vacaciones;
  }

  public Double getHoras_adeudadas() {
    return horas_adeudadas;
  }

  public void setHoras_adeudadas(Double horas_adeudadas) {
    this.horas_adeudadas = horas_adeudadas;
  }

  public Double getLicencia_maternidad() {
    return licencia_maternidad;
  }

  public void setLicencia_maternidad(Double licencia_maternidad) {
    this.licencia_maternidad = licencia_maternidad;
  }

  public Double getHoras_practica() {
    return horas_practica;
  }

  public void setHoras_practica(Double horas_practica) {
    this.horas_practica = horas_practica;
  }

  public Double getReintegro_cuotas_sociales() {
    return reintegro_cuotas_sociales;
  }

  public void setReintegro_cuotas_sociales(Double reintegro_cuotas_sociales) {
    this.reintegro_cuotas_sociales = reintegro_cuotas_sociales;
  }

  public Double getSeguro_acc_per_vida() {
    return seguro_acc_per_vida;
  }

  public void setSeguro_acc_per_vida(Double seguro_acc_per_vida) {
    this.seguro_acc_per_vida = seguro_acc_per_vida;
  }

  public Double getReten_monotri_per() {
    return reten_monotri_per;
  }

  public void setReten_monotri_per(Double reten_monotri_per) {
    this.reten_monotri_per = reten_monotri_per;
  }

  public Double getSeguro_vida_oblig() {
    return seguro_vida_oblig;
  }

  public void setSeguro_vida_oblig(Double seguro_vida_oblig) {
    this.seguro_vida_oblig = seguro_vida_oblig;
  }

  public Double getCosto_habilitacion() {
    return costo_habilitacion;
  }

  public void setCosto_habilitacion(Double costo_habilitacion) {
    this.costo_habilitacion = costo_habilitacion;
  }

  public Double getApto_psi_fi() {
    return apto_psi_fi;
  }

  public void setApto_psi_fi(Double apto_psi_fi) {
    this.apto_psi_fi = apto_psi_fi;
  }

  public Double getCredisolcs() {
    return credisolcs;
  }

  public void setCredisolcs(Double credisolcs) {
    this.credisolcs = credisolcs;
  }

  public Double getCredisolcc() {
    return credisolcc;
  }

  public void setCredisolcc(Double credisolcc) {
    this.credisolcc = credisolcc;
  }

  public Double getAlmacen() {
    return almacen;
  }

  public void setAlmacen(Double almacen) {
    this.almacen = almacen;
  }

  public Double getEmbargo_judicial() {
    return embargo_judicial;
  }

  public void setEmbargo_judicial(Double embargo_judicial) {
    this.embargo_judicial = embargo_judicial;
  }

  public Double getDeposito_exceso() {
    return deposito_exceso;
  }

  public void setDeposito_exceso(Double deposito_exceso) {
    this.deposito_exceso = deposito_exceso;
  }

  public Double getDescuento_rotura_perdida() {
    return descuento_rotura_perdida;
  }

  public void setDescuento_rotura_perdida(Double descuento_rotura_perdida) {
    this.descuento_rotura_perdida = descuento_rotura_perdida;
  }

  public Double getCalzado() {
    return calzado;
  }

  public void setCalzado(Double calzado) {
    this.calzado = calzado;
  }

  public Double getAdherente_monotributo() {
    return adherente_monotributo;
  }

  public void setAdherente_monotributo(Double adherente_monotributo) {
    this.adherente_monotributo = adherente_monotributo;
  }

  public Double getCuota_social() {
    return cuota_social;
  }

  public void setCuota_social(Double cuota_social) {
    this.cuota_social = cuota_social;
  }

  public Double getBonificaciones() {
    return bonificaciones;
  }

  public void setBonificaciones(Double bonificaciones) {
    this.bonificaciones = bonificaciones;
  }

  public Double getAdelanto() {
    return adelanto;
  }

  public void setAdelanto(Double adelanto) {
    this.adelanto = adelanto;
  }

  public Double getPrestamo() {
    return prestamo;
  }

  public void setPrestamo(Double prestamo) {
    this.prestamo = prestamo;
  }

  public String getCuota_actual() {
    return cuota_actual;
  }

  public void setCuota_actual(String cuota_actual) {
    this.cuota_actual = cuota_actual;
  }

  public Double getInteres() {
    return interes;
  }

  public void setInteres(Double interes) {
    this.interes = interes;
  }

  public Double getCreditogupaserviciosas() {
    return creditogupaserviciosas;
  }

  public void setCreditogupaserviciosas(Double creditogupaserviciosas) {
    this.creditogupaserviciosas = creditogupaserviciosas;
  }

  public String getIdLiquidacion() {
    return idLiquidacion;
  }

  public void setIdLiquidacion(String idLiquidacion) {
    this.idLiquidacion = idLiquidacion;
  }

  public String getIdAsociado() {
    return idAsociado;
  }

  public void setIdAsociado(String idAsociado) {
    this.idAsociado = idAsociado;
  }

  public YearMonth getPeriodo() {
    return periodo;
  }

  public void setPeriodo(YearMonth periodo) {
    this.periodo = periodo;
  }
}
