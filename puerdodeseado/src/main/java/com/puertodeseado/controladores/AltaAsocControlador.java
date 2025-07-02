package com.puertodeseado.controladores;

import com.puertodeseado.EntidadesDTO.*;
import com.puertodeseado.excepciones.CuilRepetidoException;
import com.puertodeseado.servicio.AsociadosMainServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/asociado")
public class AltaAsocControlador {

  @Autowired
  AsociadosMainServicio asociadosMainServicio;
  @Autowired
  AsociadoMainControlador asociadoMainControlador;

  @PostMapping("/alta")
  public String alta(@Valid @ModelAttribute("asociadoDTO") AsociadoDTO asociadoDTO,
                     BindingResult result,
                     Model modelo) {

    // Validar manualmente el archivo
    if (asociadoDTO.getArchivo() == null || asociadoDTO.getArchivo().isEmpty()) {
      result.rejectValue("archivo", "error.archivo", "Se debe seleccionar un archivo");
    }

    // Verificar si hay errores de validación
    if (result.hasErrors()) {
      // Retornar la vista del formulario con los errores
      return "altaAsoc.html";
    }

    try {

      boolean respuesta = asociadosMainServicio.buscaSiExisteAsociado(asociadoDTO.getCuil());

      if (respuesta) {
        modelo.addAttribute("error", "CUIL REPETIDO");
        return "altaAsoc.html";

      } else {
        asociadosMainServicio.crearAsociado(
                asociadoDTO.getApellido(),
                asociadoDTO.getNombre(),
                asociadoDTO.getDepatamentoLaboral(),
                asociadoDTO.getCbu(),
                asociadoDTO.getFechaNacimiento(),
                asociadoDTO.getNacionalidad(),
                asociadoDTO.getDni(),
                asociadoDTO.getCuil(),
                asociadoDTO.getEstadoCivil(),
                asociadoDTO.getGrupoSanguineo(),
                asociadoDTO.getTelFijo(),
                asociadoDTO.getTelMovil(),
                asociadoDTO.getEmail(),
                asociadoDTO.getCalle(),
                asociadoDTO.getNumero(),
                asociadoDTO.getPiso(),
                asociadoDTO.getDpto(),
                asociadoDTO.getBarrio(),
                asociadoDTO.getDistrito(),
                asociadoDTO.getProvincia(),
                asociadoDTO.getCodigoPostal(),
                asociadoDTO.getArchivo(),
                asociadoDTO.getSalarioMinimo());


        modelo.addAttribute("ropaDTO", new RopaDTO());
        modelo.addAttribute("ropaTalleDTO", new RopaTalleDTO());
        return "index.html";  // Retorna a la página de inicio si todo es correcto


      }
    } catch (Exception e) {
      modelo.addAttribute("error", e.getMessage());
      return "altaAsoc.html";
    }

  }

//  @PostMapping("/actualizar")
//  public String actualizar(@Valid @ModelAttribute("asociadosDtoCompDTO") AsociadosDtoCompDTO asociadosDtoCompDTO, BindingResult result, ModelMap modelo) {
//
//
//
//    List<FieldError> erroresGuardados = new ArrayList<>();
//    for (FieldError error : result.getFieldErrors()){
//      if (!error.getField().equals("asociadoDTO.archivo")) {
//        erroresGuardados.add(error);
//      }
//    }
//
//    // Crear un nuevo BindingResult en lugar de modificar el original
//    BindingResult filteredResult = new BeanPropertyBindingResult(asociadosDtoCompDTO, "asociadosDtoCompDTO");
//    erroresGuardados.forEach(filteredResult::addError);
//
//
//    if (filteredResult.hasErrors()) {
//
//      modelo.addAttribute("asociadosDtoCompDTO", asociadosDtoCompDTO);
//
//      //modelo.put("idAsoc", id);
//      modelo.put("nombre", asociadosDtoCompDTO.getAsociadoDTO().getNombre());
//      modelo.put("apellido", asociadosDtoCompDTO.getAsociadoDTO().getApellido());
//      modelo.put("fechaIngreso", asociadosDtoCompDTO.getAsociadoDTO().getFechaIngreso());
//      modelo.put("depatamentoLaboral", asociadosDtoCompDTO.getAsociadoDTO().getDepatamentoLaboral());
//      modelo.put("cbu", asociadosDtoCompDTO.getAsociadoDTO().getCbu());
//      modelo.put("fechaNac", asociadosDtoCompDTO.getAsociadoDTO().getFechaNacimiento());
//      modelo.put("nacionalidad", asociadosDtoCompDTO.getAsociadoDTO().getNacionalidad());
//      modelo.put("dni", asociadosDtoCompDTO.getAsociadoDTO().getDni());
//      modelo.put("cuil", asociadosDtoCompDTO.getAsociadoDTO().getCuil());
//      modelo.put("estadoCivil", asociadosDtoCompDTO.getAsociadoDTO().getEstadoCivil());
//      modelo.put("grupoSanguineo", asociadosDtoCompDTO.getAsociadoDTO().getGrupoSanguineo());
//      modelo.put("telFijo", asociadosDtoCompDTO.getAsociadoDTO().getTelFijo());
//      modelo.put("telMovil", asociadosDtoCompDTO.getAsociadoDTO().getTelMovil());
//      modelo.put("email", asociadosDtoCompDTO.getAsociadoDTO().getEmail());
//      modelo.put("calle", asociadosDtoCompDTO.getAsociadoDTO().getCalle());
//      modelo.put("numero", asociadosDtoCompDTO.getAsociadoDTO().getNumero());
//      modelo.put("piso", asociadosDtoCompDTO.getAsociadoDTO().getPiso());
//      modelo.put("dpto", asociadosDtoCompDTO.getAsociadoDTO().getDpto());
//      modelo.put("barrio", asociadosDtoCompDTO.getAsociadoDTO().getBarrio());
//      modelo.put("distrito", asociadosDtoCompDTO.getAsociadoDTO().getDistrito());
//      modelo.put("provincia", asociadosDtoCompDTO.getAsociadoDTO().getProvincia());
//      modelo.put("codigoPostal", asociadosDtoCompDTO.getAsociadoDTO().getCodigoPostal());
//
//      String rutaImagen = "/imagen/perfil/" + asociadosDtoCompDTO.getAsociadoDTO().getIdAsoc();
//
//      modelo.put("rutaImagen", rutaImagen);
//
//      System.out.println("hay un error " + result);
//
//
//      return "asociado.html";
//    }
//
//
//    try {
//      //System.out.println("estoy en el try");
//      asociadosMainServicio.actualizarAsociado(asociadosDtoCompDTO.getAsociadoDTO().getApellido(),
//              asociadosDtoCompDTO.getAsociadoDTO().getNombre(),
//              asociadosDtoCompDTO.getAsociadoDTO().getDepatamentoLaboral(),
//              asociadosDtoCompDTO.getAsociadoDTO().getCbu(),
//              asociadosDtoCompDTO.getAsociadoDTO().getFechaNacimiento(),
//              asociadosDtoCompDTO.getAsociadoDTO().getNacionalidad(),
//              asociadosDtoCompDTO.getAsociadoDTO().getDni(),
//              asociadosDtoCompDTO.getAsociadoDTO().getCuil(),
//              asociadosDtoCompDTO.getAsociadoDTO().getEstadoCivil(),
//              asociadosDtoCompDTO.getAsociadoDTO().getGrupoSanguineo(),
//              asociadosDtoCompDTO.getAsociadoDTO().getTelFijo(),
//              asociadosDtoCompDTO.getAsociadoDTO().getTelMovil(),
//              asociadosDtoCompDTO.getAsociadoDTO().getEmail(),
//              asociadosDtoCompDTO.getAsociadoDTO().getCalle(),
//              asociadosDtoCompDTO.getAsociadoDTO().getNumero(),
//              asociadosDtoCompDTO.getAsociadoDTO().getPiso(),
//              asociadosDtoCompDTO.getAsociadoDTO().getDpto(),
//              asociadosDtoCompDTO.getAsociadoDTO().getBarrio(),
//              asociadosDtoCompDTO.getAsociadoDTO().getDistrito(),
//              asociadosDtoCompDTO.getAsociadoDTO().getProvincia(),
//              asociadosDtoCompDTO.getAsociadoDTO().getCodigoPostal(),
//              asociadosDtoCompDTO.getAsociadoDTO().getIdAsoc());
//
//      //System.out.println("actualizado OK Desde el TRY TRY TRY TRY TRY ");
//
//
//    } catch (Exception e) {
//      modelo.addAttribute("error", "CUIL REPETIDO");
//
//      modelo.addAttribute("asociadosDtoCompDTO", asociadosDtoCompDTO);
//
//      modelo.put("nombre", asociadosDtoCompDTO.getAsociadoDTO().getNombre());
//      modelo.put("apellido", asociadosDtoCompDTO.getAsociadoDTO().getApellido());
//      modelo.put("fechaIngreso", asociadosDtoCompDTO.getAsociadoDTO().getFechaIngreso());
//      modelo.put("depatamentoLaboral", asociadosDtoCompDTO.getAsociadoDTO().getDepatamentoLaboral());
//      modelo.put("cbu", asociadosDtoCompDTO.getAsociadoDTO().getCbu());
//      modelo.put("fechaNac", asociadosDtoCompDTO.getAsociadoDTO().getFechaNacimiento());
//      modelo.put("nacionalidad", asociadosDtoCompDTO.getAsociadoDTO().getNacionalidad());
//      modelo.put("dni", asociadosDtoCompDTO.getAsociadoDTO().getDni());
//      modelo.put("cuil", asociadosDtoCompDTO.getAsociadoDTO().getCuil());
//      modelo.put("estadoCivil", asociadosDtoCompDTO.getAsociadoDTO().getEstadoCivil());
//      modelo.put("grupoSanguineo", asociadosDtoCompDTO.getAsociadoDTO().getGrupoSanguineo());
//      modelo.put("telFijo", asociadosDtoCompDTO.getAsociadoDTO().getTelFijo());
//      modelo.put("telMovil", asociadosDtoCompDTO.getAsociadoDTO().getTelMovil());
//      modelo.put("email", asociadosDtoCompDTO.getAsociadoDTO().getEmail());
//      modelo.put("calle", asociadosDtoCompDTO.getAsociadoDTO().getCalle());
//      modelo.put("numero", asociadosDtoCompDTO.getAsociadoDTO().getNumero());
//      modelo.put("piso", asociadosDtoCompDTO.getAsociadoDTO().getPiso());
//      modelo.put("dpto", asociadosDtoCompDTO.getAsociadoDTO().getDpto());
//      modelo.put("barrio", asociadosDtoCompDTO.getAsociadoDTO().getBarrio());
//      modelo.put("distrito", asociadosDtoCompDTO.getAsociadoDTO().getDistrito());
//      modelo.put("provincia", asociadosDtoCompDTO.getAsociadoDTO().getProvincia());
//      modelo.put("codigoPostal", asociadosDtoCompDTO.getAsociadoDTO().getCodigoPostal());
//
//      String rutaImagen = "/imagen/perfil/" + asociadosDtoCompDTO.getAsociadoDTO().getIdAsoc();
//
//      modelo.put("rutaImagen", rutaImagen);
//
//      return "asociado.html";
//    }
//
//    modelo.addAttribute("asociadosDtoCompDTO", asociadosDtoCompDTO);
//
//    String nCuil = String.valueOf(asociadosDtoCompDTO.getAsociadoDTO().getCuil());
//    return asociadoMainControlador.buscarAsociado(nCuil, modelo);
//
//  }


  @PostMapping("/actualizar")
  public String actualizar(@Valid @ModelAttribute("asociadosDtoCompDTO") AsociadosDtoCompDTO asociadosDtoCompDTO, BindingResult result, ModelMap modelo) {

    List<FieldError> erroresGuardados = new ArrayList<>();
    for (FieldError error : result.getFieldErrors()) {
      if (!error.getField().equals("asociadoDTO.archivo")) {
        erroresGuardados.add(error);
      }
    }

    // Crear un nuevo BindingResult en lugar de modificar el original
    BindingResult filteredResult = new BeanPropertyBindingResult(asociadosDtoCompDTO, "asociadosDtoCompDTO");
    erroresGuardados.forEach(filteredResult::addError);

    // NUEVO: Imprimir tanto result como filteredResult para comparar en los logs
    System.out.println("Errores en result: " + result);
    System.out.println("Errores en filteredResult: " + filteredResult);


    System.out.println("=== Errores en filteredResult ===");
    for (FieldError error : filteredResult.getFieldErrors()) {
      System.out.println("Campo: " + error.getField() + " - Mensaje: " + error.getDefaultMessage() + " - Valor rechazado: " + error.getRejectedValue());
    }

    if (filteredResult.hasErrors()) {
      modelo.addAttribute("asociadosDtoCompDTO", asociadosDtoCompDTO);
      // NUEVO: Agregar filteredResult al modelo para que la plantilla use los errores filtrados
      modelo.addAttribute(BindingResult.MODEL_KEY_PREFIX + "asociadosDtoCompDTO", filteredResult);

      //modelo.put("idAsoc", id);
      modelo.put("nombre", asociadosDtoCompDTO.getAsociadoDTO().getNombre());
      modelo.put("apellido", asociadosDtoCompDTO.getAsociadoDTO().getApellido());
      modelo.put("fechaIngreso", asociadosDtoCompDTO.getAsociadoDTO().getFechaIngreso());
      modelo.put("depatamentoLaboral", asociadosDtoCompDTO.getAsociadoDTO().getDepatamentoLaboral());
      modelo.put("cbu", asociadosDtoCompDTO.getAsociadoDTO().getCbu());
      modelo.put("fechaNac", asociadosDtoCompDTO.getAsociadoDTO().getFechaNacimiento());
      modelo.put("nacionalidad", asociadosDtoCompDTO.getAsociadoDTO().getNacionalidad());
      modelo.put("dni", asociadosDtoCompDTO.getAsociadoDTO().getDni());
      modelo.put("cuil", asociadosDtoCompDTO.getAsociadoDTO().getCuil());
      modelo.put("estadoCivil", asociadosDtoCompDTO.getAsociadoDTO().getEstadoCivil());
      modelo.put("grupoSanguineo", asociadosDtoCompDTO.getAsociadoDTO().getGrupoSanguineo());
      modelo.put("telFijo", asociadosDtoCompDTO.getAsociadoDTO().getTelFijo());
      modelo.put("telMovil", asociadosDtoCompDTO.getAsociadoDTO().getTelMovil());
      modelo.put("email", asociadosDtoCompDTO.getAsociadoDTO().getEmail());
      modelo.put("calle", asociadosDtoCompDTO.getAsociadoDTO().getCalle());
      modelo.put("numero", asociadosDtoCompDTO.getAsociadoDTO().getNumero());
      modelo.put("piso", asociadosDtoCompDTO.getAsociadoDTO().getPiso());
      modelo.put("dpto", asociadosDtoCompDTO.getAsociadoDTO().getDpto());
      modelo.put("barrio", asociadosDtoCompDTO.getAsociadoDTO().getBarrio());
      modelo.put("distrito", asociadosDtoCompDTO.getAsociadoDTO().getDistrito());
      modelo.put("provincia", asociadosDtoCompDTO.getAsociadoDTO().getProvincia());
      modelo.put("codigoPostal", asociadosDtoCompDTO.getAsociadoDTO().getCodigoPostal());

      String rutaImagen = "/imagen/perfil/" + asociadosDtoCompDTO.getAsociadoDTO().getIdAsoc();
      modelo.put("rutaImagen", rutaImagen);

      System.out.println("hay un error " + result);

      return "asociado.html";
    }

    try {
      asociadosMainServicio.actualizarAsociado(asociadosDtoCompDTO.getAsociadoDTO().getApellido(),
              asociadosDtoCompDTO.getAsociadoDTO().getNombre(),
              asociadosDtoCompDTO.getAsociadoDTO().getDepatamentoLaboral(),
              asociadosDtoCompDTO.getAsociadoDTO().getCbu(),
              asociadosDtoCompDTO.getAsociadoDTO().getFechaNacimiento(),
              asociadosDtoCompDTO.getAsociadoDTO().getNacionalidad(),
              asociadosDtoCompDTO.getAsociadoDTO().getDni(),
              asociadosDtoCompDTO.getAsociadoDTO().getCuil(),
              asociadosDtoCompDTO.getAsociadoDTO().getEstadoCivil(),
              asociadosDtoCompDTO.getAsociadoDTO().getGrupoSanguineo(),
              asociadosDtoCompDTO.getAsociadoDTO().getTelFijo(),
              asociadosDtoCompDTO.getAsociadoDTO().getTelMovil(),
              asociadosDtoCompDTO.getAsociadoDTO().getEmail(),
              asociadosDtoCompDTO.getAsociadoDTO().getCalle(),
              asociadosDtoCompDTO.getAsociadoDTO().getNumero(),
              asociadosDtoCompDTO.getAsociadoDTO().getPiso(),
              asociadosDtoCompDTO.getAsociadoDTO().getDpto(),
              asociadosDtoCompDTO.getAsociadoDTO().getBarrio(),
              asociadosDtoCompDTO.getAsociadoDTO().getDistrito(),
              asociadosDtoCompDTO.getAsociadoDTO().getProvincia(),
              asociadosDtoCompDTO.getAsociadoDTO().getCodigoPostal(),
              asociadosDtoCompDTO.getAsociadoDTO().getIdAsoc());
    } catch (Exception e) {
      modelo.addAttribute("error", "CUIL REPETIDO");
      modelo.addAttribute("asociadosDtoCompDTO", asociadosDtoCompDTO);
      // NUEVO: Agregar filteredResult al modelo para que la plantilla use los errores filtrados
      modelo.addAttribute(BindingResult.MODEL_KEY_PREFIX + "asociadosDtoCompDTO", filteredResult);

      modelo.put("nombre", asociadosDtoCompDTO.getAsociadoDTO().getNombre());
      modelo.put("apellido", asociadosDtoCompDTO.getAsociadoDTO().getApellido());
      modelo.put("fechaIngreso", asociadosDtoCompDTO.getAsociadoDTO().getFechaIngreso());
      modelo.put("depatamentoLaboral", asociadosDtoCompDTO.getAsociadoDTO().getDepatamentoLaboral());
      modelo.put("cbu", asociadosDtoCompDTO.getAsociadoDTO().getCbu());
      modelo.put("fechaNac", asociadosDtoCompDTO.getAsociadoDTO().getFechaNacimiento());
      modelo.put("nacionalidad", asociadosDtoCompDTO.getAsociadoDTO().getNacionalidad());
      modelo.put("dni", asociadosDtoCompDTO.getAsociadoDTO().getDni());
      modelo.put("cuil", asociadosDtoCompDTO.getAsociadoDTO().getCuil());
      modelo.put("estadoCivil", asociadosDtoCompDTO.getAsociadoDTO().getEstadoCivil());
      modelo.put("grupoSanguineo", asociadosDtoCompDTO.getAsociadoDTO().getGrupoSanguineo());
      modelo.put("telFijo", asociadosDtoCompDTO.getAsociadoDTO().getTelFijo());
      modelo.put("telMovil", asociadosDtoCompDTO.getAsociadoDTO().getTelMovil());
      modelo.put("email", asociadosDtoCompDTO.getAsociadoDTO().getEmail());
      modelo.put("calle", asociadosDtoCompDTO.getAsociadoDTO().getCalle());
      modelo.put("numero", asociadosDtoCompDTO.getAsociadoDTO().getNumero());
      modelo.put("piso", asociadosDtoCompDTO.getAsociadoDTO().getPiso());
      modelo.put("dpto", asociadosDtoCompDTO.getAsociadoDTO().getDpto());
      modelo.put("barrio", asociadosDtoCompDTO.getAsociadoDTO().getBarrio());
      modelo.put("distrito", asociadosDtoCompDTO.getAsociadoDTO().getDistrito());
      modelo.put("provincia", asociadosDtoCompDTO.getAsociadoDTO().getProvincia());
      modelo.put("codigoPostal", asociadosDtoCompDTO.getAsociadoDTO().getCodigoPostal());

      String rutaImagen = "/imagen/perfil/" + asociadosDtoCompDTO.getAsociadoDTO().getIdAsoc();
      modelo.put("rutaImagen", rutaImagen);

      return "asociado.html";
    }

    modelo.addAttribute("asociadosDtoCompDTO", asociadosDtoCompDTO);
    String nCuil = String.valueOf(asociadosDtoCompDTO.getAsociadoDTO().getCuil());
    return asociadoMainControlador.buscarAsociado(nCuil, modelo);
  }





  @PatchMapping("/baja")
  @ResponseBody
  public String bajaAsociado(@RequestParam("idAsoc") String idAsoc) {



    try {
      if (!asociadosMainServicio.bajaAsociado(idAsoc)) {
        return "error en baja asociado";
      } else {
        return "baja exitosa";
      }
    } catch (Exception e) {
      return "error en baja asociado " + e.getMessage();
    }

  }


  @PostMapping("/actualizarHabilitacion")
  public String actualizarHabilitacion(@Valid @ModelAttribute("asociadosDtoCompDTO") AsociadosDtoCompDTO asociadosDtoCompDTO, BindingResult result, ModelMap modelo) {

    //System.out.println("paramos aca");

    List<FieldError> erroresGuardados = new ArrayList<>();
    for (FieldError error : result.getFieldErrors()){
      if (!error.getField().equals("asociadoDTO.archivo")) {
        erroresGuardados.add(error);
      }
    }

    // Crear un nuevo BindingResult en lugar de modificar el original
    BindingResult filteredResult = new BeanPropertyBindingResult(asociadosDtoCompDTO, "asociadosDtoCompDTO");
    erroresGuardados.forEach(filteredResult::addError);


    if (filteredResult.hasErrors()) {

      modelo.addAttribute("asociadosDtoCompDTO", asociadosDtoCompDTO);

      //modelo.put("idAsoc", id);
      modelo.put("nombre", asociadosDtoCompDTO.getAsociadoDTO().getNombre());
      modelo.put("apellido", asociadosDtoCompDTO.getAsociadoDTO().getApellido());
      modelo.put("fechaIngreso", asociadosDtoCompDTO.getAsociadoDTO().getFechaIngreso());
      modelo.put("depatamentoLaboral", asociadosDtoCompDTO.getAsociadoDTO().getDepatamentoLaboral());
      modelo.put("cbu", asociadosDtoCompDTO.getAsociadoDTO().getCbu());
      modelo.put("fechaNac", asociadosDtoCompDTO.getAsociadoDTO().getFechaNacimiento());
      modelo.put("nacionalidad", asociadosDtoCompDTO.getAsociadoDTO().getNacionalidad());
      modelo.put("dni", asociadosDtoCompDTO.getAsociadoDTO().getDni());
      modelo.put("cuil", asociadosDtoCompDTO.getAsociadoDTO().getCuil());
      modelo.put("estadoCivil", asociadosDtoCompDTO.getAsociadoDTO().getEstadoCivil());
      modelo.put("grupoSanguineo", asociadosDtoCompDTO.getAsociadoDTO().getGrupoSanguineo());
      modelo.put("telFijo", asociadosDtoCompDTO.getAsociadoDTO().getTelFijo());
      modelo.put("telMovil", asociadosDtoCompDTO.getAsociadoDTO().getTelMovil());
      modelo.put("email", asociadosDtoCompDTO.getAsociadoDTO().getEmail());
      modelo.put("calle", asociadosDtoCompDTO.getAsociadoDTO().getCalle());
      modelo.put("numero", asociadosDtoCompDTO.getAsociadoDTO().getNumero());
      modelo.put("piso", asociadosDtoCompDTO.getAsociadoDTO().getPiso());
      modelo.put("dpto", asociadosDtoCompDTO.getAsociadoDTO().getDpto());
      modelo.put("barrio", asociadosDtoCompDTO.getAsociadoDTO().getBarrio());
      modelo.put("distrito", asociadosDtoCompDTO.getAsociadoDTO().getDistrito());
      modelo.put("provincia", asociadosDtoCompDTO.getAsociadoDTO().getProvincia());
      modelo.put("codigoPostal", asociadosDtoCompDTO.getAsociadoDTO().getCodigoPostal());

      String rutaImagen = "/imagen/perfil/" + asociadosDtoCompDTO.getAsociadoDTO().getIdAsoc();

      modelo.put("rutaImagen", rutaImagen);


      return "asociado.html";
    }


    try {
      System.out.println("estoy en el try");

      asociadosMainServicio.actualizarHabilitacion(asociadosDtoCompDTO.getAsociadoHabilitacionDTO().getHabilitacion(),
              asociadosDtoCompDTO.getAsociadoDTO().getIdAsoc());


    } catch (Exception e) {

      // ver que hagoo esto
      modelo.addAttribute("error", "CUIL REPETIDO");


      modelo.addAttribute("asociadosDtoCompDTO", asociadosDtoCompDTO);

      modelo.put("nombre", asociadosDtoCompDTO.getAsociadoDTO().getNombre());
      modelo.put("apellido", asociadosDtoCompDTO.getAsociadoDTO().getApellido());
      modelo.put("fechaIngreso", asociadosDtoCompDTO.getAsociadoDTO().getFechaIngreso());
      modelo.put("depatamentoLaboral", asociadosDtoCompDTO.getAsociadoDTO().getDepatamentoLaboral());
      modelo.put("cbu", asociadosDtoCompDTO.getAsociadoDTO().getCbu());
      modelo.put("fechaNac", asociadosDtoCompDTO.getAsociadoDTO().getFechaNacimiento());
      modelo.put("nacionalidad", asociadosDtoCompDTO.getAsociadoDTO().getNacionalidad());
      modelo.put("dni", asociadosDtoCompDTO.getAsociadoDTO().getDni());
      modelo.put("cuil", asociadosDtoCompDTO.getAsociadoDTO().getCuil());
      modelo.put("estadoCivil", asociadosDtoCompDTO.getAsociadoDTO().getEstadoCivil());
      modelo.put("grupoSanguineo", asociadosDtoCompDTO.getAsociadoDTO().getGrupoSanguineo());
      modelo.put("telFijo", asociadosDtoCompDTO.getAsociadoDTO().getTelFijo());
      modelo.put("telMovil", asociadosDtoCompDTO.getAsociadoDTO().getTelMovil());
      modelo.put("email", asociadosDtoCompDTO.getAsociadoDTO().getEmail());
      modelo.put("calle", asociadosDtoCompDTO.getAsociadoDTO().getCalle());
      modelo.put("numero", asociadosDtoCompDTO.getAsociadoDTO().getNumero());
      modelo.put("piso", asociadosDtoCompDTO.getAsociadoDTO().getPiso());
      modelo.put("dpto", asociadosDtoCompDTO.getAsociadoDTO().getDpto());
      modelo.put("barrio", asociadosDtoCompDTO.getAsociadoDTO().getBarrio());
      modelo.put("distrito", asociadosDtoCompDTO.getAsociadoDTO().getDistrito());
      modelo.put("provincia", asociadosDtoCompDTO.getAsociadoDTO().getProvincia());
      modelo.put("codigoPostal", asociadosDtoCompDTO.getAsociadoDTO().getCodigoPostal());

      String rutaImagen = "/imagen/perfil/" + asociadosDtoCompDTO.getAsociadoDTO().getIdAsoc();

      modelo.put("rutaImagen", rutaImagen);

      return "asociado.html";
    }

    modelo.addAttribute("asociadosDtoCompDTO", asociadosDtoCompDTO);

    String nCuil = String.valueOf(asociadosDtoCompDTO.getAsociadoDTO().getCuil());
    return asociadoMainControlador.buscarAsociado(nCuil, modelo);

  }

}



