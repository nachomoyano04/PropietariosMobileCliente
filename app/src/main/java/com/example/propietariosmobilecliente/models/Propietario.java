package com.example.propietariosmobilecliente.models;

import java.io.Serializable;

public class Propietario {
    private int idPropietario;
    private String dni;
    private String apellido;
    private String nombre;
    private String telefono;
    private String correo;
    private String password;
    private String avatar;
    private boolean estado;

    public Propietario() {
    }

    public Propietario(int idPropietario, String dni, String apellido, String nombre, String telefono, String correo, String password, String avatar, boolean estado) {
        this.idPropietario = idPropietario;
        this.dni = dni;
        this.apellido = apellido;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.password = password;
        this.avatar = avatar;
        this.estado = estado;
    }

    public int getIdPropietario() {
        return idPropietario;
    }

    public void setIdPropietario(int idPropietario) {
        this.idPropietario = idPropietario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getNombreYApellido(){
        return nombre+" "+apellido;
    }
}
