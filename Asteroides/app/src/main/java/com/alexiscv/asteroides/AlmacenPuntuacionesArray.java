package com.alexiscv.asteroides;

import java.util.Vector;

public class AlmacenPuntuacionesArray implements AlmacenPuntuaciones {

    private Vector<String> puntuaciones;

    /**
     * Constructor
     */
    public AlmacenPuntuacionesArray() {
        puntuaciones = new Vector<>();
        puntuaciones.add("123000 Pepito Dominguez");
        puntuaciones.add("111000 Pedro Martinez");
        puntuaciones.add("011000 Paco Pérez");
    }

    /**
     * Sobreescribimos nuestro método de interfaz
     *
     * @param puntos
     * @param nombre
     * @param fecha
     */
    @Override
    public void guardarPuntuacion(int puntos, String nombre, long fecha) {

        puntuaciones.add(0, puntos + " " + nombre);

    }

    /**
     * Sobreescribimos nuestro método de interfaz
     *
     * @param cantidad
     * @return
     */
    @Override
    public Vector<String> listaPuntuaciones(int cantidad) {
        return puntuaciones;
    }
}
