package com.puertodeseado.servicio.anticiporetorno;

import com.puertodeseado.entidades.anticiporetorno.ObjetivosMain;
import com.puertodeseado.repositorio.anticipoderetorno.LiquidacionHistorialRepositorio;
import com.puertodeseado.repositorio.anticipoderetorno.ObjetivosMainRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImprimirRecibosPorObjetivoServicio {

    @Autowired
    private LiquidacionHistorialRepositorio liquidacionHistorialRepositorio;
    @Autowired
    private ObjetivosMainRepositorio objetivosMainRepositorio;

    public List<Optional<ObjetivosMain>>  listarObjetivos(String periodo){

        List<Integer> listaObjetivosInt = liquidacionHistorialRepositorio.listarObjetivos(periodo);

        // acá almacenaré los objetivos
        List<Optional<ObjetivosMain>> listaObjetivos = new ArrayList<>();



        for (Integer obj : listaObjetivosInt){
            Optional<ObjetivosMain> objetivo = objetivosMainRepositorio.findById(obj);
            listaObjetivos.add(objetivo);
        }
        return listaObjetivos;
    }
}
