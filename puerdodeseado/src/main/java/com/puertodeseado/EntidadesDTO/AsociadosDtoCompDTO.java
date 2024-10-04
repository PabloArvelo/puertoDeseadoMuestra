package com.puertodeseado.EntidadesDTO;


import jakarta.validation.Valid;

public class AsociadosDtoCompDTO {
    @Valid
    private AsociadoDTO asociadoDTO;
    @Valid
    private AsociadoFotoDTO asociadoFotoDTO;

    @Valid
    private AsociadoHabilitacionDTO asociadoHabilitacionDTO;

    public AsociadosDtoCompDTO() {
    }

    public AsociadoDTO getAsociadoDTO() {
        return asociadoDTO;
    }

    public void setAsociadoDTO(AsociadoDTO asociadoDTO) {
        this.asociadoDTO = asociadoDTO;
    }

    public AsociadoFotoDTO getAsociadoFotoDTO() {
        return asociadoFotoDTO;
    }

    public void setAsociadoFotoDTO(AsociadoFotoDTO asociadoFotoDTO) {
        this.asociadoFotoDTO = asociadoFotoDTO;
    }

    public AsociadoHabilitacionDTO getAsociadoHabilitacionDTO() {
        return asociadoHabilitacionDTO;
    }

    public void setAsociadoHabilitacionDTO(AsociadoHabilitacionDTO asociadoHabilitacionDTO) {
        this.asociadoHabilitacionDTO = asociadoHabilitacionDTO;
    }
}
