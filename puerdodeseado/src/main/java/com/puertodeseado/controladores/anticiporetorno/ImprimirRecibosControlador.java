package com.puertodeseado.controladores.anticiporetorno;


import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.entidades.anticiporetorno.LiquidacionHistorial;
import com.puertodeseado.entidades.anticiporetorno.ObjetivosMain;
import com.puertodeseado.repositorio.anticipoderetorno.LiquidacionHistorialRepositorio;
import com.puertodeseado.servicio.anticiporetorno.ImprimirReciboServicio;
import com.puertodeseado.servicio.anticiporetorno.ImprimirRecibosPorObjetivoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;


import java.time.YearMonth;
import java.util.*;

@Controller
@RequestMapping("/imprimirRecibos")
public class ImprimirRecibosControlador {

    @Autowired
    private ImprimirReciboServicio imprimirReciboServicio;
    @Autowired
    private LiquidacionHistorialRepositorio liquidacionHistorialRepositorio;
    @Autowired
    private ImprimirRecibosPorObjetivoServicio imprimirRecibosPorObjetivoServicio;


    @GetMapping("/vistaImprimirRecibos")
    public String vistaImprimirRecibos() {
        return "imprimirRecibos.html";
    }


    // -------------------------------------------------------------------------------------
    // ---------------------------------- TODOS --------------------------------------------
    // -------------------------------------------------------------------------------------

    // consumido desde imprimirRecibos.html
    @GetMapping("/menuTodos")
    public String vistaTodos() {

        // consume imprimirRecibos.js
        return "imprimirTodos.html";
    }

    // consumido desde imprimirTodos.js
    @ResponseBody
    @GetMapping("/verTodos")
    public List<LiquidacionHistorial> verTodos(@RequestParam("periodo") String periodo) {
        return liquidacionHistorialRepositorio.imprimirTodos(periodo);
    }

    // consumido desde imprimirRecibos2.js
    @GetMapping("/verRecibo")
    @ResponseBody
    public ResponseEntity<Map<String, Object>> verRecibo(@RequestParam("periodo") YearMonth periodo) {
        Map<String, Object> response = new HashMap<>();
        try {
            Integer objetivo = 0;
            String tipoImpresion = "todos";
            String idAsoc = "";

            String respuesta = imprimirReciboServicio.imprimirRecibos(periodo, objetivo, tipoImpresion, idAsoc).get("recibo").toString();

            if (respuesta.equals("liquidación no existe")) {
                response.put("success", false);
                response.put("message", respuesta);
                return ResponseEntity.badRequest().body(response);
            } else {
                response.put("success", true);
                response.put("periodo", periodo.toString());
                return ResponseEntity.ok(response);
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", "An unexpected error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    // consumido desde imprimirRecibos2.js
    @GetMapping("/imprimirTodos")
    public String imprimir(@RequestParam("periodo") YearMonth periodo,
                           @RequestParam("tipoFetch") String tipoFetch,
                           Model model) {
        model.addAttribute("periodo", periodo);
        model.addAttribute("tipoFetch", tipoFetch);

        return "reciboAnticipo";
    }

    // cosumido por el switch desde reciboAnticipo.js
    @ResponseBody
    @GetMapping("/todos")
    public Map<String, Object> imprimirTodos(@RequestParam("periodo") YearMonth periodo) {
        Integer objetivo = 0;
        String tipoImpresion = "todos";
        String idAsoc = "";
        return imprimirReciboServicio.imprimirRecibos(periodo, objetivo, tipoImpresion, idAsoc);
    }

    // -------------------------------------------------------------------------------------
    // ---------------------------------- POR OBJETIVO -------------------------------------
    // -------------------------------------------------------------------------------------

    // consumido desde imprimirRecibos.html
    @GetMapping("/porObjetivo")
    public String imprimirPorObjetivo() {
        System.out.println("porObjetivo");
        return "imprimirPorObjetivo";
    }

    // consumido desde imprimirPorObjetivo.js
    // consulto y obtengo de la BBDD los periodos disponibles con recibos sin imprimir  para luego listarlas en el periodoSelect
    @ResponseBody
    @GetMapping("/listarPeriodos")
    public List<String> listarPeriodos() {
        System.out.println("listarPeriodos");
        return liquidacionHistorialRepositorio.listarPeriodos();
    }


    // consumido desde imprimirPorObjetivo.js
    // completa el select de objetivos, segun el período elejido
    @ResponseBody
    @GetMapping("/listarObjetivos")
    public List<Optional<ObjetivosMain>> listarObjetivos(@RequestParam("periodo") String periodo) {
        System.out.println("listarObjetivos");
        return imprimirRecibosPorObjetivoServicio.listarObjetivos(periodo);
    }

    // consumido desde imprimirPorObjetivo.js
    @ResponseBody
    @GetMapping("/objetivos")
    public List<LiquidacionHistorial> objetivos(@RequestParam("periodo") String periodo,
                                                @RequestParam("objetivo") Integer objetivo) {
        System.out.println("objetivos");
        return liquidacionHistorialRepositorio.imprimirPorObjetivo(periodo, objetivo);
    }

    // consumido desde imprimirPorObjetivo.js
    @GetMapping("/imprimirObjetivo")
    public String imprimirObjetivo(@RequestParam("periodo") YearMonth periodo,
                                   @RequestParam("objetivo") Integer objetivo,
                                   @RequestParam("tipoFetch") String tipoFetch,
                                   Model model) {
        model.addAttribute("periodo", periodo);
        model.addAttribute("objetivo", objetivo);
        model.addAttribute("tipoFetch", tipoFetch);

        System.out.println("imprimirObjetivo");

        return "reciboAnticipo";
    }


    // cosumido por el switch desde reciboAnticipo.js
    @ResponseBody
    @GetMapping("/objetivoImprimir")
    public Map<String, Object> imprimirPorObjetivo(@RequestParam("periodo") YearMonth periodo,
                                                   @RequestParam("objetivo") Integer objetivo) {


        System.out.println("objetivoImprimir");

        String tipoImpresion = "objetivo";
        String idAsoc = "";

        return imprimirReciboServicio.imprimirRecibos(periodo, objetivo, tipoImpresion, idAsoc);
    }

    // -------------------------------------------------------------------------------------
    // ---------------------------------- CUBRE FRANCO -------------------------------------
    // -------------------------------------------------------------------------------------


    // consumido desde imprimirRecibos.html
    @GetMapping("/vistaCubreFranco")
    public String vistaCubreFranco() {
        System.out.println("porObjetivo");
        return "imprimirCubreFranco";
    }


    // consumido desde imprimirPorObjetivo.js
    @ResponseBody
    @GetMapping("/cubreFranco")
    public List<LiquidacionHistorial> cubreFranco(@RequestParam("periodo") String periodo) {
        System.out.println("objetivos");
        return liquidacionHistorialRepositorio.imprimirCubreFranco(periodo);

    }

    // consumido desde imprimirCubreFranco.js
    @GetMapping("/imprimirCubreFranco")
    public String imprimirCubreFranco(@RequestParam("periodo") YearMonth periodo,
                                      @RequestParam("tipoFetch") String tipoFetch,
                                      Model model) {
        model.addAttribute("periodo", periodo);
        model.addAttribute("tipoFetch", tipoFetch);

        return "reciboAnticipo";
    }


    // cosumido por el switch desde reciboAnticipo.js
    @ResponseBody
    @GetMapping("/cubreFrancoImprimir")
    public Map<String, Object> imprimirPorCubreFranco(@RequestParam("periodo") YearMonth periodo) {

        Integer objetivo = 0;
        String tipoImpresion = "cubreFranco";
        String idAsoc = "";

        return imprimirReciboServicio.imprimirRecibos(periodo, objetivo, tipoImpresion, idAsoc);
    }

    // -------------------------------------------------------------------------------------
    // ------------------------------------ UNO SOLO ---------------------------------------
    // -------------------------------------------------------------------------------------

    // consumido desde imprimirRecibos.html
    @GetMapping("/vistaUnoSolo")
    public String vistaUnoSolo() {
        System.out.println("porObjetivo");
        return "imprimirUnoSolo";
    }

    @ResponseBody
    @GetMapping("/listarAsociadosLiquidados")
    public List<Optional<AsociadosMain>> listarAsociadosLiquidados(@RequestParam("periodo") String periodo) {
        return imprimirReciboServicio.listarAsociadosLiquidados(periodo);
    }

    // consumido desde imprimirUnoSolo.js
    @ResponseBody
    @GetMapping("/asociados")
    public List<LiquidacionHistorial> asociados(@RequestParam("periodo") String periodo,
                                                @RequestParam("idAsoc") String idAsoc) {
        System.out.println("asociados");
        return liquidacionHistorialRepositorio.imprimirUnoSolo(periodo, idAsoc);
    }

    // consumido desde imprimirUnoSolo.js
    @GetMapping("/imprimirUnoSolo")
    public String imprimirUnoSolo(@RequestParam("periodo") YearMonth periodo,
                                  @RequestParam("idAsoc") String idAsoc,
                                  @RequestParam("tipoFetch") String tipoFetch,
                                  Model model) {
        model.addAttribute("periodo", periodo);
        model.addAttribute("idAsoc", idAsoc);
        model.addAttribute("tipoFetch", tipoFetch);

        System.out.println("imprimirObjetivo");

        return "reciboAnticipo";
    }

    // cosumido por el switch desde reciboAnticipo.js
    @ResponseBody
    @GetMapping("/unoSoloImprimir")
    public Map<String, Object> imprimirUnoSolo(@RequestParam("periodo") YearMonth periodo,
                                               @RequestParam("idAsoc") String idAsoc) {

        System.out.println("unoSoloImprimir");

        Integer objetivo = 0;
        String tipoImpresion = "unoSolo";


        return imprimirReciboServicio.imprimirRecibos(periodo, objetivo, tipoImpresion, idAsoc);
    }

    // consumido desde reciboAnticipo.js
    @PostMapping("/modificarEstadoImpresion")
    @ResponseBody
    public void modificarEstadoImpresion(@RequestBody List<String> idsLiqui) {
        imprimirReciboServicio.modificarEstadoImpresion(idsLiqui);

    }


}
