package com.alexiscv.t5ejem_fragmentsestaticos;

import java.util.List;

public class Filosofo {

    private int idFoto;
    private String nombre;
    private List<String> citas;

    public Filosofo(int idFoto, String nombre, List<String> citas) {
        this.idFoto = idFoto;
        this.nombre = nombre;
        this.citas = citas;
    }

    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<String> getCitas() {
        return citas;
    }

    public void setCitas(List<String> citas) {
        this.citas = citas;
    }

    @Override
    public String toString() {
        return "Filosofo{" +
                "idFoto=" + idFoto +
                ", nombre='" + nombre + '\'' +
                ", citas=" + citas +
                '}';
    }
}
