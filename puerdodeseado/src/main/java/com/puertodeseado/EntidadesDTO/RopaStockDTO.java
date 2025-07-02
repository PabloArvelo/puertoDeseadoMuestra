package com.puertodeseado.EntidadesDTO;


// es necesario en estee caso usar una interfaz porque Spring Data JPA no puede
// convertir el resulta al tipo DTO comun que baía planteado.
// los atributos de la interfaz dene coincidir con los campos en la tabla

public interface RopaStockDTO {
  String getId_Prenda();
  String getTalle();
  boolean getNuevo();
  Integer getStock();

}
