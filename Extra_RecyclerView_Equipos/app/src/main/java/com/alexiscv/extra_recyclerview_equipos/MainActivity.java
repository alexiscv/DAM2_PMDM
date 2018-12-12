package com.alexiscv.extra_recyclerview_equipos;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * IMPORTANTE
 * antes de nada, aadir esto al Grandle APP
 * implementation 'com.android.support:recyclerview-v7:28.0.0'
 */
public class MainActivity extends AppCompatActivity {

    private final int COD_PETICION_INSERT = 1234;
    private RecyclerView rvListadoEquipos;
    //private String[] arrayDatosEquipos = {"Barcelona", "Real Madrid", "Atlético", "Valencia", "Sevilla", "Málaga", "Celta", "Villarreal", "Athletic", "Getafe", "Rayo", "Éibar", "Levante", "Espanyol", "Granada", "R.Sociedad", "Almería", "Deportivo", "Elche", "Córdoba"};
    public static ArrayList<Equipo> arrayDatosEquipos = new ArrayList<>();
    private AdaptadorEquipos adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cargar los datos, solo si está vacio
        // Al destruir y crear la actividad, si no controlamos
        // esto, se irian duplicando los datos.
        if (arrayDatosEquipos.isEmpty()) {
            cargarDatos();
        }

        // Referenciamos
        rvListadoEquipos = findViewById(R.id.rvListadoEquipos);

        // Creamos el adaptador
        adaptador = new AdaptadorEquipos(arrayDatosEquipos);

        // Iniciamos el RecyclerView
        rvListadoEquipos.setHasFixedSize(true); // opcional, probarlo, es algo del tamaño
        rvListadoEquipos.setAdapter(adaptador);

        // Indica al RecyclerView como distribuir los items/elementos del listado
        // 3 opciones a continuaciób...
        rvListadoEquipos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //rvListaTitulares.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        //rvListaTitulares.setLayoutManager(new GridLayoutManager(this,3));

        // Extras, ItemDecoration & Animator
        rvListadoEquipos.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvListadoEquipos.setItemAnimator(new DefaultItemAnimator());

        // Listener
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "Has pulsado en " + rvListadoEquipos.getChildAdapterPosition(v), Toast.LENGTH_SHORT).show();

                // Abrirmos otra activity para mostrar el equipo
                Intent i = new Intent(getApplicationContext(), VerEquiposActivity.class);

                // Le pasamos la información como EXTRA
                i.putExtra("posicion", rvListadoEquipos.getChildAdapterPosition(v));

                startActivity(i);

            }
        });

    }

    /**
     * Carga los datos de los equipos al array
     */
    private void cargarDatos() {

        // Referenciamos los elementos
        String[] nombres = getResources().getStringArray(R.array.nombre_equipo);
        int[] puntos = getResources().getIntArray(R.array.puntos_equipo);
        TypedArray objetos = getResources().obtainTypedArray(R.array.escudo_equipo);

        // Creamos un array de imagenes
        Drawable[] imagenes = new Drawable[objetos.length()];

        // Recorremos el array de escudos de equipos
        // añadiendo las imagenes a nuestro array de imagenes
        for (int i = 0; i < objetos.length(); i++) {
            imagenes[i] = objetos.getDrawable(i);
        }

        // Recorremos el array de nombres, añadiendo los elementos al ArrayList de equipos
        for (int i = 0; i < nombres.length; i++) {
            arrayDatosEquipos.add(new Equipo(nombres[i], imagenes[i], puntos[i]));
        }
    }

    /**
     * El método que crea el menú
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if
        // it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    /**
     * Método que detecta que hemos pulsado un item
     * del menú.
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_aniadir:
                lanzarActivityAniadir();
                return true;
            case R.id.action_borrar:
                borrarEquipoSeleccionado();
                return true;
            default:
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Método que borra el equipo que esté seleccionado en la lista
     * Es llamado desde una opción de un menú de opciones (BORRAR)
     */
    private void borrarEquipoSeleccionado() {
    }

    /**
     * Método que lanza la actividad AniadirActivity
     * Es llamado desde una opción del menú de opción (AÑADIR)
     */
    private void lanzarActivityAniadir() {
        Intent i = new Intent(MainActivity.this, AniadirActivity.class);

        // Al lanzar la activity con startActivityForResult
        // Le decimos que estamos a la espera de una respuesta por parte
        // de la actividad que acabamos de abrir.
        startActivityForResult(i, COD_PETICION_INSERT);

    }

    /**
     * Recoge el resultado de la Actividad Añadir
     * Estamos esperando por este resultado desde que se pulsó
     * el botón AÑADIR en el MAIN
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == COD_PETICION_INSERT) {
            if (resultCode == RESULT_OK) {
                String resultadoInsercion = data.getStringExtra(AniadirActivity.RESULTADO_INSERCION);
                if (resultadoInsercion.equals("OK")) {
                    //notifico al adaptador para que recargue lista
                    adaptador.notifyDataSetChanged();
                }
            } else {
                Toast.makeText(this, "INSERCIÓN CANCELADA: Equipo nuevo no insertado", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
