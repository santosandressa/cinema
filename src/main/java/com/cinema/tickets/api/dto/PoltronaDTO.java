package com.cinema.tickets.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotEmpty;

public class PoltronaDTO {

    @Schema(description = "Identificador da poltrona")
    private String id;

    @Schema(description = "Identificador da cadeira", example = "A1")
    @NotEmpty
    private String cadeira;

    @Schema(description = "Identificador da fileira", example = "A")
    @NotEmpty
    private String fileira;

    @Schema(description = "Status da poltrona", example = "DISPONIVEL")
    @NotEmpty
    private String status;

    public PoltronaDTO() {
    }

    public PoltronaDTO(String id, String cadeira, String fileira, String status) {
        this.id = id;
        this.cadeira = cadeira;
        this.fileira = fileira;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCadeira() {
        return cadeira;
    }

    public void setCadeira(String cadeira) {
        this.cadeira = cadeira;
    }

    public String getFileira() {
        return fileira;
    }

    public void setFileira(String fileira) {
        this.fileira = fileira;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}