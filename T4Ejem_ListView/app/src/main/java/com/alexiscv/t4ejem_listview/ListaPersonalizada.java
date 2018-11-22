package com.alexiscv.t4ejem_listview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListaPersonalizada extends Activity {

    private ArrayList<Usuario> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personalizada);

        // Inicializo el array de usuarios
        usuarios = new ArrayList<Usuario>();

        // Relleno el ArrayList
        rellenarArrayList();

        // Obtenemos la referencia a nuestra ListView
        ListView listaUsuarios = (ListView) findViewById(R.id.LstUsuarios);

        // Declaramos una variable de nuestra clase UsuariosAdapter
        UsuarioAdapter adaptador;

        // La inicializamos con su constructor
        adaptador = new UsuarioAdapter(this, usuarios);

        // Asignamos el adaptador al ListView
        listaUsuarios.setAdapter(adaptador);

        /**
         * Listener para los elementos
         */
        listaUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Recogemos el valor de la opción pulsada
                String opcionSeleccionada = ((Usuario)parent.getAdapter().getItem(position)).getNombre();

                // Mostramos el texto en el TextView
                TextView lblEtiqueta = findViewById(R.id.lblEtiqueta);
                lblEtiqueta.setText("Opción seleccionada: " + opcionSeleccionada);

            }
        });

    }

    private void rellenarArrayList() {
        usuarios.add(new Usuario("Laura", "López Gómez", "Oviedo"));
        usuarios.add(new Usuario("Mario", "García Tomás", "Gijón"));
        usuarios.add(new Usuario("Inés", "Ortega y Gasset", "Avilés"));
        usuarios.add(new Usuario("Pedro", "Martínez Castañón", "Madrid"));
        usuarios.add(new Usuario("Sandra", "Pascual Uría", "Barcelona"));
        usuarios.add(new Usuario("Adrián", "García García", "Oviedo"));
        usuarios.add(new Usuario("Aroa", "Uría Castañón", "Madrid"));
        usuarios.add(new Usuario("Benito", "Fernández Uría", "Barcelona"));
        usuarios.add(new Usuario("Cristiano", "Ronaldo García", "Oviedo"));
        usuarios.add(new Usuario("Damián", "Martínez Rojas", "Madrid"));
        usuarios.add(new Usuario("Estela", "Pascual Norte", "Barcelona"));
        usuarios.add(new Usuario("Francisco", "García Zarracina", "Oviedo"));

    }
}
