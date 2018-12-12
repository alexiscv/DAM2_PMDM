package com.alexiscv.t11ej2_sqlite;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.alexiscv.t11ej2_sqlite.data.AlumnoNota;
import com.alexiscv.t11ej2_sqlite.data.AlumnoNotaDbHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private AlumnoNotaDbHelper alumnoNotaDbHelper;
    private TextView tvRegistros;
    private EditText etNombreBuscar, etNombreAlumno, etNombreAsignatura, etNota, etProfesor;
    private Button btBuscarPorNombre;
    private Button btLimpiarBusqueda;
    private Button btBorrarPorNombre;
    private Button btInsertar;
    private Button btActualizar;
    private ArrayList<AlumnoNota> registrosAlumnoNota = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initUI();

        //BASE DATOS ASISTENTE
        alumnoNotaDbHelper = new AlumnoNotaDbHelper(this);

        //obtengo todos los registros
        cargarDatosBd();
    }


    private void initUI() {

        tvRegistros = (TextView) findViewById(R.id.tvRegistrosBD);
        etNombreBuscar = (EditText) findViewById(R.id.etNombreBuscar);
        etNombreAlumno = (EditText) findViewById(R.id.etNombreAlumno);
        etNombreAsignatura = (EditText) findViewById(R.id.etAsignatura);
        etNota = (EditText) findViewById(R.id.etNota);
        etProfesor = (EditText) findViewById(R.id.etProfesor);


        btBuscarPorNombre = (Button) findViewById(R.id.btBuscarPorNombre);
        btBuscarPorNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mostrar(alumnoNotaDbHelper.getAlumnoNotaByNombre(etNombreBuscar.getText().toString()));
            }
        });
        btLimpiarBusqueda = (Button) findViewById(R.id.btResetBusqueda);
        btLimpiarBusqueda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                limpiarCampos();
                cargarDatosBd();
            }
        });
        btBorrarPorNombre = (Button) findViewById(R.id.btBorrarPorNombre);
        btBorrarPorNombre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!TextUtils.isEmpty(etNombreBuscar.getText())) {
                    alumnoNotaDbHelper.deleteAlumnoNota(etNombreBuscar.getText().toString());
                    cargarDatosBd();
                    limpiarCampos();
                }
            }
        });

        btInsertar = (Button) findViewById(R.id.btInsertar);
        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (datosAlumnoInsertarValidos()) {
                    alumnoNotaDbHelper.insertarAlumnoNotas(new AlumnoNota(etNombreAlumno.getText().toString(),
                            etNombreAsignatura.getText().toString(),
                            Double.parseDouble(etNota.getText().toString()),
                            etProfesor.getText().toString()));
                    cargarDatosBd();
                    limpiarCampos();
                }
            }
        });

        btActualizar = (Button) findViewById(R.id.btActualizar);
        btActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (datosAlumnoInsertarValidos()) {
                    String nombre = etNombreAlumno.getText().toString();
                    String asignatura = etNombreAsignatura.getText().toString();
                    alumnoNotaDbHelper.updateAlumnoNota(new AlumnoNota(nombre, asignatura,
                                    Double.parseDouble(etNota.getText().toString()),
                                    etProfesor.getText().toString()),
                            nombre,
                            asignatura);
                    cargarDatosBd();
                    limpiarCampos();
                }
            }
        });
    }


    /**
     * Método que comprueba que los datos sean correctos antes de insertar
     *
     * @return true si son correctos, false si están vacios o la nota no es numerico.
     */
    private boolean datosAlumnoInsertarValidos() {
        if (TextUtils.isEmpty(etNombreAlumno.getText()))
            return false;
        if (TextUtils.isEmpty(etNombreAsignatura.getText()))
            return false;
        if (TextUtils.isEmpty(etNota.getText()))
            return false;
        if (TextUtils.isEmpty(etProfesor.getText()))
            return false;
        try {
            Double.parseDouble(etNota.getText().toString());
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Método que limpia los editText
     */
    private void limpiarCampos() {
        etNombreBuscar.setText("");
        etNombreAlumno.setText("");
        etNombreAsignatura.setText("");
        etNota.setText("");
        etProfesor.setText("");
    }


    /**
     * Metodo que muestra los datos contenidos en un cursor en pantalla
     *
     * @param c cursor con los datos de un alumno o varios
     */
    private void mostrar(Cursor c) {
        if (c != null && c.getCount() > 0) {
            tvRegistros.setText("");
            while (c.moveToNext()) {
                AlumnoNota a = new AlumnoNota(c);
                tvRegistros.append(a.toString() + "\n");
            }
        } else {
            tvRegistros.setText("No hay registros con ese nombre");
        }
    }


    /**
     * Metodo que carga los datos de la BD en un arrayList y además
     * los muestra en el TextView
     */
    private void cargarDatosBd() {

        Cursor c = alumnoNotaDbHelper.getAllAlumnosNotas();
        if (c != null && c.getCount() > 0) {
            tvRegistros.setText("");
            while (c.moveToNext()) {
                AlumnoNota a = new AlumnoNota(c);
                registrosAlumnoNota.add(a);
                tvRegistros.append(a.toString() + "\n");
            }
        } else {
            tvRegistros.setText("No hay registros");
        }


    }
}
