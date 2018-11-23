package com.alexiscv.t4ejem_listview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaConFoto extends Activity {

    private ArrayList<Animal> animales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_con_foto);

        // Inicializamos el array de animales
        animales = new ArrayList<>();

        // Cargamos los valores
        rellenarArrayAnimales();

        // Obtenemos la referencia de la ListView que mostrará la información
        ListView listaAnimales = findViewById(R.id.LstAnimales);

        // Creamos un adaptador para cargar la información y el estilo a la lista
        // El adaptador será nuestro AnimalAdapter
        AnimalAdapter adaptador = new AnimalAdapter(this, animales);

        // Aplicamos el adaptador a la lista
        listaAnimales.setAdapter(adaptador);

        /**
         * Listener para los elementos
         */
        listaAnimales.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Recogemos el valor de la opción pulsada
                String opcionSeleccionada = ((Animal)parent.getAdapter().getItem(position)).getNombre();

                // Mostramos el texto en el TextView
                TextView lblEtiqueta = findViewById(R.id.lblEtiqueta);
                lblEtiqueta.setText("Opción seleccionada: " + opcionSeleccionada);

            }
        });

    }

    private void rellenarArrayAnimales() {
        String[] animalesArray = getResources().getStringArray(R.array.array_animales);

        animales.add(new Animal(animalesArray[0], R.drawable.perro));
        animales.add(new Animal(animalesArray[1], R.drawable.gato));
        animales.add(new Animal(animalesArray[2], R.drawable.raton));
        animales.add(new Animal(animalesArray[3], R.drawable.loro));
        animales.add(new Animal(animalesArray[4], R.drawable.leon));
    }
}
