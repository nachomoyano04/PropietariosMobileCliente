package com.example.propietariosmobilecliente.models;

import java.io.Serializable;

public class Inmueble implements Serializable {
    private int idInmueble;
    private Propietario propietario;
    private int idPropietario;
    private String direccion;
    private int idDireccion;
    private String tipo;
    private int idTipo;
    private String metros2;
    private int cantidadAmbientes;
    private boolean disponible;
    private Double precio;
    private String descripcion;
    private boolean cochera;
    private boolean piscina;
    private boolean mascotas;
    private boolean estado;
    private String urlImagen;

    public Inmueble() {
    }

    public Inmueble(int idInmueble, Propietario propietario, int idPropietario, String direccion, int idDireccion, String tipo, int idTipo, String metros2, int cantidadAmbientes, boolean disponible, Double precio, String descripcion, boolean cochera, boolean piscina, boolean mascotas, boolean estado, String urlImagen) {
        this.idInmueble = idInmueble;
        this.propietario = propietario;
        this.idPropietario = idPropietario;
        this.direccion = direccion;
        this.idDireccion = idDireccion;
        this.tipo = tipo;
        this.idTipo = idTipo;
        this.metros2 = metros2;
        this.cantidadAmbientes = cantidadAmbientes;
        this.disponible = disponible;
        this.precio = precio;
        this.descripcion = descripcion;
        this.cochera = cochera;
        this.piscina = piscina;
        this.mascotas = mascotas;
        this.estado = estado;
        this.urlImagen = urlImagen;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public void setPropietario(Propietario propietario) {
        this.propietario = propietario;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdDireccion() {
        return idDireccion;
    }

    public void setIdDireccion(int idDireccion) {
        this.idDireccion = idDireccion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(int idTipo) {
        this.idTipo = idTipo;
    }

    public String getMetros2() {
        return metros2;
    }

    public void setMetros2(String metros2) {
        this.metros2 = metros2;
    }

    public int getCantidadAmbientes() {
        return cantidadAmbientes;
    }

    public void setCantidadAmbientes(int cantidadAmbientes) {
        this.cantidadAmbientes = cantidadAmbientes;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isCochera() {
        return cochera;
    }

    public void setCochera(boolean cochera) {
        this.cochera = cochera;
    }

    public boolean isPiscina() {
        return piscina;
    }

    public void setPiscina(boolean piscina) {
        this.piscina = piscina;
    }

    public boolean isMascotas() {
        return mascotas;
    }

    public void setMascotas(boolean mascotas) {
        this.mascotas = mascotas;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}
