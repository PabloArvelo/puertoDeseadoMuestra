package com.puertodeseado.servicio;


import com.puertodeseado.entidades.AsociadosMain;
import com.puertodeseado.entidades.Imagen;
import com.puertodeseado.excepciones.CuilRepetidoException;
import com.puertodeseado.excepciones.MisExcepciones;
import com.puertodeseado.repositorio.AsociadosMainRepositorio;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class AsociadosMainServicio {

    @Autowired
    private AsociadosMainRepositorio asociadosMainRepositorio;
    @Autowired
    private ImagenServicio imagenServicio;

    @Transactional
    public void crearAsociado(String apellido,
                              String nombre,
                              String depatamentoLaboral,
                              String cbu,
                              Date fechaNacimiento,
                              String nacionalidad,
                              Integer dni,
                              Long cuil,
                              String estadoCivil,
                              String grupoSanguineo,
                              Long telFijo,
                              Long telMovil,
                              String email,
                              String calle,
                              Integer numero,
                              String piso,
                              String dpto,
                              String barrio,
                              String distrito,
                              String provincia,
                              Integer codigoPostal,
                              MultipartFile archivo,
                              Double salarioMinimo) throws MisExcepciones, CuilRepetidoException {


        AsociadosMain asociado = new AsociadosMain();

        asociado.setApellido(apellido.toUpperCase().trim());
        asociado.setNombre(nombre.toUpperCase().trim());
        //asociado.setFechaIngreso(new Date());
        asociado.setDepatamentoLaboral(depatamentoLaboral);
        asociado.setFechaNacimiento(fechaNacimiento);
        asociado.setNacionalidad(nacionalidad.toUpperCase());
        asociado.setDni(dni);
        asociado.setCuil(cuil);
        asociado.setEstadoCivil(estadoCivil.toUpperCase());
        asociado.setGrupoSanguineo(grupoSanguineo);
        asociado.setTelFijo(telFijo);
        asociado.setTelMovil(telMovil);
        asociado.setEmail(email.toLowerCase().trim());
        asociado.setCalle(calle.toUpperCase().trim());
        asociado.setNumero(numero);
        asociado.setPiso(piso.trim());
        asociado.setDpto(dpto.toUpperCase().trim());
        asociado.setBarrio(barrio.toUpperCase().trim());
        asociado.setDistrito(distrito.toUpperCase().trim());
        asociado.setProvincia(provincia.toUpperCase().trim());
        asociado.setCodigoPostal(codigoPostal);

        Imagen imagen = imagenServicio.guardar(archivo);
        asociado.setImagen(imagen);
        asociado.setSalarioMinimo(salarioMinimo);
        asociado.setCbu(cbu);

        //divido el salariio minimo en 60 y lo paso a BigDecimal para redondearlo a dos decimales
        BigDecimal redondeado = new BigDecimal(salarioMinimo/60).setScale(2, RoundingMode.CEILING);
        // seteo el salario mínimo previamente redondeado, pero lo paso previo a double
        asociado.setCuotaSocial(redondeado.doubleValue());

        try {
            asociadosMainRepositorio.save(asociado);
        } catch (DataIntegrityViolationException e) {
            throw new CuilRepetidoException("el cuil esta super repetido");

        }

    }

    @Transactional
    public void actualizarAsociado(String apellido,
                                   String nombre,
                                   String depatamentoLaboral,
                                   String cbu,
                                   Date fechaNacimiento,
                                   String nacionalidad,
                                   Integer dni,
                                   Long cuil,
                                   String estadoCivil,
                                   String grupoSanguineo,
                                   Long telFijo,
                                   Long telMovil,
                                   String email,
                                   String calle,
                                   Integer numero,
                                   String piso,
                                   String dpto,
                                   String barrio,
                                   String distrito,
                                   String provincia,
                                   Integer codigoPostal,
                                   String id) {

        try {
            asociadosMainRepositorio.actualizarAsociado(apellido.toUpperCase(),
                    nombre.toUpperCase(),
                    depatamentoLaboral,
                    cbu,
                    fechaNacimiento,
                    nacionalidad.toUpperCase(),
                    dni,
                    cuil,
                    estadoCivil,
                    grupoSanguineo,
                    telFijo,
                    telMovil,
                    email.toLowerCase(),
                    calle.toUpperCase(),
                    numero,
                    piso,
                    dpto.toUpperCase(),
                    barrio.toUpperCase(),
                    distrito.toUpperCase(),
                    provincia.toUpperCase(),
                    codigoPostal,
                    id);

            //System.out.println("asociado actualizado OK OK OK OK OK OK OK");

        } catch (Exception e) {
            System.out.println("ERROOOOOORRRR  al actualizar ASOCIADOOOO " + e.getMessage());
        }
    }

    @Transactional
    public Boolean bajaAsociado(String idAsoc){



        Date fechaBaja = new Date();
        // capturo la cantidad de filas afectadas
        int filasAfectadas = asociadosMainRepositorio.bajaAsociado(idAsoc, fechaBaja);
        // retorno true si es mayor a cero
        return filasAfectadas > 0;
    }

    @Transactional
    public void actualizarHabilitacion(Date habilitacion, String id) {

        try {
            asociadosMainRepositorio.actualizarHabilitacionAsociado(habilitacion, id);

        } catch (Exception e) {
            System.out.println("ERROOOOOORRRR  al actualizar HABILITACIONNNNNN " + e.getMessage());
        }
    }

    @Transactional
    public void guardarFotoAsociado(Imagen imagen,String id){

        try {
            asociadosMainRepositorio.actualizarFotoAsociado(imagen.getId(), id);
        }catch (Exception e){
            System.out.println("ERROOOOOORRRR  al actualizar FOTOOOOOO    ASOCIADOOOO " + e.getMessage());
        }
    }

    public AsociadosMain buscarAsocPorCuil(Long cuil) {
        return asociadosMainRepositorio.buscarPorCuil(cuil);
    }

    public boolean buscaSiExisteAsociado(Long cuil){
        int filasResultado = asociadosMainRepositorio.buscaSiExisteAsociado(cuil);
        // retorno true si es mayor a cero
        return filasResultado > 0;
    }

    public List<AsociadosMain> buscarPorApellido(String apellido) {
        String apellidoConLike = "%" + apellido + "%";

        return asociadosMainRepositorio.buscarPorApellido(apellidoConLike);
    }

    public AsociadosMain getOne(String id) {
        return asociadosMainRepositorio.getReferenceById(id);
    }

    @Transactional
    public void importarDesdeCSV(MultipartFile archivoCSV) throws Exception {

        try (Reader reader = new InputStreamReader(archivoCSV.getInputStream(), StandardCharsets.UTF_8)) {
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();
            CSVReader csvReader = new CSVReaderBuilder(reader).withCSVParser(parser).withSkipLines(1).build();
            List<String[]> registros = csvReader.readAll();

            //Integer dni = !registro[6].isEmpty() ? Integer.parseInt(registro[6]) : null;

            for (String[] registro : registros) {
                String apellido = registro[0];
                String nombre = registro[1];
                Date fechaIngreso = new SimpleDateFormat("yyyy-MM-dd").parse(registro[2]);
                String departamentoLaboral = registro[3];
                Date fechaNacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(registro[4]);
                String nacionalidad = registro[5];

                Integer dni = !registro[6].isEmpty() ? Integer.parseInt(registro[6]) : null;
                Long cuil = !registro[7].isEmpty() ? Long.parseLong(registro[7]) : null;

                String estadoCivil = registro[8];
                String grupoSanguineo = registro[9];

                Long telFijo = !registro[10].isEmpty() ? Long.parseLong(registro[10]) : null;
                Long telMovil = !registro[11].isEmpty() ? Long.parseLong(registro[11]) : null;

                String email = registro[12];
                String calle = registro[13];

                Integer numero = !registro[14].isEmpty() ? Integer.parseInt(registro[14]) : null;
                String piso = registro[15];

                String dpto = registro[16];
                String barrio = registro[17];
                String distrito = registro[18];
                String provincia = registro[19];

                Integer codigoPostal = !registro[20].isEmpty() ? Integer.parseInt(registro[20]) : null;

                String imagen = registro[21];

                Double salarioMinimo = Double.valueOf(registro[22]);

                String cbu = registro[23];

                // Crear asociado y guardar en la base de datos
                crearAsociado(apellido, nombre, departamentoLaboral, cbu, fechaNacimiento, nacionalidad, dni, cuil, estadoCivil, grupoSanguineo, telFijo, telMovil, email, calle, numero, piso, dpto, barrio, distrito, provincia, codigoPostal, null,salarioMinimo);
            }
        } catch (Exception e) {
            throw new Exception("Error al importar desde CSV", e);
        }
    }




}
