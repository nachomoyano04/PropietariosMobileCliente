package com.example.propietariosmobilecliente.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Contrato implements Serializable {
//    private Propietario propietario;
    private String propietario;
//    private Inquilino inquilino
    private String inquilino;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double importeContrato;

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getInquilino() {
        return inquilino;
    }

    public void setInquilino(String inquilino) {
        this.inquilino = inquilino;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Double getImporteContrato() {
        return importeContrato;
    }

    public void setImporteContrato(Double importeContrato) {
        this.importeContrato = importeContrato;
    }
}
