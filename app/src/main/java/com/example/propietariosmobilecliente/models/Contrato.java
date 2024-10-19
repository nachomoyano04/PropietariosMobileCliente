package com.example.propietariosmobilecliente.models;

import android.util.JsonReader;
import android.util.JsonWriter;

import com.google.gson.TypeAdapter;
import com.google.gson.annotations.JsonAdapter;

import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Contrato implements Serializable{
    private int idContrato;
    private Inmueble inmueble;
    private int idInmueble;
    private Inquilino inquilino;
    private int idInquilino;
    @JsonAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime fechaInicio;
    @JsonAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime fechaFin;
    @JsonAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime fechaAnulacion;
    private double monto;
    private boolean estado;

    public Contrato(){}

    public Contrato(int idContrato, Inmueble inmueble, int idInmueble, Inquilino inquilino, int idInquilino, LocalDateTime fechaInicio, LocalDateTime fechaFin, LocalDateTime fechaAnulacion, double monto, boolean estado) {
        this.idContrato = idContrato;
        this.inmueble = inmueble;
        this.idInmueble = idInmueble;
        this.inquilino = inquilino;
        this.idInquilino = idInquilino;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.fechaAnulacion = fechaAnulacion;
        this.monto = monto;
        this.estado = estado;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDateTime getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(LocalDateTime fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
        @Override
        public void write(com.google.gson.stream.JsonWriter out, LocalDateTime value) throws IOException {
            out.value(value.toString());

        }

        @Override
        public LocalDateTime read(com.google.gson.stream.JsonReader in) throws IOException {
            return LocalDateTime.parse(in.nextString());
        }
    }
}
