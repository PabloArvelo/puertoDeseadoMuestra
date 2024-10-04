package com.puertodeseado.entidades;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Imagen {
  @Id
  @GeneratedValue(generator = "uuid")
  @GenericGenerator(name = "uuid", strategy = "uuid2")
  private String id;
  private String mime; // es el tipo de archivo
  private String nombre;
  @Lob  // informa a spring que pueden ser datos grandes
  @Basic(fetch = FetchType.LAZY)  // le decimos que es de tipo perezosa, quiere decir que es solo cuando lo pidamos
  @Column(columnDefinition = "LONGBLOB")
  private byte[] contenido;

  // NOTA solo cuando hagamos un get para el atributo contenido, solo ahi se carga
  // los demas atributos se cargan siempre, pero contenido, porque es LAZY (peresoza) solo cuando la llamemos con get

  public Imagen() {
  }

  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }

  public String getMime() {
    return mime;
  }
  public void setMime(String mime) {
    this.mime = mime;
  }

  public String getNombre() {
    return nombre;
  }
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public byte[] getContenido() {
    return contenido;
  }
  public void setContenido(byte[] contenido) {
    this.contenido = contenido;
  }
}
