package com.example.propietariosmobilecliente.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Contrato implements Serializable{
    private int idContrato;
    private Propietario propietario;
    private Inquilino inquilino;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Double importeContrato;

    public Contrato(){}

    public Contrato(int idContrato, Propietario propietario, Inquilino inquilino, LocalDate fechaInicio, LocalDate fechaFin, Double importeContrato) {
        this.idContrato = idContrato;
        this.propietario = propietario;
        this.inquilino = inquilino;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.importeContrato = importeContrato;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
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
