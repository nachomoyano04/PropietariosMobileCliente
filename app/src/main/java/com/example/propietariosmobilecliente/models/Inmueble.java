package com.example.propietariosmobilecliente.models;

import java.io.Serializable;

public class Inmueble implements Serializable {
    private String direccion;
    private int imagen;
    private String tipo;
    private Double precio;
    private String metros2;
    private boolean disponible;
    private int ambientes;
    private String uso;
    private String descripcion;

    public Inmueble() {
    }

    public Inmueble(String direccion, int imagen, String tipo, Double precio, String metros2, boolean disponible, int ambientes, String uso, String descripcion) {
        this.direccion = direccion;
        this.imagen = imagen;
        this.tipo = tipo;
        this.precio = precio;
        this.metros2 = metros2;
        this.disponible = disponible;
        this.ambientes = ambientes;
        this.uso = uso;
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getMetros2() {
        return metros2;
    }

    public void setMetros2(String metros2) {
        this.metros2 = metros2;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getAmbientes() {
        return ambientes;
    }

    public void setAmbientes(int ambientes) {
        this.ambientes = ambientes;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
