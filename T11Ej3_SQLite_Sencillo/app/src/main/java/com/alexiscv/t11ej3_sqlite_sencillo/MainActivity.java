package com.alexiscv.t11ej3_sqlite_sencillo;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.nio.Buffer;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    TextView listadoRegistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciamos
        listadoRegistros = findViewById(R.id.listadoRegistros);

    }

    /**
     * Abre una conexión a la BD
     */
    private void abrirConexionDb() {
        UsuariosSQLiteHelper usdbh =
                new UsuariosSQLiteHelper(this, "DBUsuarios", null, 1);

        db = usdbh.getWritableDatabase();
    }

    /**
     * Cierra la conexión con la DB.
     */
    private void cerrarConexionDb() {
        db.close();
    }

    /**
     * Añade un registro a la BD
     *
     * @param view
     */
    public void addRegistro(View view) {

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        abrirConexionDb();

        //Insertar un registro
        db.execSQL("INSERT INTO Usuarios (codigo,nombre) VALUES (99,'KOLDO') ");

        // Cerramos la base de datos
        cerrarConexionDb();

    }

    public void modRegistro(View view) {
        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        abrirConexionDb();

        //Actualizar un registro
        db.execSQL("UPDATE Usuarios SET nombre='LOGAN' WHERE codigo=99 ");

        // Cerramos la base de datos
        cerrarConexionDb();

    }

    public void delRegistro(View view) {
        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        abrirConexionDb();

        //Eliminar un registro
        db.execSQL("DELETE FROM Usuarios WHERE codigo<>0 ");

        // Cerramos la base de datos
        cerrarConexionDb();

    }

    /**
     * Recupera un listado de los registros de la base de datos
     *
     * @param view
     */
    public void getRegistros(View view) {

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        abrirConexionDb();

        // Guardamos en un cursor la sentencia SELECT
        Cursor c = db.rawQuery(" SELECT codigo,nombre FROM Usuarios", null);

        // Muestra los registros
        mostrarRegistros(c);

        // Cerrar conexión
        cerrarConexionDb();

    }

    /**
     * Recorre los registros que se le pasan en un cursor y los muestra
     *
     * @param c
     */
    private void mostrarRegistros(Cursor c) {

        StringBuffer listado = new StringBuffer();

        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya más registros
            do {
                String codigo = c.getString(0);
                String nombre = c.getString(1);

                listado.append(codigo + " - " + nombre + "\n");

            } while (c.moveToNext());
        }else{
            listado.append("No hay registros para mostrar.");
        }

        listadoRegistros.setText(listado.toString());

    }


    /**
     * Devuelve un registro en concreto
     *
     * @param view
     */
    public void getRegistro(View view) {

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        abrirConexionDb();

        // Guardamos en un cursor la sentencia SELECT
        Cursor c = db.rawQuery(" SELECT codigo,nombre FROM Usuarios WHERE nombre='usu1' ", null);

        // Muestra los registros
        mostrarRegistros(c);

        // Cerrar
        cerrarConexionDb();

    }

    public void contarRegistros(View view) {

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        abrirConexionDb();

        // Guardamos en un cursor la sentencia SELECT
        Cursor c = db.rawQuery(" SELECT codigo,nombre FROM Usuarios", null);

        // Mostramos el total
        listadoRegistros.setText("Hay un total de " + c.getCount() + " registro(s).");

        // Cerrar
        cerrarConexionDb();

    }

    /**
     * Cargamos datos de prueba
     *
     * @param view
     */
    public void cargarDatosPrueba(View view) {

        //Abrimos la base de datos 'DBUsuarios' en modo escritura
        abrirConexionDb();

        //Si hemos abierto correctamente la base de datos
        if (db != null) {

            //Insertamos 5 usuarios de ejemplo
            for (int i = 1; i <= 5; i++) {
                //Generamos los datos
                int codigo = i;
                String nombre = "Usuario" + i;

                //Insertamos los datos en la tabla Usuarios
                db.execSQL("INSERT INTO Usuarios (codigo, nombre) " +
                        "VALUES (" + codigo + ", '" + nombre + "')");
            }

            // Cerramos la base de datos
            cerrarConexionDb();

        }

    }
}
