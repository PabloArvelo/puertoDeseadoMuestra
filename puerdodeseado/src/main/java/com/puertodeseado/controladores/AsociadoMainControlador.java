package com.puertodeseado.controladores;


import com.puertodeseado.EntidadesDTO.*;
import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.servicio.AsociadosMainServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/buscar")
public class AsociadoMainControlador {

  @Autowired
  private AsociadosMainServicio asociadosMainServicio;


  @GetMapping("/unoSolo")
  public String buscarAsociado(@RequestParam(required = false) String nCuil, ModelMap modelo) {


    if (!esNumero(nCuil)) {
      modelo.put("noExisteCuil", "solo debe ingresar numeros");

      modelo.addAttribute("ropaDTO", new RopaDTO());
      modelo.addAttribute("ropaTalleDTO", new RopaTalleDTO());

      return "index.html";
    }

    Long cuilAsoc = Long.parseLong(nCuil);
    AsociadosMain asociado = asociadosMainServicio.buscarAsocPorCuil(cuilAsoc);

    if (asociado == null) {

      if (nCuil == null) {
        String respuesta = "ingrese un cuil";
        modelo.put("noExisteCuil", respuesta);

        modelo.addAttribute("ropaDTO", new RopaDTO());
        modelo.addAttribute("ropaTalleDTO", new RopaTalleDTO());

        return "index.html";
      }

      String respuesta = "el asociado con cuil " + cuilAsoc + " no existe";
      modelo.put("noExisteCuil", respuesta);

      modelo.addAttribute("ropaDTO", new RopaDTO());
      modelo.addAttribute("ropaTalleDTO", new RopaTalleDTO());

      return "index.html";
    } else {

      // ------------------------ CALCULO LA EDAD ------------------------
      LocalDate fechaActual = LocalDate.now();
      Timestamp timestamp = (Timestamp) asociado.getFechaNacimiento();

      LocalDateTime localDateTime = timestamp.toLocalDateTime();
      LocalDate fechaNacimiento = localDateTime.toLocalDate();

      Period periodo = Period.between(fechaNacimiento, fechaActual);
      int edad = periodo.getYears();

      // ------------------------------------------------------------------


      //para dar formato a las fechas (ingreso nacimiento, etc)
      String formatoFecha = "dd/MM/yyyy";
      DateFormat dateFormat = new SimpleDateFormat(formatoFecha);

      String id = asociado.getId();
      String nombre = asociado.getNombre();
      String apellido = asociado.getApellido();

      String fechaIngreso = dateFormat.format(asociado.getFechaIngreso());
      String depatamentoLaboral = asociado.getDepatamentoLaboral();
      String fechaNac = dateFormat.format(asociado.getFechaNacimiento());
      String nacionalidad = asociado.getNacionalidad();
      Integer dni = asociado.getDni();
      Long cuil = asociado.getCuil();
      String estadoCivil = asociado.getEstadoCivil();
      String grupoSanguineo = asociado.getGrupoSanguineo();
      Long telFijo = asociado.getTelFijo();
      Long telMovil = asociado.getTelMovil();
      String email = asociado.getEmail();
      String calle = asociado.getCalle();
      Integer numero = asociado.getNumero();
      String piso = asociado.getPiso();
      String dpto = asociado.getDpto();
      String barrio = asociado.getBarrio();
      String distrito = asociado.getDistrito();
      String provincia = asociado.getProvincia();
      Integer codigoPostal = asociado.getCodigoPostal();
      String rutaImagen = "/imagen/perfil/" + id;   // antes llamaba la ruta desde el front

      Date fechaHabil = asociado.getHabilitacion();

      String habilitacion = "";
      if (fechaHabil == null) {
        habilitacion = "no habilitado";
      } else {
        habilitacion = dateFormat.format(asociado.getHabilitacion());
      }


      modelo.put("idAsoc", id);
      modelo.put("nombre", nombre);
      modelo.put("apellido", apellido);
      modelo.put("edad", edad);
      modelo.put("fechaIngreso", fechaIngreso);
      modelo.put("depatamentoLaboral", depatamentoLaboral);
      modelo.put("fechaNac", fechaNac);
      modelo.put("nacionalidad", nacionalidad);
      modelo.put("dni", dni);
      modelo.put("cuil", cuil);
      modelo.put("estadoCivil", estadoCivil);
      modelo.put("grupoSanguineo", grupoSanguineo);
      modelo.put("telFijo", telFijo);
      modelo.put("telMovil", telMovil);
      modelo.put("email", email);
      modelo.put("calle", calle);
      modelo.put("numero", numero);
      modelo.put("piso", piso);
      modelo.put("dpto", dpto);
      modelo.put("barrio", barrio);
      modelo.put("distrito", distrito);
      modelo.put("provincia", provincia);
      modelo.put("codigoPostal", codigoPostal);
      modelo.put("rutaImagen", rutaImagen);  // ahora la paso como atributo del modelo.
      modelo.put("habilitacion", habilitacion);

      // -------------------------- regla para los iconos de habilitciones------------------
      String icoSinHabil = "/img/iconos/no_habilitado.png";
      String icoHabilVerde = "/img/iconos/habil_Green.png";
      String icoHabilAmarillo = "/img/iconos/habil_Yellow.png";
      String icoHabilRojo = "/img/iconos/habil_Red.png";


      if (asociado.getHabilitacion() == null){
        modelo.put("rutaImagenHabilitacion", icoSinHabil);
      }else {

        // convierto la fecha de habilitación que está en la DDBB a LocalDate
        // para poder calcular la diferencia de 45 días
        LocalDate fechaInicioHabil = asociado.getHabilitacion()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        System.out.println("fecha convertida: "+fechaInicioHabil);

        // calculo un año desde la fecha de habilitacion (le sumo un año)
        LocalDate fechaVenceHabilitacion = fechaInicioHabil.plusYears(1);

        System.out.println("fecha vence: "+fechaVenceHabilitacion);

        // defino la cantidad de días previos al vencimineto
        Integer diasAlertaVencimiento = 45;

        //defino la fecha tope para iniciar la alerta del vencimiento
        // LocalDate fechaInicioAlertaVencimiento = fechaVenceHabilitacion.minusDays(45);

        // calfulo la diferencia de días entre la fecha actual y la fecha de vencimiento de la habilitación
        Long calculaAlertaVencimiento = ChronoUnit.DAYS.between(fechaActual, fechaVenceHabilitacion);

        System.out.println("calculo alerta: "+calculaAlertaVencimiento);


        if (calculaAlertaVencimiento > diasAlertaVencimiento){
          modelo.put("rutaImagenHabilitacion", icoHabilVerde);
        }

        if (calculaAlertaVencimiento <= diasAlertaVencimiento && calculaAlertaVencimiento >= 1){
          modelo.put("rutaImagenHabilitacion", icoHabilAmarillo);
        }

        if (calculaAlertaVencimiento <= 0){
          modelo.put("rutaImagenHabilitacion", icoHabilRojo);
        }

      }

      // ---------------------------------------------------------------------------------



      modelo.addAttribute("asociadosDtoCompDTO", new AsociadosDtoCompDTO()); // estoy usando un DTO compuesto.
      return "asociado.html";
    }
  }

  @GetMapping("/unoSoloDesdeApellido/{nCuil}")
  public String buscarAsociadoPorApellido(@PathVariable Long nCuil, ModelMap modelo, Model model) {

    Long cuilAsoc = nCuil;
    AsociadosMain asociado = asociadosMainServicio.buscarAsocPorCuil(nCuil);

    if (asociado == null) {

      if (nCuil == null) {
        String respuesta = "ingrese un cuil";
        modelo.put("noExisteApellido", respuesta);
        RopaDTO ropaDTO = new RopaDTO();
        model.addAttribute("ropaDTO", ropaDTO);
        RopaTalleDTO ropaTalleDTO = new RopaTalleDTO();
        modelo.addAttribute("ropaTalleDTO", ropaTalleDTO);
        return "index.html";
      }

      String respuesta = "el asociado con apellido " + cuilAsoc + " no existe";
      modelo.put("noExisteApellido", respuesta);
      RopaDTO ropaDTO = new RopaDTO();
      model.addAttribute("ropaDTO", ropaDTO);
      RopaTalleDTO ropaTalleDTO = new RopaTalleDTO();
      modelo.addAttribute("ropaTalleDTO", ropaTalleDTO);
      return "index.html";
    } else {

      LocalDate fechaActual = LocalDate.now();
      Timestamp timestamp = (Timestamp) asociado.getFechaNacimiento();

      LocalDateTime localDateTime = timestamp.toLocalDateTime();
      LocalDate fechaNacimiento = localDateTime.toLocalDate();

      Period periodo = Period.between(fechaNacimiento, fechaActual);
      int edad = periodo.getYears();

      //para dar formato a las fechas (ingreso nacimiento, etc)
      String formatoFecha = "dd/MM/yyyy";
      DateFormat dateFormat = new SimpleDateFormat(formatoFecha);

      String id = asociado.getId();
      String nombre = asociado.getNombre();
      String apellido = asociado.getApellido();

      String fechaIngreso = dateFormat.format(asociado.getFechaIngreso());
      String depatamentoLaboral = asociado.getDepatamentoLaboral();
      String fechaNac = dateFormat.format(asociado.getFechaNacimiento());
      String nacionalidad = asociado.getNacionalidad();
      Integer dni = asociado.getDni();
      Long cuil = asociado.getCuil();
      String estadoCivil = asociado.getEstadoCivil();
      String grupoSanguineo = asociado.getGrupoSanguineo();
      Long telFijo = asociado.getTelFijo();
      Long telMovil = asociado.getTelMovil();
      String email = asociado.getEmail();
      String calle = asociado.getCalle();
      Integer numero = asociado.getNumero();
      String piso = asociado.getPiso();
      String dpto = asociado.getDpto();
      String barrio = asociado.getBarrio();
      String distrito = asociado.getDistrito();
      String provincia = asociado.getProvincia();
      Integer codigoPostal = asociado.getCodigoPostal();
      //Imagen imagen = asociado.getImagen();
      String rutaImagen = "/imagen/perfil/" + id;   // antes llamaba la ruta desde el front


      Date fechaHabil = asociado.getHabilitacion();

      String habilitacion = "";
      if (fechaHabil == null) {
        habilitacion = "no habilitado";
      } else {
        habilitacion = dateFormat.format(asociado.getHabilitacion());
      }


      modelo.put("idAsoc", id);
      modelo.put("nombre", nombre);
      modelo.put("apellido", apellido);
      modelo.put("edad", edad);
      modelo.put("fechaIngreso", fechaIngreso);
      modelo.put("depatamentoLaboral", depatamentoLaboral);
      modelo.put("fechaNac", fechaNac);
      modelo.put("nacionalidad", nacionalidad);
      modelo.put("dni", dni);
      modelo.put("cuil", cuil);
      modelo.put("estadoCivil", estadoCivil);
      modelo.put("grupoSanguineo", grupoSanguineo);
      modelo.put("telFijo", telFijo);
      modelo.put("telMovil", telMovil);
      modelo.put("email", email);
      modelo.put("calle", calle);
      modelo.put("numero", numero);
      modelo.put("piso", piso);
      modelo.put("dpto", dpto);
      modelo.put("barrio", barrio);
      modelo.put("distrito", distrito);
      modelo.put("provincia", provincia);
      modelo.put("codigoPostal", codigoPostal);
      //modelo.put("imagen",imagen);
      modelo.put("rutaImagen", rutaImagen);  // ahora la paso como atributo del modelo.
      modelo.put("habilitacion", habilitacion);

      // -------------------------- regla para los iconos de habilitciones------------------
      String icoSinHabil = "/img/iconos/no_habilitado.png";
      String icoHabilVerde = "/img/iconos/habil_Green.png";
      String icoHabilAmarillo = "/img/iconos/habil_Yellow.png";
      String icoHabilRojo = "/img/iconos/habil_Red.png";


      if (asociado.getHabilitacion() == null){
        modelo.put("rutaImagenHabilitacion", icoSinHabil);
      }else {

        // convierto la fecha de habilitación que está en la DDBB a LocalDate
        // para poder calcular la diferencia de 45 días
        LocalDate fechaInicioHabil = asociado.getHabilitacion()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        System.out.println("fecha convertida: "+fechaInicioHabil);

        // calculo un año desde la fecha de habilitacion (le sumo un año)
        LocalDate fechaVenceHabilitacion = fechaInicioHabil.plusYears(1);

        System.out.println("fecha vence: "+fechaVenceHabilitacion);

        // defino la cantidad de días previos al vencimineto
        Integer diasAlertaVencimiento = 45;

        //defino la fecha tope para iniciar la alerta del vencimiento
        // LocalDate fechaInicioAlertaVencimiento = fechaVenceHabilitacion.minusDays(45);

        // calfulo la diferencia de días entre la fecha actual y la fecha de vencimiento de la habilitación
        Long calculaAlertaVencimiento = ChronoUnit.DAYS.between(fechaActual, fechaVenceHabilitacion);

        System.out.println("calculo alerta: "+calculaAlertaVencimiento);


        if (calculaAlertaVencimiento > diasAlertaVencimiento){
          modelo.put("rutaImagenHabilitacion", icoHabilVerde);
        }

        if (calculaAlertaVencimiento <= diasAlertaVencimiento && calculaAlertaVencimiento >= 1){
          modelo.put("rutaImagenHabilitacion", icoHabilAmarillo);
        }

        if (calculaAlertaVencimiento <= 0){
          modelo.put("rutaImagenHabilitacion", icoHabilRojo);
        }

      }

      // ---------------------------------------------------------------------------------



      modelo.addAttribute("asociadosDtoCompDTO", new AsociadosDtoCompDTO());
      return "asociado.html";
    }
  }

  @GetMapping("/asociadoPorApellido")
  public String buscarPorApellido(@RequestParam(required = false) String apellido, ModelMap modelo) {

    List<AsociadosMain> resultado = new ArrayList<>();
    resultado = asociadosMainServicio.buscarPorApellido(apellido);


    List<AsociadosMain> resultadoMuestra = new ArrayList<>();

    for (int i = 0; i < resultado.size(); i++) {
      AsociadosMain asociados = new AsociadosMain();
      asociados.setApellido(resultado.get(i).getApellido());
      asociados.setNombre(resultado.get(i).getNombre());
      asociados.setCuil(resultado.get(i).getCuil());
      resultadoMuestra.add(asociados);
    }

    modelo.addAttribute("asociados", resultadoMuestra);
    return "asociado.html";
  }

  public Boolean esNumero(String dato) {
    try {
      Double.parseDouble(dato);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

}
