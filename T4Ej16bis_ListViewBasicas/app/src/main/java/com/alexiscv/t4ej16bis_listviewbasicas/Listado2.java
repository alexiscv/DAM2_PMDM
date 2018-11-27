package com.alexiscv.t4ej16bis_listviewbasicas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class Listado2 extends AppCompatActivity {

    private String[] arrayDatos;
    private TextView itemSeleccionado;
    private ListView listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listado2);

        // Referenciamos los elementos
        itemSeleccionado = findViewById(R.id.tvItemSeleccionado);
        listado = findViewById(R.id.lvLista);

        // Cargamos los valores al array desde strings.xml
        arrayDatos = getResources().getStringArray(R.array.arrayDatos);

        // Creamos un adaptador para mostrar los datos en la ListView
        // Le pasamos contexto, layout y datos
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayDatos);

        // Establecemos nuestro adaptador al Listado
        listado.setAdapter(adaptador);


    }
}
