package com.example.propietariosmobilecliente.models;

import com.google.gson.annotations.JsonAdapter;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pago implements Serializable {
    private int idPago;
    private int idContrato;
    private Contrato contrato;
    @JsonAdapter(Contrato.LocalDateTimeAdapter.class)
    private LocalDateTime fechaPago;
    private int numeroPago;
    private double importe;
    private String detalle;
    private boolean estado;

    public Pago() {
    }

    public Pago(int idPago, int idContrato, Contrato contrato, LocalDateTime fechaPago, int numeroPago, double importe, String detalle, boolean estado) {
        this.idPago = idPago;
        this.idContrato = idContrato;
        this.contrato = contrato;
        this.fechaPago = fechaPago;
        this.numeroPago = numeroPago;
        this.importe = importe;
        this.detalle = detalle;
        this.estado = estado;
    }

    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public int getNumeroPago() {
        return numeroPago;
    }

    public void setNumeroPago(int numeroPago) {
        this.numeroPago = numeroPago;
    }

    public double getImportePago() {
        return importe;
    }

    public void setImportePago(double importe) {
        this.importe = importe;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
