package com.puertodeseado.EntidadesDTO;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class AsociadoActualizarDTO {
    private String idAsoc;


    @NotEmpty(message = "dato obligatorio")

    private String apellido;
    @NotEmpty(message = "dato obligatorio")
    private String nombre;

    private Date fechaIngreso;
    @NotEmpty(message = "dato obligatorio")
    private String depatamentoLaboral;
    @NotNull(message = "dato obligatorio")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    @NotEmpty(message = "dato obligatorio")
    private String nacionalidad;
    @NotNull(message = "dato obligatorio")
    private Integer dni;
    @NotNull(message = "dato obligatorio")
    @Min(value = 10000000000L, message = "El CUIL debe tener 11 dígitos")
    @Max(value = 99999999999L, message = "El CUIL debe tener 11 dígitos")
    private Long cuil;
    @NotEmpty(message = "dato obligatorio")
    private String estadoCivil;

    private String grupoSanguineo;
    private Long telFijo;
    @NotNull(message = "dato obligatorio")
    private Long telMovil;
    @Email
    @NotEmpty(message = "dato obligatorio")
    private String email;
    @NotEmpty(message = "dato obligatorio")
    private String calle;
    @NotNull(message = "dato obligatorio")
    private Integer numero;
    private String piso;
    private String dpto;
    @NotEmpty(message = "dato obligatorio")
    private String barrio;
    @NotEmpty(message = "dato obligatorio")
    private String distrito;
    @NotEmpty(message = "dato obligatorio")
    private String provincia;
    @NotNull(message = "dato obligatorio")
    private Integer codigoPostal;


    private MultipartFile archivo;

    public AsociadoActualizarDTO() {
    }

    public String getIdAsoc() {
        return idAsoc;
    }

    public void setIdAsoc(String idAsoc) {
        this.idAsoc = idAsoc;
    }

    public @NotEmpty(message = "dato obligatorio") String getApellido() {
        return apellido;
    }

    public void setApellido(@NotEmpty(message = "dato obligatorio") String apellido) {
        this.apellido = apellido;
    }

    public @NotEmpty(message = "dato obligatorio") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotEmpty(message = "dato obligatorio") String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public @NotEmpty(message = "dato obligatorio") String getDepatamentoLaboral() {
        return depatamentoLaboral;
    }

    public void setDepatamentoLaboral(@NotEmpty(message = "dato obligatorio") String depatamentoLaboral) {
        this.depatamentoLaboral = depatamentoLaboral;
    }

    public @NotNull(message = "dato obligatorio") Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(@NotNull(message = "dato obligatorio") Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public @NotEmpty(message = "dato obligatorio") String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(@NotEmpty(message = "dato obligatorio") String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public @NotNull(message = "dato obligatorio") Integer getDni() {
        return dni;
    }

    public void setDni(@NotNull(message = "dato obligatorio") Integer dni) {
        this.dni = dni;
    }

    public @NotNull(message = "dato obligatorio") @Min(value = 10000000000L, message = "El CUIL debe tener 11 dígitos") @Max(value = 99999999999L, message = "El CUIL debe tener 11 dígitos") Long getCuil() {
        return cuil;
    }

    public void setCuil(@NotNull(message = "dato obligatorio") @Min(value = 10000000000L, message = "El CUIL debe tener 11 dígitos") @Max(value = 99999999999L, message = "El CUIL debe tener 11 dígitos") Long cuil) {
        this.cuil = cuil;
    }

    public @NotEmpty(message = "dato obligatorio") String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(@NotEmpty(message = "dato obligatorio") String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getGrupoSanguineo() {
        return grupoSanguineo;
    }

    public void setGrupoSanguineo(String grupoSanguineo) {
        this.grupoSanguineo = grupoSanguineo;
    }

    public Long getTelFijo() {
        return telFijo;
    }

    public void setTelFijo(Long telFijo) {
        this.telFijo = telFijo;
    }

    public @NotNull(message = "dato obligatorio") Long getTelMovil() {
        return telMovil;
    }

    public void setTelMovil(@NotNull(message = "dato obligatorio") Long telMovil) {
        this.telMovil = telMovil;
    }

    public @Email @NotEmpty(message = "dato obligatorio") String getEmail() {
        return email;
    }

    public void setEmail(@Email @NotEmpty(message = "dato obligatorio") String email) {
        this.email = email;
    }

    public @NotEmpty(message = "dato obligatorio") String getCalle() {
        return calle;
    }

    public void setCalle(@NotEmpty(message = "dato obligatorio") String calle) {
        this.calle = calle;
    }

    public @NotNull(message = "dato obligatorio") Integer getNumero() {
        return numero;
    }

    public void setNumero(@NotNull(message = "dato obligatorio") Integer numero) {
        this.numero = numero;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public @NotEmpty(message = "dato obligatorio") String getBarrio() {
        return barrio;
    }

    public void setBarrio(@NotEmpty(message = "dato obligatorio") String barrio) {
        this.barrio = barrio;
    }

    public @NotEmpty(message = "dato obligatorio") String getDistrito() {
        return distrito;
    }

    public void setDistrito(@NotEmpty(message = "dato obligatorio") String distrito) {
        this.distrito = distrito;
    }

    public @NotEmpty(message = "dato obligatorio") String getProvincia() {
        return provincia;
    }

    public void setProvincia(@NotEmpty(message = "dato obligatorio") String provincia) {
        this.provincia = provincia;
    }

    public @NotNull(message = "dato obligatorio") Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(@NotNull(message = "dato obligatorio") Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public MultipartFile getArchivo() {
        return archivo;
    }

    public void setArchivo(MultipartFile archivo) {
        this.archivo = archivo;
    }
}
