package com.alexiscv.t4ejem_gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Atributos
    private String[] datos = new String[25];
    private GridView gridOpciones;
    private TextView tvItemElegido;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtengo las referencias a Objetos
        gridOpciones = findViewById(R.id.gridOpciones);
        tvItemElegido = findViewById(R.id.tvItemElegido);

        // Añadimos dataos autogenerados al array
        for (int i = 0; i <= 24; i++) {
            datos[i] = "DATO " + i;
        }

        // Creamos un adaptador
        // Pasandole el contexto, el layout y el array de datos
        final ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);

        // Establecemos el adaptador al GridView para que este muestre
        // los datos con el layout elegido
        gridOpciones.setAdapter(adaptador);

        // Creamos un Listener para controlar los clicks sobre los elementos del GridView
        gridOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Aisgno la opción elegida al TextView del layout
                String datoPulsado = ((TextView) view).getText().toString();
                tvItemElegido.setText(datoPulsado);

                // Otra opción
                // tvItemElegido.setText(dato[position]);
            }
        });
    }
}
