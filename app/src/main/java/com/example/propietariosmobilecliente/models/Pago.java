package com.example.propietariosmobilecliente.models;

import java.io.Serializable;
import java.time.LocalDate;

public class Pago implements Serializable {
    private int numeroPago;
    private LocalDate fechaPago;
    private Double importePago;

    public int getNumeroPago() {
        return numeroPago;
    }

    public void setNumeroPago(int numeroPago) {
        this.numeroPago = numeroPago;
    }

    public LocalDate getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDate fechaPago) {
        this.fechaPago = fechaPago;
    }

    public Double getImportePago() {
        return importePago;
    }

    public void setImportePago(Double importePago) {
        this.importePago = importePago;
    }
}
