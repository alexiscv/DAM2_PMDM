package com.alexiscv.t4ej20_listviewoptimizadas;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listado;
    private ArrayList<Elemento> listadoElementos;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializo el ArrayList y lo lleno con datos
        // Si no lo inicicializase daría error
        listadoElementos = new ArrayList<>();
        llenarListado();

        // Referenciamos los elementos XML
        listado = findViewById(R.id.lvListado);
        textView = findViewById(R.id.tvItemSeleccionado);

        // Creamos el Adaptador
        // Le pasaremos contexto, layout y datos;
        //
        // *Importante, en este caso es un layout personalizado, así que lo crearemos.
        // Además el adaptador debe ser personalizado, para poder indicar como se posicionarán
        // los elementos en nuestro layout.
        ElementoAdapter adaptador = new ElementoAdapter(this, R.layout.elemento_layout, listadoElementos);

        // Establecemos nuestro adaptador al ListView
        listado.setAdapter(adaptador);

        /**
         * Listener para controlar las pulsaciones en los elementos del listado
         */
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                textView.setText("Has pulsado en: "+listadoElementos.get(position).getNombre());

            }
        });

    }

    /**
     * Método para cargar datos de prueba
     */
    private void llenarListado() {

        String[] nombres = getResources().getStringArray(R.array.nombres_elementos);
        TypedArray imagenes = getResources().obtainTypedArray(R.array.iconos_elementos);

        for (int i = 0; i < nombres.length; i++) {

            listadoElementos.add(new Elemento(nombres[i], imagenes.getDrawable(i)));

        }

        // Devuelvo el array iconos para poder reutilizarlo
        imagenes.recycle();

    }
}
