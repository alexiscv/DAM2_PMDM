package com.alexiscv.asteroides;

import java.util.Vector;

public interface AlmacenPuntuaciones {
    /**
     * Método para guardar la puntuación de una partida, con los parámetros puntuación obtenida,
     * nombre del jugador y fecha de la partida.
     *
     * @param puntos
     * @param nombre
     * @param fecha
     */
    public void guardarPuntuacion(int puntos, String nombre, long fecha);

    /**
     * Método para obtener una lista de puntuaciones previamente almecenadas.
     * El parámetro cantidad indica el número máximo de puntuaciones
     * que ha de devolver
     *
     * @param cantidad
     * @return
     */
    public Vector<String> listaPuntuaciones(int cantidad);
}
