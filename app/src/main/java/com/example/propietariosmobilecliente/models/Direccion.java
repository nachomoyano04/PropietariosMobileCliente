package com.example.propietariosmobilecliente.models;

import java.io.Serializable;

public class Direccion implements Serializable {
    private int idDireccion;
    private String calle;
    private String altura;
    private String ciudad;

    public Direccion() {
    }

    public Direccion(int idDireccion, String calle, String altura, String ciudad) {
        this.idDireccion = idDireccion;
        this.calle = calle;
        this.altura = altura;
        this.ciudad = ciudad;
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

    @Override
    public String toString() {
        return calle +" "+ altura +", "+ ciudad;
    }
}
