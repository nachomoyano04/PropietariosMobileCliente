package com.example.propietariosmobilecliente.models;

import java.io.Serializable;

public class Inquilino implements Serializable {
    private int idInquilino;
    private String dni;
    private String apellido;
    private String nombre;
    private String telefono;
    private String correo;
    private boolean estado;

    public Inquilino() {
    }

    public Inquilino(int idInquilino, String dni, String apellido, String nombre, String telefono, String correo, boolean estado) {
        this.idInquilino = idInquilino;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.estado = estado;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String nombreYApellido() {
        return nombre+" "+apellido;
    }
}
