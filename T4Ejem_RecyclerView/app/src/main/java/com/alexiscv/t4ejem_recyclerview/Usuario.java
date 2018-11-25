package com.alexiscv.t4ejem_recyclerview;

public class Usuario {
    private String nombre;
    private String apellidos;
    private String direccion;

    public Usuario(String nombre, String apellidos, String direccion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

}