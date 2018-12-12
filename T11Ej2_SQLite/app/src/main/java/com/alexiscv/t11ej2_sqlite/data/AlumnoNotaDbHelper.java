package com.alexiscv.t11ej2_sqlite.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AlumnoNotaDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    private Context context;
    private static final String TEXT_TYPE = " TEXT";
    private static final String DOUBLE_TYPE = " REAL";

    private static final String COMMA_SEP = ",";
    private static final String ABRE_PARENTESIS = " (";
    private static final String CIERRA_PARENTESIS = ") ";

    //clave unica de la tabla AlumnoNota, como una primary key
    private static final String PRIMARY_KEY = " PRIMARY KEY" + ABRE_PARENTESIS + AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOMBRE_ALUMNO + COMMA_SEP + AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOMBRE_ASIGNATURA + CIERRA_PARENTESIS;

    //sentencia crear tabla AlumnoNota
    private static final String SQL_CREATE_TABLE = "CREATE TABLE " + AlumnoNotaContract.AlumnoNotaEntry.TABLE_NAME + ABRE_PARENTESIS + AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOMBRE_ALUMNO + TEXT_TYPE + COMMA_SEP + AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOMBRE_ASIGNATURA + TEXT_TYPE + COMMA_SEP + AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOTA + DOUBLE_TYPE + COMMA_SEP + AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOMBRE_PROFESOR + TEXT_TYPE + COMMA_SEP + PRIMARY_KEY + CIERRA_PARENTESIS;

    //sentencia borrar tabla AlumnoNOta
    private static final String SQL_DELETE_TABLE = "DROP TABLE IF EXISTS " + AlumnoNotaContract.AlumnoNotaEntry.TABLE_NAME;

    /**
     * Constructor
     *
     * @param context
     */
    public AlumnoNotaDbHelper(Context context) {
        super(context, AlumnoNotaContract.DATABASE_NAME, null, DATABASE_VERSION); //cursor_factory=null
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //crear tabla vacía
        db.execSQL(SQL_CREATE_TABLE);

        // Método que insertar datos ficticios para prueba inicial
        mockData(db);

    }

    /**
     * Método que inserta en la BD que se le pasa como parámetro
     * unos datos ficticios para hacer pruebas
     *
     * @param sqLiteDatabase base de datos donde se insertan los datos
     */
    private void mockData(SQLiteDatabase sqLiteDatabase) {
        mockAlumnoNota(sqLiteDatabase, new AlumnoNota("Carlos Perez", "Matematicas", 8.2, "Luis"));
        mockAlumnoNota(sqLiteDatabase, new AlumnoNota("Carlos Perez", "Lengua", 3, "Maria"));
        mockAlumnoNota(sqLiteDatabase, new AlumnoNota("Carlos Perez", "Inglés", 4, "Juan"));
        mockAlumnoNota(sqLiteDatabase, new AlumnoNota("Lucía", "Matematicas", 4, "Luis"));
        mockAlumnoNota(sqLiteDatabase, new AlumnoNota("Lucía", "Inglés", 10, "Juan"));
        mockAlumnoNota(sqLiteDatabase, new AlumnoNota("Andrea", "Matematicas", 9, "Luis"));
        mockAlumnoNota(sqLiteDatabase, new AlumnoNota("Andrea", "Lengua", 5, "Maria"));
    }

    /**
     * Método que inserta el registro de prueba que se le pasa, en la
     * BD que se le pasa
     *
     * @param db
     * @param registroAlumnoNota
     * @return
     */
    public long mockAlumnoNota(SQLiteDatabase db, AlumnoNota registroAlumnoNota) {
        return db.insert(AlumnoNotaContract.AlumnoNotaEntry.TABLE_NAME, null, registroAlumnoNota.toContentValues());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_TABLE);
        onCreate(db);

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    ////////////////////////////// MÉTODOS MANEJO BD ////////////////////////////////////////

    /**
     * Método   que insertar el alumno que se le pasa
     *
     * @param alumno alumno a insertar en la BD
     * @return long el ID de la nueva fila insertada o -1 si hubo errores
     */
    public long insertarAlumnoNotas(AlumnoNota alumno) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert(
                AlumnoNotaContract.AlumnoNotaEntry.TABLE_NAME,
                null,
                alumno.toContentValues());
    }

    /**
     * Método que devuelve un cursor con todos los alumnos de la BD, con sus notas
     *
     * @return Cursor el cursor con todos los registros de la BD
     */


    public Cursor getAllAlumnosNotas() {
        SQLiteDatabase db = getReadableDatabase();
        return db
                .query(
                        AlumnoNotaContract.AlumnoNotaEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
    }


    /**
     * Método que devuelve un cursor con los datos de un alumno cuyo nombre se le pasa
     *
     * @param nombreAlumno el nombre del alumno a buscar
     * @return Cursor el cursor con todos los datos de ese alumno
     */
    public Cursor getAlumnoNotaByNombre(String nombreAlumno) {
        Cursor c = getReadableDatabase().query(
                AlumnoNotaContract.AlumnoNotaEntry.TABLE_NAME,
                null,
                AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOMBRE_ALUMNO + " LIKE ?",
                new String[]{nombreAlumno},
                null,
                null,
                null);
        return c;
    }


    /**
     * Metodo que borra un alumno de la BD cuyo nombre se le pasa.
     *
     * @param nombreAlumno el nombre del alumno a borrar
     * @return int el número de filas afectadas si hay cláusula where, 0 en otro caso.
     */
    public int deleteAlumnoNota(String nombreAlumno) {
        return getWritableDatabase().delete(
                AlumnoNotaContract.AlumnoNotaEntry.TABLE_NAME,
                AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOMBRE_ALUMNO + " LIKE ?",
                new String[]{nombreAlumno});
    }


    /**
     * Método que actualiza un registro de la BD con los nuevos datos que se le pasan en forma de registro.
     * Al ser la clave nombre+asignatura, debemos pasar los dos datos
     *
     * @param registro     el registro que contiene los nuevos valores
     * @param nombreAlumno el nombre del alumno a actualizar
     * @param asignatura   la asignatura de ese alumno a actualizar
     * @return int el número de filas afectadas
     */
    public int updateAlumnoNota(AlumnoNota registro, String nombreAlumno, String asignatura) {
        return getWritableDatabase().update(
                AlumnoNotaContract.AlumnoNotaEntry.TABLE_NAME,
                registro.toContentValues(),
                AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOMBRE_ALUMNO + " LIKE ? AND " + AlumnoNotaContract.AlumnoNotaEntry.COLUMN_NAME_NOMBRE_ASIGNATURA + " LIKE ? ",
                new String[]{nombreAlumno, asignatura}
        );
    }

}
