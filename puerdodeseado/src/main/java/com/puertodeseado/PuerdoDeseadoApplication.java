package com.puertodeseado;

import com.puertodeseado.entidades.RopaMovimiento;
import com.puertodeseado.servicio.RopaMovimientoServicio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;


@SpringBootApplication
@EnableScheduling
public class PuerdoDeseadoApplication {
	@Autowired
	private RopaMovimientoServicio ropaMovimientoServicio;

	public static void main(String[] args) {
		SpringApplication.run(PuerdoDeseadoApplication.class, args);

	}

//	@PostConstruct
//	public void init() {
//		ropaMovimientoServicio.listarEstadosPrendaEnStock(4);
//
//	}

}
