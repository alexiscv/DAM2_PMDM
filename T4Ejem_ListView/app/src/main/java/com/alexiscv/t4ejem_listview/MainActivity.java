package com.alexiscv.t4ejem_listview;

import android.app.Activity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.opengl.EGLExt;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private ListView listado;
    private String[] ciudades = {"Arriondas", "Aviles", "Ribadesella", "Gijón", "Cangas de Onís", "Oviedo"};
    ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creamos las referencias
        listado = (ListView) findViewById(R.id.layoutListado);

        // Creamos un adaptador para el listado
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ciudades);

        // Asignamos el adaptador
        listado.setAdapter(adaptador);

    }

    public void verListadoPersonalizado(View v) {

        Intent listadoPersonalizado = new Intent(this, ListaPersonalizada.class);
        startActivity(listadoPersonalizado);

    }

    public void verListadoAnimales(View view) {

        Intent listadoAnimales = new Intent(this, ListaConFoto.class);
        startActivity(listadoAnimales);
    }
}
