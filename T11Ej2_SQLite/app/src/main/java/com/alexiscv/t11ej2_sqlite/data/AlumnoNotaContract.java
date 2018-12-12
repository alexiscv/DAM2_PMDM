package com.alexiscv.t11ej2_sqlite.data;

import android.provider.BaseColumns;

/**
 * Esquema para la BASE DE DATOS
 * En forma de constantes declaramos el nombre de la db
 * las tablas y las columnas
 * para poder usarlo de forma global.
 */
public class AlumnoNotaContract {

    //nombre de la base de datos
    public static final String DATABASE_NAME = "NotasAlumnos.db";

    //constructor privado para prevenir instanciar por error
    private AlumnoNotaContract() {
    }

    //clase interna que define la tabla AlumnoNota
    public static class AlumnoNotaEntry implements BaseColumns {
        public static final String TABLE_NAME = "alumnoNota";

        public static final String COLUMN_NAME_NOMBRE_ALUMNO = "nombre";
        public static final String COLUMN_NAME_NOMBRE_ASIGNATURA = "asignatura";
        public static final String COLUMN_NAME_NOTA = "nota";
        public static final String COLUMN_NAME_NOMBRE_PROFESOR = "profesor";

    }
}
