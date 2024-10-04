package com.puertodeseado.controladores;

import com.puertodeseado.EntidadesDTO.*;
import com.puertodeseado.excepciones.CuilRepetidoException;
import com.puertodeseado.servicio.AsociadosMainServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/asociado")
public class AltaAsocControlador {

  @Autowired
  AsociadosMainServicio asociadosMainServicio;
  @Autowired
  AsociadoMainControlador asociadoMainControlador;


  @PostMapping("/alta")
  public String alta(@Valid AsociadoDTO asociadoDTO, BindingResult result, Model modelo) throws CuilRepetidoException {

    if (asociadoDTO.getArchivo().isEmpty()) {
      modelo.addAttribute("errorArchivo", "seleccionar imagen");
    }


    if (result.hasErrors()) {
      //modelo.addAttribute("errores",result.getAllErrors());
      return "altaAsoc.html";
    }

    try {

      asociadosMainServicio.crearAsociado(
              asociadoDTO.getApellido(),
              asociadoDTO.getNombre(),
              asociadoDTO.getDepatamentoLaboral(),
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
              asociadoDTO.getArchivo());

      modelo.addAttribute("ropaDTO", new RopaDTO());
      modelo.addAttribute("ropaTalleDTO", new RopaTalleDTO());
      return "index.html";  // Retorna a la página de inicio si todo es correcto
    } catch (CuilRepetidoException e) {
      modelo.addAttribute("error", e.getMessage());
      return "altaAsoc.html";
    } catch (Exception e) {
      modelo.addAttribute("error", "CUIL REPETIDO"); // ESTÁ CAPTURANDO EL ERROR ACÁ TIENE QUE SER EN CuilRepetidoException
      return "altaAsoc.html";
    }

  }

  @PostMapping("/actualizar")
  public String actualizar(@Valid @ModelAttribute("asociadosDtoCompDTO") AsociadosDtoCompDTO asociadosDtoCompDTO, BindingResult result, ModelMap modelo) {


    if (result.hasErrors()) {

      modelo.addAttribute("asociadosDtoCompDTO", asociadosDtoCompDTO);

      //modelo.put("idAsoc", id);
      modelo.put("nombre", asociadosDtoCompDTO.getAsociadoDTO().getNombre());
      modelo.put("apellido", asociadosDtoCompDTO.getAsociadoDTO().getApellido());
      modelo.put("fechaIngreso", asociadosDtoCompDTO.getAsociadoDTO().getFechaIngreso());
      modelo.put("depatamentoLaboral", asociadosDtoCompDTO.getAsociadoDTO().getDepatamentoLaboral());
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
      asociadosMainServicio.actualizarAsociado(asociadosDtoCompDTO.getAsociadoDTO().getApellido(),
              asociadosDtoCompDTO.getAsociadoDTO().getNombre(),
              asociadosDtoCompDTO.getAsociadoDTO().getDepatamentoLaboral(),
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

      modelo.put("nombre", asociadosDtoCompDTO.getAsociadoDTO().getNombre());
      modelo.put("apellido", asociadosDtoCompDTO.getAsociadoDTO().getApellido());
      modelo.put("fechaIngreso", asociadosDtoCompDTO.getAsociadoDTO().getFechaIngreso());
      modelo.put("depatamentoLaboral", asociadosDtoCompDTO.getAsociadoDTO().getDepatamentoLaboral());
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


  @PostMapping("/actualizarHabilitacion")
  public String actualizarHabilitacion(@Valid @ModelAttribute("asociadosDtoCompDTO") AsociadosDtoCompDTO asociadosDtoCompDTO, BindingResult result, ModelMap modelo) {

    System.out.println("paramos aca");


    if (result.hasErrors()) {

      modelo.addAttribute("asociadosDtoCompDTO", asociadosDtoCompDTO);

      //modelo.put("idAsoc", id);
      modelo.put("nombre", asociadosDtoCompDTO.getAsociadoDTO().getNombre());
      modelo.put("apellido", asociadosDtoCompDTO.getAsociadoDTO().getApellido());
      modelo.put("fechaIngreso", asociadosDtoCompDTO.getAsociadoDTO().getFechaIngreso());
      modelo.put("depatamentoLaboral", asociadosDtoCompDTO.getAsociadoDTO().getDepatamentoLaboral());
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



