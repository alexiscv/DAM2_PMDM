package com.alexiscv.extra_recyclerview_equipos;

import android.graphics.drawable.Drawable;

class Equipo {

    private String nombre;
    private Drawable imagen;
    private int puntos;

    public Equipo(String nombreEquipo, Drawable imagenEscudo, int puntos) throws IllegalArgumentException {
        if (nombreEquipo == null || nombreEquipo.isEmpty()) {
            throw new IllegalArgumentException("ERROR: Nombre del equipo vacío. Obligatorio un nombre");
        } else if (imagenEscudo == null) {
            throw new IllegalArgumentException("ERROR: Equipo sin foto en el escudo. Marque la casilla 'Sin Foto' si no desea foto");
        } else {
            this.nombre = nombreEquipo;
            this.imagen = imagenEscudo;
            this.puntos = puntos;
        }
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Drawable getImagen() {
        return imagen;
    }

    public void setImagen(Drawable imagen) {
        this.imagen = imagen;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    @Override
    public String toString() {
        return "Equipo{" +
                "nombre='" + nombre + '\'' +
                ", imagen=" + imagen +
                ", puntos=" + puntos +
                '}';
    }
}
