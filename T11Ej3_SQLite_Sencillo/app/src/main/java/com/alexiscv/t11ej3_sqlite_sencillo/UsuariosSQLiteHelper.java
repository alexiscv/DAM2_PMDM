package com.alexiscv.t11ej3_sqlite_sencillo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper {

    //Sentencia SQL para crear la tabla de Usuarios
    String sqlCreate = "CREATE TABLE Usuarios (codigo INTEGER, nombre TEXT)";

    /**
     * Constructor
     *
     * @param contexto
     * @param nombre
     * @param factory
     * @param version
     */
    public UsuariosSQLiteHelper(Context contexto, String nombre,
                                SQLiteDatabase.CursorFactory factory, int version) {
        super(contexto, nombre, factory, version);
    }

    /**
     * Ejecutado automáticamente por nuestra clase UsuariosDBHelper cuando sea
     * necesaria la creación de la base de datos. Es decir, cuando no exista.
     *
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Se ejecuta la sentencia SQL de creación de la dabla
        // execSQL() es un método sencillo, que ejecuta una sentencia SQL.
        db.execSQL(sqlCreate);

    }

    /**
     * Se lanza automáticamente cuando sea necesaria la actualización
     * de la estructura de la DB. Por ejemplo porque exite una actualización
     * de la aplicación y la BD tiene nuevos campos.
     *
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //NOTA: Por simplicidad del ejemplo aquí utilizamos directamente la opción de
        //      eliminar la tabla anterior y crearla de nuevo vacía con el nuevo formato.
        //      Sin embargo lo normal será que haya que migrar datos de la tabla antigua
        //      a la nueva, por lo que este método debería ser más elaborado.

        //Se elimina la versión anterior de la tabla
        db.execSQL("DROP TABLE IF EXISTS Usuarios");

        //Se crea la nueva versión de la tabla
        db.execSQL(sqlCreate);
    }
}
