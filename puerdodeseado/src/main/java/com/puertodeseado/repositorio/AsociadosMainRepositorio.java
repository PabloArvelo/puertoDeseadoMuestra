package com.puertodeseado.repositorio;

import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.entidades.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AsociadosMainRepositorio extends JpaRepository<AsociadosMain, String> {

  // buscar asociados por Numero de CUIL
  @Query("SELECT a FROM AsociadosMain a WHERE a.cuil = :cuil")
  public AsociadosMain buscarPorCuil(@Param("cuil") Long cuil);

  // buscar asociados por APELLIDO
  @Query(value = "SELECT * FROM asociados_main " +
          "WHERE apellido LIKE :apellido " +
          "ORDER BY apellido, nombre ASC", nativeQuery = true)
  public List<AsociadosMain> buscarPorApellido(@Param("apellido") String apellido);


  //para este caso que es un UPDATE, lleva la anotación @Modifying
  @Modifying
  @Query(value = "UPDATE asociados_main SET " +
          "apellido = :apellido, " +
          "nombre = :nombre," +
          "depatamento_laboral = :depatamento_laboral, " +
          "fecha_nacimiento = :fecha_nacimiento, " +
          "nacionalidad = :nacionalidad, " +
          "dni = :dni, " +
          "cuil = :cuil, " +
          "estado_civil = :estado_civil, " +
          "grupo_sanguineo = :grupo_sanguineo, " +
          "tel_fijo = :tel_fijo, " +
          "tel_movil = :tel_movil, " +
          "email = :email, " +
          "calle = :calle, " +
          "numero = :numero, " +
          "piso = :piso, " +
          "dpto = :dpto, " +
          "barrio = :barrio, " +
          "distrito = :distrito, " +
          "provincia = :provincia, " +
          "codigo_postal = :codigo_postal " +
          "WHERE id = :id", nativeQuery = true)
  public void actualizarAsociado(@Param("apellido") String apellido,
                                 @Param("nombre") String nombre,
                                 @Param("depatamento_laboral") String depatamento_laboral,
                                 @Param("fecha_nacimiento") Date fecha_nacimiento,
                                 @Param("nacionalidad") String nacionalidad,
                                 @Param("dni") Integer dni,
                                 @Param("cuil") Long cuil,
                                 @Param("estado_civil") String estado_civil,
                                 @Param("grupo_sanguineo") String grupo_sanguineo,
                                 @Param("tel_fijo") Long tel_fijo,
                                 @Param("tel_movil") Long tel_movil,
                                 @Param("email") String email,
                                 @Param("calle") String calle,
                                 @Param("numero") Integer numero,
                                 @Param("piso") String piso,
                                 @Param("dpto") String dpto,
                                 @Param("barrio") String barrio,
                                 @Param("distrito") String distrito,
                                 @Param("provincia") String provincia,
                                 @Param("codigo_postal") Integer codigo_postal,
                                 @Param("id") String id);


  @Modifying
  @Query(value = "UPDATE asociados_main SET " +
          "imagen_id = :imagen_id " +
          "WHERE id = :id", nativeQuery = true)
  public void actualizarFotoAsociado(@Param("imagen_id") String idImagen, @Param("id") String id);

  @Modifying
  @Query(value = "UPDATE asociados_main SET " +
          "habilitacion = :habilitacion " +
          "WHERE id = :id", nativeQuery = true)
  public void actualizarHabilitacionAsociado(@Param("habilitacion") Date habilitacion, @Param("id") String id);


  @Query(value = "SELECT * FROM asociados_main "+
  "WHERE habilitacion IS NOT NULL;", nativeQuery = true)
  public List<AsociadosMain> asociadosConHabilitacion();


}
