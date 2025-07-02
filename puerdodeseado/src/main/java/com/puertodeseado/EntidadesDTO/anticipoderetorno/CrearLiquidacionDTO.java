package com.puertodeseado.EntidadesDTO.anticipoderetorno;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.time.YearMonth;

public class CrearLiquidacionDTO {

    @NotNull(message = "*")
    private YearMonth periodoNuevaLiquidacion;
    //@NotNull(message = "*")
    private MultipartFile archivoCSV;

    public CrearLiquidacionDTO() {
    }

    public CrearLiquidacionDTO(YearMonth periodoNuevaLiquidacion, MultipartFile archivoCSV) {
        this.periodoNuevaLiquidacion = periodoNuevaLiquidacion;
        this.archivoCSV = archivoCSV;
    }

    public YearMonth getPeriodoNuevaLiquidacion() {
        return periodoNuevaLiquidacion;
    }

    public void setPeriodoNuevaLiquidacion(YearMonth periodoNuevaLiquidacion) {
        this.periodoNuevaLiquidacion = periodoNuevaLiquidacion;
    }

    public MultipartFile getArchivoCSV() {
        return archivoCSV;
    }

    public void setArchivoCSV(MultipartFile archivoCSV) {
        this.archivoCSV = archivoCSV;
    }

    @Override
    public String toString() {
        return "CrearLiquidacionDTO{" +
                "periodoNuevaLiquidacion=" + periodoNuevaLiquidacion +
                ", archivoCSV=" + (archivoCSV != null ? archivoCSV.getOriginalFilename() : "null") +
                '}';
    }
}
