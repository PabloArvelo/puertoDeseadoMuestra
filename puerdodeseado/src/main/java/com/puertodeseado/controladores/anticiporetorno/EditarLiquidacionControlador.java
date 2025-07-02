package com.puertodeseado.controladores.anticiporetorno;


import com.puertodeseado.EntidadesDTO.RespuestaDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.DatosSesionDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.LiquidacionActualizarDTO;
import com.puertodeseado.EntidadesDTO.anticipoderetorno.LiquidacionesPorPeriodoDTO;
import com.puertodeseado.entidades.anticiporetorno.LiquidacionHistorial;
import com.puertodeseado.entidades.anticiporetorno.ObjetivosMain;
import com.puertodeseado.repositorio.anticipoderetorno.LiquidacionHistorialRepositorio;
import com.puertodeseado.repositorio.anticipoderetorno.ObjetivosMainRepositorio;
import com.puertodeseado.servicio.anticiporetorno.EditarLiquidacionServicio;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/editarLiquidacion")
public class EditarLiquidacionControlador {

    @Autowired
    private EditarLiquidacionServicio editarLiquidacionServicio;
    @Autowired
    private LiquidacionHistorialRepositorio liquidacionHistorialRepositorio;
    @Autowired
    private ObjetivosMainRepositorio objetivosMainRepositorio;

    public static final Logger log = LoggerFactory.getLogger(EditarLiquidacionControlador.class);


    @GetMapping("/debugSession")
    @ResponseBody
    public Map<String, Object> debugSession(HttpSession session) {
        Map<String, Object> response = new HashMap<>();
//        response.put("idAsoc", session.getAttribute("idAsoc"));
//        response.put("periodo", session.getAttribute("periodo"));
        response.put("sessionId", session.getId());
        return response;
    }

    @GetMapping("/vistaEditarLiquidacion")
    public String vistaEditarLiquidacion(){

        return "editarLiquidacion.html";
    }



    @GetMapping("/liquidacionesPorPeriodo")
    @ResponseBody
    public List<LiquidacionesPorPeriodoDTO> liquidacionesPorPeriodo(@RequestParam YearMonth periodo){
        List<LiquidacionesPorPeriodoDTO> listaLiquidaciones = editarLiquidacionServicio.liquidacionesPorPeriodo(periodo);
        listaLiquidaciones.sort(Comparator.comparing(LiquidacionesPorPeriodoDTO::getApellido));
        return listaLiquidaciones;
    }



    @GetMapping("/buscarLiquidacionPorAsocPeriodo")
    @ResponseBody
    public Map<String, Object> buscarLiquidacionPorAsocPeriodo(HttpSession session){





        String idAsoc = (String) session.getAttribute("idAsoc");
        String periodo = (String) session.getAttribute("periodo");

        List<ObjetivosMain> objetivos = objetivosMainRepositorio.findAll();
        LiquidacionHistorial liquidacion = editarLiquidacionServicio.buscarLiquidacionPorAsocPeriodo(idAsoc,periodo);

        Map<String, Object> respuesta = new HashMap<>();

        respuesta.put("liquidacion", liquidacion);
        respuesta.put("objetivos", objetivos);

        return respuesta;
    }


    //guarda los datos de la sesion
    @PostMapping("/guardarParametrosSesion")
    public void guardarParametrosSesion(@RequestBody DatosSesionDTO datos, HttpSession session){

        System.out.println("Hola");

        session.setAttribute("idAsoc",datos.getIdAsoc());

        session.setAttribute("periodo",datos.getPeriodo());



    }


    @GetMapping("/verLiquidacion")
    public String vistaLiquidacion(){

        return "liquidacion.html";
    }


    @PutMapping("guardarEdicion/{idLiqui}")
    @ResponseBody
    public ResponseEntity<Object> guardarEditado(@PathVariable String idLiqui, @Valid @RequestBody LiquidacionActualizarDTO liqAtcDTO, BindingResult result) {

        System.out.println("hola");


        if (result.hasErrors()) {
            // Construir una respuesta con los errores
            Map<String, String> errores = result.getFieldErrors().stream()
                    .collect(Collectors.toMap(
                            fieldError -> fieldError.getField(),
                            fieldError -> fieldError.getDefaultMessage(),
                            (mensaje1, mensaje2) -> mensaje1 + "; " + mensaje2 // Combina mensajes duplicados
                    ));

            // Retornar los errores como JSON
            return ResponseEntity.badRequest().body(errores);
        }

        RespuestaDTO respuestaDTO = editarLiquidacionServicio.editarLiquidacion(liqAtcDTO);

        System.out.println("hola");

        return ResponseEntity.ok(Map.of("mensaje", respuestaDTO.getMensaje()));
    }





    private void actualizarCampos(LiquidacionHistorial liquiEditadaHistorial, LiquidacionActualizarDTO liqAtcDTO) {
        // Utilizamos reflexión para asignar valores automáticamente
        Field[] fieldsDTO = liqAtcDTO.getClass().getDeclaredFields();
        Field[] fieldsEntity = liquiEditadaHistorial.getClass().getDeclaredFields();

        for (Field fieldDTO : fieldsDTO) {
            fieldDTO.setAccessible(true);
            for (Field fieldEntity : fieldsEntity) {
                if (fieldEntity.getName().equals(fieldDTO.getName())) {
                    fieldEntity.setAccessible(true);
                    try {
                        fieldEntity.set(liquiEditadaHistorial, fieldDTO.get(liqAtcDTO));
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException("Error al actualizar los campos", e);
                    }
                }
            }
        }
    }







}



