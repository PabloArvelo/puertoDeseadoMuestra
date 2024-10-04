package com.puertodeseado.clases;



import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class TareaProgramada {

    // Formateador para la hora actual
    private static final DateTimeFormatter formmatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    //@Scheduled(fixedRate = 5000)
//    public void imprimirHora(){
//        String horaActual = LocalDateTime.now().format(formmatter);
//        System.out.println("Hola Mabel! es la hora "+horaActual);
//    }

}
