package com.alexiscv.t4ej16bis_listviewbasicas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private String[] arrayDatos = {"Patatas", "Macarrones", "Arroz", "Pollo", "Salmón", "Cebolla", "Lechuga", "Mandarinas", "Leche", "Huvos", "Chorizo", "Platanos", "Chocolate", "Yogures", "Jamón de York"};
    private ListView listado;
    private TextView itemSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciamos la Lista y el TextView de item seleccionado
        listado = findViewById(R.id.lvLista);
        itemSeleccionado = findViewById(R.id.tvItemSeleccionado);

        // Creamos el adaptado para poder cargar contenido a la lista
        // Le pasaremos: contexto, layout y datos
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayDatos);

        // Establecemos el adaptador del ListView
        listado.setAdapter(adaptador);

        /**
         * Listener para detectar cuando se pulsa en un elemento de la lista
         */
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Vamos a tomar el elemento del array de datos usando la posición
                // y a mostrar su nombre en el TextView "tvItemSeleccionado"
                itemSeleccionado.setText("Has seleccionado: " + arrayDatos[position]);

            }
        });

    }

    /**
     * Abre la activity de Listado2
     *
     * @param view
     */
    public void verListado2(View view) {

        Intent intent = new Intent(this, Listado2.class);
        startActivity(intent);

    }
}
