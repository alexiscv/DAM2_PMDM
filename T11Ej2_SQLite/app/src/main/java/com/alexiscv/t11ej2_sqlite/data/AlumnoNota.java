package com.alexiscv.t11ej2_sqlite.data;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Esta clase es una chapuza, debería ser mas compleja
 * pero lo importante es comprender el uso de SQLite
 */
public class AlumnoNota {

    // Atributos del Obj.
    private String nombreAlumno;
    private String nombreAsignatura;
    private double nota;
    private String profesor;

    /**
     * Constructor del Obj. AlumnoNota
     * Se le pasan todos los paremetros
     *
     * @param nombreAlumno
     * @param nombreAsignatura
     * @param nota
     * @param profesor
     */
    public AlumnoNota(String nombreAlumno, String nombreAsignatura, double nota, String profesor) {
        this.nombreAlumno = nombreAlumno;
        this.nombreAsignatura = nombreAsignatura;
        this.nota = nota;
        this.profesor = profesor;
    }

    /**
     * Segundo Constructor, se le pasará un Cursor.
     * Ese cursor contiene los datos del AlumnoNota a crear.
     *
     * @param c
     */
    public AlumnoNota(Cursor c) {
        this.nombreAlumno = c.getString(c.getColumnIndex(AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOMBRE_ALUMNO));
        this.nombreAsignatura = c.getString(c.getColumnIndex(AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOMBRE_ASIGNATURA));
        this.nota = c.getDouble(c.getColumnIndex(AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOTA));
        this.profesor = c.getString(c.getColumnIndex(AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOMBRE_PROFESOR));
    }

    public String getNombreAlumno() {
        return nombreAlumno;
    }

    public void setNombreAlumno(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public String getNombreAsignatura() {
        return nombreAsignatura;
    }

    public void setNombreAsignatura(String nombreAsignatura) {
        this.nombreAsignatura = nombreAsignatura;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    /**
     * Devuelve un objeto ContentValue con los datos de esta entrada,
     * para luego insertar en BD.
     *
     * @return
     */
    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
        cv.put(AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOMBRE_ALUMNO, nombreAlumno);
        cv.put(AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOMBRE_ASIGNATURA, nombreAsignatura);
        cv.put(AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOTA, nota);
        cv.put(AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOMBRE_PROFESOR, profesor);
        return cv;
    }

    @Override
    public String toString() {
        return "{" +
                "Alumno='" + nombreAlumno + '\'' +
                "--'" + nombreAsignatura + '\'' +
                "--" + nota +
                "--'" + profesor + '\'' +
                '}';
    }
}
