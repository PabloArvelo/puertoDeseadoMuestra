package com.puertodeseado.servicio.anticiporetorno;

import com.puertodeseado.EntidadesDTO.anticipoderetorno.ReciboConceptosDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.RecibosImprimirDTO;
import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.entidades.anticiporetorno.ImprimirRecibo;
import com.puertodeseado.entidades.anticiporetorno.LiquidacionHistorial;
import com.puertodeseado.repositorio.AsociadosMainRepositorio;
import com.puertodeseado.repositorio.anticipoderetorno.ImprimirRecibosRepositorio;
import com.puertodeseado.repositorio.anticipoderetorno.LiquidacionHistorialRepositorio;
import com.tenpisoft.n2w.MoneyConverters;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ImprimirReciboServicio {

    @Autowired
    private ImprimirRecibosRepositorio imprimirRecibosRepositorio;
    @Autowired
    private LiquidacionHistorialRepositorio liquidacionHistorialRepositorio;
    @Autowired
    private AsociadosMainRepositorio asociadosMainRepositorio;


    public Map<String, Object> imprimirRecibos(YearMonth periodo, Integer objetivo, String tipoImpresion, String idAsoc) {

        String periodoString = periodo.toString();

        Map<String, Object> respuesta = new HashMap<>();

        List<ImprimirRecibo> recibosImprimir = new ArrayList<>();

        List<LiquidacionHistorial> liquidaciones = new ArrayList<>();



        // crea la lista de impresion elegida, segun el tipo de impresión enviado desde el controlador
        switch (tipoImpresion) {
            case "todos":
                liquidaciones = liquidacionHistorialRepositorio.imprimirTodos(periodoString);
                break;
            case "objetivo":
                liquidaciones = liquidacionHistorialRepositorio.imprimirPorObjetivo(periodoString, objetivo);
                break;
            case "cubreFranco":
                liquidaciones = liquidacionHistorialRepositorio.imprimirCubreFranco(periodoString);
                break;
            case "unoSolo":
                liquidaciones = liquidacionHistorialRepositorio.imprimirUnoSolo(periodoString, idAsoc);
                break;
        }


        // contiene todas las liquidaciones que necesito imprimir


        // valido que la liquidación exista
        if (liquidaciones.isEmpty()) {
            String esVacio = "Liquidación ya se imprimió o no existe";
            respuesta.put("recibo", esVacio);
            return respuesta;

        } else {

            // creo la lista con las instancias
            for (LiquidacionHistorial liqui : liquidaciones) {

                ImprimirRecibo recibo = new ImprimirRecibo();

                recibo.setAsociadosMain(liqui.getAsociadosMain());

                LiquidacionHistorial liquidacion = liquidacionHistorialRepositorio.getReferenceById(liqui.getId());

                recibo.setLiquidacionHistorial(liquidacion);

                recibosImprimir.add(recibo);
            }


            List<RecibosImprimirDTO> recibosImprimirDTO = new ArrayList<>();


            for (ImprimirRecibo recibo : recibosImprimir) {
                List<ReciboConceptosDTO> listaConceptosDTO = new ArrayList<>();

                String horas1 = recibo.getLiquidacionHistorial().getHoras1() + ":" + recibo.getLiquidacionHistorial().getMinutos1();
                String horas2 = recibo.getLiquidacionHistorial().getHoras2() + ":" + recibo.getLiquidacionHistorial().getMinutos2();
                String horas3 = recibo.getLiquidacionHistorial().getHoras3() + ":" + recibo.getLiquidacionHistorial().getMinutos3();
                String horas4 = recibo.getLiquidacionHistorial().getHoras4() + ":" + recibo.getLiquidacionHistorial().getMinutos4();
                String horas5 = recibo.getLiquidacionHistorial().getHoras5() + ":" + recibo.getLiquidacionHistorial().getMinutos5();
                String horas6 = recibo.getLiquidacionHistorial().getHoras6() + ":" + recibo.getLiquidacionHistorial().getMinutos6();
                String horas7 = recibo.getLiquidacionHistorial().getHoras7() + ":" + recibo.getLiquidacionHistorial().getMinutos7();
                String horas8 = recibo.getLiquidacionHistorial().getHoras8() + ":" + recibo.getLiquidacionHistorial().getMinutos8();
                String horas9 = recibo.getLiquidacionHistorial().getHoras9() + ":" + recibo.getLiquidacionHistorial().getMinutos9();
                String horas10 = recibo.getLiquidacionHistorial().getHoras10() + ":" + recibo.getLiquidacionHistorial().getMinutos10();

                listaConceptosDTO.add(new ReciboConceptosDTO("Horas trabajadas mes " + periodoString, horas1, recibo.getLiquidacionHistorial().getValorHora1().doubleValue(), calculaPercepcionPorHoras(recibo.getLiquidacionHistorial().getHoras1(),
                        recibo.getLiquidacionHistorial().getMinutos1(),
                        recibo.getLiquidacionHistorial().getValorHora1()).doubleValue(), 0.00, 0.00, 0.00, 0.00));

                listaConceptosDTO.add(new ReciboConceptosDTO("Horas trabajadas mes " + periodoString, horas2, recibo.getLiquidacionHistorial().getValorHora2().doubleValue(), calculaPercepcionPorHoras(recibo.getLiquidacionHistorial().getHoras2(),
                        recibo.getLiquidacionHistorial().getMinutos2(),
                        recibo.getLiquidacionHistorial().getValorHora2()).doubleValue(), 0.00, 0.00, 0.00, 0.00));

                listaConceptosDTO.add(new ReciboConceptosDTO("Horas trabajadas mes " + periodoString, horas3, recibo.getLiquidacionHistorial().getValorHora3().doubleValue(), calculaPercepcionPorHoras(recibo.getLiquidacionHistorial().getHoras3(),
                        recibo.getLiquidacionHistorial().getMinutos3(),
                        recibo.getLiquidacionHistorial().getValorHora3()).doubleValue(), 0.00, 0.00, 0.00, 0.00));

                listaConceptosDTO.add(new ReciboConceptosDTO("Horas trabajadas mes " + periodoString, horas4, recibo.getLiquidacionHistorial().getValorHora4().doubleValue(), calculaPercepcionPorHoras(recibo.getLiquidacionHistorial().getHoras4(),
                        recibo.getLiquidacionHistorial().getMinutos4(),
                        recibo.getLiquidacionHistorial().getValorHora4()).doubleValue(), 0.00, 0.00, 0.00, 0.00));

                listaConceptosDTO.add(new ReciboConceptosDTO("Horas trabajadas mes " + periodoString, horas5, recibo.getLiquidacionHistorial().getValorHora5().doubleValue(), calculaPercepcionPorHoras(recibo.getLiquidacionHistorial().getHoras5(),
                        recibo.getLiquidacionHistorial().getMinutos5(),
                        recibo.getLiquidacionHistorial().getValorHora5()).doubleValue(), 0.00, 0.00, 0.00, 0.00));

                listaConceptosDTO.add(new ReciboConceptosDTO("Horas trabajadas mes " + periodoString, horas6, recibo.getLiquidacionHistorial().getValorHora6().doubleValue(), calculaPercepcionPorHoras(recibo.getLiquidacionHistorial().getHoras6(),
                        recibo.getLiquidacionHistorial().getMinutos6(),
                        recibo.getLiquidacionHistorial().getValorHora6()).doubleValue(), 0.00, 0.00, 0.00, 0.00));

                listaConceptosDTO.add(new ReciboConceptosDTO("Horas trabajadas mes " + periodoString, horas7, recibo.getLiquidacionHistorial().getValorHora7().doubleValue(), calculaPercepcionPorHoras(recibo.getLiquidacionHistorial().getHoras7(),
                        recibo.getLiquidacionHistorial().getMinutos7(),
                        recibo.getLiquidacionHistorial().getValorHora7()).doubleValue(), 0.00, 0.00, 0.00, 0.00));

                listaConceptosDTO.add(new ReciboConceptosDTO("Horas trabajadas mes " + periodoString, horas8, recibo.getLiquidacionHistorial().getValorHora8().doubleValue(), calculaPercepcionPorHoras(recibo.getLiquidacionHistorial().getHoras8(),
                        recibo.getLiquidacionHistorial().getMinutos8(),
                        recibo.getLiquidacionHistorial().getValorHora8()).doubleValue(), 0.00, 0.00, 0.00, 0.00));

                listaConceptosDTO.add(new ReciboConceptosDTO("Horas trabajadas mes " + periodoString, horas9, recibo.getLiquidacionHistorial().getValorHora9().doubleValue(), calculaPercepcionPorHoras(recibo.getLiquidacionHistorial().getHoras9(),
                        recibo.getLiquidacionHistorial().getMinutos9(),
                        recibo.getLiquidacionHistorial().getValorHora9()).doubleValue(), 0.00, 0.00, 0.00, 0.00));

                listaConceptosDTO.add(new ReciboConceptosDTO("Horas trabajadas mes " + periodoString, horas10, recibo.getLiquidacionHistorial().getValorHora10().doubleValue(), calculaPercepcionPorHoras(recibo.getLiquidacionHistorial().getHoras10(),
                        recibo.getLiquidacionHistorial().getMinutos10(),
                        recibo.getLiquidacionHistorial().getValorHora10()).doubleValue(), 0.00, 0.00, 0.00, 0.00));


                // esto va a al haber
                listaConceptosDTO.add(new ReciboConceptosDTO("Presentismo", "", 0.00, recibo.getLiquidacionHistorial().getPresentismo(), 0.00, 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Nocturnidad", "", 0.00, recibo.getLiquidacionHistorial().getNocturnidad(), 0.00, 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Nocturnidad Eventual", "", 0.00, recibo.getLiquidacionHistorial().getNocturnidadEventual(), 0.00, 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Bonificaciones", "", 0.00, recibo.getLiquidacionHistorial().getBonificaciones(), 0.00, 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Bonificaciones Feriado", "", 0.00, recibo.getLiquidacionHistorial().getBonificacionFeriados(), 0.00, 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Reconocimiento Horas", "", 0.00, recibo.getLiquidacionHistorial().getReconocimientoHoras(), 0.00, 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Reconocimiento Horas", "", 0.00, recibo.getLiquidacionHistorial().getReconocimientoHoras(), 0.00, 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Carpeta Médica", "", 0.00, recibo.getLiquidacionHistorial().getCarpetaMedica(), 0.00, 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Horas Vacaciones", "", 0.00, recibo.getLiquidacionHistorial().getHsVacaciones(), 0.00, 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("horas Adeudadas", "", 0.00, recibo.getLiquidacionHistorial().getHorasAdeudadas(), 0.00, 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Licencia por Maternidad", "", 0.00, recibo.getLiquidacionHistorial().getLicenciaMaternidad(), 0.00, 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Horas Práctica", "", 0.00, recibo.getLiquidacionHistorial().getHorasPractica(), 0.00, 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Reintegro Cuotas Sociales", "", 0.00, recibo.getLiquidacionHistorial().getReintegroCuotasSociales(), 0.00, 0.00, 0.00, 0.00));

                // esto va a al debe
                listaConceptosDTO.add(new ReciboConceptosDTO("Cuota Social", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getCuotaSocial(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Retención Monotributo", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getRetenMonotriPer(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Seguro Acc. Per. + Vida", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getSeguroAccPer_Vida(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Seguro Vida Obligatorio", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getSeguroVidaOblig(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Costo Habilitación", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getCostoHabilitacion(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Apto Psicológico", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getAptoPsiFi(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Cuota Social Credisol", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getCredisolCS(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Crédito Credisol", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getCredisolCC(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Almacén", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getAlmacen(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Cuota Alimentaria", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getEmbargoJudicial(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Depósito en Exceso", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getDepositoExceso(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Descuento rotura/perdida", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getDescuentoRoturaPerdida(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Calzado", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getCalzado(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Adherente monotributo", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getAdherenteMonotributo(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Adelantos", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getAdelanto(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Préstamo cuota Nº: " + recibo.getLiquidacionHistorial().getCuotaActual(), "", 0.00, 0.0, recibo.getLiquidacionHistorial().getPrestamo(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Credito Gupa SAS", "", 0.00, 0.0, recibo.getLiquidacionHistorial().getCreditoGUPAServicioSAS(), 0.00, 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Total Bruto", "", 0.0, 0.0, 0.00, recibo.getLiquidacionHistorial().getTotalBruto().doubleValue(), 0.00, 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Total Deducciones", "", 0.0, 0.0, 0.00, 0.00, recibo.getLiquidacionHistorial().getTotalDeducciones().doubleValue(), 0.00));
                listaConceptosDTO.add(new ReciboConceptosDTO("Total Neto", "", 0.0, 0.0, 0.00, 0.00, 0.00, recibo.getLiquidacionHistorial().getTotalAnticipo().doubleValue()));


                // convierto el total a cobrar a pesos
                MoneyConverters convertir = MoneyConverters.SPANISH_BANKING_MONEY_VALUE;
                String texto = convertir.asWords(recibo.getLiquidacionHistorial().getTotalAnticipo());


                // limpio la lista de conceptos para que no retorne conceptos no utilizados en la liquidación de cada asociado
                listaConceptosDTO.removeIf(concepto -> concepto.getHaberes() == 0
                        && concepto.getDeducciones() == 0
                        && concepto.getTotalDeducciones() == 0
                        && concepto.getTotalBruto() == 0
                        && concepto.getTotalNeto() == 0);


                // extraigo los últimos tres dígitos del DNI para agregarlo como prefijo al número de anticipo de retorno
                int dni = recibo.getAsociadosMain().getDni();
                String dniString = String.valueOf(dni);
                String dniUltimosTresDig = dniString.substring(dniString.length() - 3);


                // obtengo el numero de anticipo para agregarlo como sufijo al número de anticipo de retorno
                int anticipoNumero = recibo.getLiquidacionHistorial().getAnticipoNumero();

                // obtengo la feha actual
                LocalDate fechaActual = LocalDate.now();
                DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                String fechaDePago = fechaActual.format(formateador);

                // obtengo el período de liquidación
                String periodoLiqui = (recibo.getLiquidacionHistorial().getPeriodo()).toString();

                // obtengo el id de liquidación
                String idLiqui = recibo.getLiquidacionHistorial().getId();


                // instancio del DTO que será retornado
                RecibosImprimirDTO reciboImprimirDTO = new RecibosImprimirDTO();

                reciboImprimirDTO.setIdLiqui(idLiqui);
                reciboImprimirDTO.setAsociado(recibo.getAsociadosMain());
                reciboImprimirDTO.setConceptos(listaConceptosDTO);
                reciboImprimirDTO.setTotalTexto(texto);
                reciboImprimirDTO.setFechaDePago(fechaDePago);
                reciboImprimirDTO.setPeriodoLiquidacion(periodoLiqui);
                reciboImprimirDTO.setAnticipoNumero(dniUltimosTresDig + "-" + anticipoNumero);

                recibosImprimirDTO.add(reciboImprimirDTO);


            }
            respuesta.put("recibo", recibosImprimirDTO);
            return respuesta;
        }

    }


    public BigDecimal calculaPercepcionPorHoras(int horas, int minutos, BigDecimal valorHora) {
        // Convierte las horas y minutos trabajados a horas decimales
        BigDecimal horasTrabajadas = new BigDecimal(horas)
                .add(new BigDecimal(minutos).divide(new BigDecimal(60), 2, RoundingMode.HALF_UP));

        // Calcula el total del pago multiplicando horas trabajadas por valor hora
        return horasTrabajadas.multiply(valorHora).setScale(2, RoundingMode.HALF_UP);  // Redondea a dos decimales
    }

    public List<Optional<AsociadosMain>> listarAsociadosLiquidados(@RequestParam("periodo") String periodo) {
        List<Object> listadoIdAsoc = liquidacionHistorialRepositorio.listarAsociadosLiquidados(periodo);

        List<Optional<AsociadosMain>> listadoAsociados = new ArrayList<>();

        for (Object idAsoc : listadoIdAsoc) {

            Optional<AsociadosMain> asociado = asociadosMainRepositorio.findById(idAsoc.toString());
            listadoAsociados.add(asociado);
        }

        // Extraer los valores de Optional y luego ordenar
        List<AsociadosMain> asociadosOrdenados = listadoAsociados.stream()
                .filter(Optional::isPresent) // Filtrar los Optional vacíos
                .map(Optional::get) // Extraer el objeto AsociadosMain
                .sorted(Comparator.comparing(AsociadosMain::getApellido)
                        .thenComparing(AsociadosMain::getNombre)) // Ordenar por apellido y luego por nombre
                .toList();

        List<Optional<AsociadosMain>> listaAsociadosOpcionalOrdenada = asociadosOrdenados.stream()
                .map(Optional::of) // Envolver cada elemento en un Optional
                .toList();

        


        return listaAsociadosOpcionalOrdenada;
    }

    @Transactional
    public void modificarEstadoImpresion(List<String> idsLiqui){
        liquidacionHistorialRepositorio.modificarEstadoImpresion(idsLiqui);
    }


}
