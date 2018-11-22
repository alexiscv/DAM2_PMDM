package com.alexiscv.t4ejem_spinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Atributos
    private Spinner desplegable;
    private TextView mensajes;
    private String[] listadoPlanetas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Atributos
        desplegable = findViewById(R.id.desplegable);
        mensajes = findViewById(R.id.mensaje);
        listadoPlanetas = getResources().getStringArray(R.array.planetas);

        // ArrayAdapter usando un StringArray y un layout por defecto
        ArrayAdapter<CharSequence> adaptador = ArrayAdapter.createFromResource(this, R.array.planetas, android.R.layout.simple_spinner_item);

        // Especifica el layout a usar cuando la lista de opciones aparece
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asociar el Prompt al Spinner
        desplegable.setPromptId(R.string.prompt_desplegable);

        // Asocia el adaptador al spinner
        desplegable.setAdapter(adaptador);

        /**
         * Controlar la selección con un Listener
         */
        desplegable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                // Las acciones...
                mensajes.setText("Has seleccionado: " + listadoPlanetas[position]);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                // Code..
                mensajes.setText("NADA SELECCIONADO");


            }
        });

    }
}
