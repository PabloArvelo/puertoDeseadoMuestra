package com.puertodeseado.controladores.anticiporetorno;

import com.puertodeseado.entidades.anticiporetorno.LiquidacionHistorial;
import com.puertodeseado.repositorio.anticipoderetorno.LiquidacionHistorialRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/eliminarLiquidacion")
public class EliminarLiquidacionControlador {

    @Autowired
    private LiquidacionHistorialRepositorio liquidacionHistorialRepositorio;

    @GetMapping("/vistaEliminarLiquidacion")
    public String vistaEliminarLiquidacion(){

        return "eliminarLiquidacion";
    }

    // los dos metodos que siguen hay que ver si estan bien o adaptarlos para la tarea de eliminar liquidación
    @DeleteMapping("eliminar")
    @ResponseBody
    public ResponseEntity<String> eliminar(@RequestParam String periodo){
        try {
            int resultado = liquidacionHistorialRepositorio.eliminarLiquidacion(periodo);

            if (resultado>0){
                return ResponseEntity.ok("liquidación eliminada con éxito");
            }else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se eliminó la liquidación");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("error interno "+ e.getMessage());
        }

    }







    public boolean verSiExiste(String periodo){
        Boolean estaVacio = liquidacionHistorialRepositorio.verSiExisteLiqui(periodo).isEmpty();
        return estaVacio;

    }
}
