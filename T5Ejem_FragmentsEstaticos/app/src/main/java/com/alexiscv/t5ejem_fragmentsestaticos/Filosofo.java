package com.alexiscv.t5ejem_fragmentsestaticos;

import java.util.List;

public class Filosofo {

    private int idFoto;
    private String nombre;
    private List<String> citas;

    /**
     * Constructor
     *
     * @param idFoto
     * @param nombre
     * @param citas
     */
    public Filosofo(int idFoto, String nombre, List<String> citas) {
        this.idFoto = idFoto;
        this.nombre = nombre;
        this.citas = citas;
    }

    /**
     * Getter & Setter
     */

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

    /**
     * ToString
     *
     * @return
     */
    @Override
    public String toString() {
        return "Filosofo{" +
                "idFoto=" + idFoto +
                ", nombre='" + nombre + '\'' +
                ", citas=" + citas +
                '}';
    }

    /**
     * Devuelve en un solo String, todas las citas de un filosofo
     *
     * @return
     */
    public String getCitasToString() {
        String citasString = "";
        for (String cita : citas) {
            citasString += cita + "\n\n";
        }
        return citasString;
    }
}
