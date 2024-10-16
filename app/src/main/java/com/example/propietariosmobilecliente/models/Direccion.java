package com.example.propietariosmobilecliente.models;

import java.io.Serializable;

public class Direccion implements Serializable {
    private int idDireccion;
    private String calle;
    private String altura;
    private String ciudad;
    private String coordenadas;

    public Direccion() {
    }

    public Direccion(int idDireccion, String calle, String altura, String ciudad, String coordenadas) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.altura = altura;
        this.ciudad = ciudad;
        this.coordenadas = coordenadas;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    @Override
    public String toString() {
        return calle +" "+ altura +", "+ ciudad;
    }
}
