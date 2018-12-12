package com.alexiscv.t11ej1_preferenciasparapantalladeconfiguracion;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciamos el TextView donde vamos a escribir
        TextView mostrarPreferencias = findViewById(R.id.mostrarPreferencias);

        // Cargamos las preferencias
        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        // Cargamos los valores en el TextView
        String nombrePref = sharedPref.getString("pref_username", "");
        boolean borrarPref = sharedPref.getBoolean("pref_key_auto_delete", false);

        mostrarPreferencias.append("Nombre de usuario: " + nombrePref + "\n");
        mostrarPreferencias.append("Borrar correos automaticamente: " + borrarPref + "\n");

        // Muestro una preferencia que se modifica en
        // ModPreferenciasActivity
        boolean isJubilado = sharedPref.getBoolean("jubilado", false);
        mostrarPreferencias.append("Está jubilado: " + isJubilado + "\n");

    }

    public void abrirPreferencias(View view) {

        Intent i = new Intent(MainActivity.this, SettingsActivity.class);
        startActivity(i);


    }

    public void abrirPreferenciasMod(View view) {

        Intent i = new Intent(MainActivity.this, ModPreferenciasActivity.class);
        startActivity(i);

    }
}
