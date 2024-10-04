package com.puertodeseado.controladores;


import com.puertodeseado.EntidadesDTO.*;
import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.entidades.Imagen;
import com.puertodeseado.excepciones.MisExcepciones;
import com.puertodeseado.repositorio.AsociadosMainRepositorio;
import com.puertodeseado.servicio.AsociadosMainServicio;
import com.puertodeseado.servicio.ImagenServicio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/imagen")
public class ImagenContolador {
  @Autowired
  private AsociadosMainServicio asociadosMainServicio;
  @Autowired
  private AsociadosMainRepositorio asociadosMainRepositorio;
  @Autowired
  private AsociadoMainControlador asociadoMainControlador;
  @Autowired
  private ImagenServicio imagenServicio;


  @GetMapping("/perfil/{id}")
  public ResponseEntity<byte[]> imagenUsuario(@PathVariable String id) {
    AsociadosMain asociado = asociadosMainServicio.getOne(id);
    byte[] imagen = asociado.getImagen().getContenido();
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.IMAGE_JPEG);
    return new ResponseEntity<>(imagen, headers, HttpStatus.OK);
  }


  // la excepción por archivo mayor a 1 MB la manejo desde asociado.js
  @PostMapping("/actualizar")
  public String modificarImagen(@Valid AsociadosDtoCompDTO asociadosDtoCompDTO,
                                BindingResult result,
                                ModelMap modelo) throws MisExcepciones {

    System.out.println("hola");


    if (asociadosDtoCompDTO.getAsociadoFotoDTO().getArchivo().isEmpty()) {

      result.rejectValue("asociadoFotoDTO.archivo", "error.asociadoFotoDTO", "seleccionar foto");

      modelo.addAttribute("asociadoDatosYFotoDTO", asociadosDtoCompDTO);


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

    if (result.hasErrors()) {
      return "asociado.html";
    }


    try {

      //busco la imagen del asociado en la tabla de asociados_main por el id del asociado.
      AsociadosMain asociado = asociadosMainRepositorio.getReferenceById(asociadosDtoCompDTO.getAsociadoDTO().getIdAsoc());

      if (asociado.getImagen() == null) {

        Imagen imagen = imagenServicio.guardar(asociadosDtoCompDTO.getAsociadoFotoDTO().getArchivo());

        String idAsoc = asociadosDtoCompDTO.getAsociadoDTO().getIdAsoc();

        asociadosMainServicio.guardarFotoAsociado(imagen, idAsoc);

        modelo.addAttribute("ropaDTO", new RopaDTO());
        modelo.addAttribute("ropaTalleDTO", new RopaTalleDTO());


        String nCuil = String.valueOf(asociadosDtoCompDTO.getAsociadoDTO().getCuil());
        asociadoMainControlador.buscarAsociado(nCuil, modelo);

      } else {
        imagenServicio.actualizar(asociadosDtoCompDTO.getAsociadoFotoDTO().getArchivo(), asociado.getImagen().getId());

        modelo.addAttribute("ropaDTO", new RopaDTO());
        modelo.addAttribute("ropaTalleDTO", new RopaTalleDTO());


        String nCuil = String.valueOf(asociadosDtoCompDTO.getAsociadoDTO().getCuil());
        asociadoMainControlador.buscarAsociado(nCuil, modelo);
      }

    } catch (MisExcepciones e) {
      System.out.println(e.getMessage());
      modelo.addAttribute("asociadoDatosYFotoDTO", new AsociadosDtoCompDTO());
      return "asociado.html";
    }

    modelo.addAttribute("asociadoDatosYFotoDTO", new AsociadosDtoCompDTO());
    return "asociado.html";
  }



}







