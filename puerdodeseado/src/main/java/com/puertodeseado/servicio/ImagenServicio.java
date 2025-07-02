package com.puertodeseado.servicio;

import com.puertodeseado.entidades.Imagen;
import com.puertodeseado.excepciones.MisExcepciones;
import com.puertodeseado.repositorio.ImagenRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class ImagenServicio {

  @Autowired
  private ImagenRepositorio imagenRepositorio;

  public Imagen guardar(MultipartFile archivo) throws MisExcepciones {

    if (archivo != null) {
      try {
        Imagen imagen = new Imagen();
        imagen.setMime(archivo.getContentType());
        imagen.setNombre(archivo.getName());
        imagen.setContenido(archivo.getBytes());
        return imagenRepositorio.save(imagen);
      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }
    return null;
  }

  @Transactional
  public void actualizar(MultipartFile archivo, String idImagen) throws MisExcepciones {
    if (archivo != null) {
      try {

        Imagen imagen = new Imagen();

        if (idImagen != null) {
          Optional<Imagen> respuesta = imagenRepositorio.findById(idImagen);
          if (respuesta.isPresent()) {
            imagen = respuesta.get();
          }
        }

        imagen.setContenido(archivo.getBytes());
        imagen.setMime(archivo.getContentType());
        imagen.setNombre(archivo.getName());

        byte[] contenido = imagen.getContenido();
        String mime = imagen.getMime();
        String nombre = imagen.getNombre();

        imagenRepositorio.actualizarImagen(contenido,mime,nombre,idImagen);

      } catch (Exception e) {
        System.err.println(e.getMessage());
      }
    }

  }
}

