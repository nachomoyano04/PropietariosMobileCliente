package com.example.propietariosmobilecliente.models;

import java.io.Serializable;

public class Inmueble implements Serializable {
    private String direccion;
    private int imagen;
    private String tipo;
    private Double precio;

    public Inmueble() {
    }

    public Inmueble(String direccion, int imagen, String tipo, Double precio) {
        this.direccion = direccion;
        this.imagen = imagen;
        this.tipo = tipo;
        this.precio = precio;
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
}
