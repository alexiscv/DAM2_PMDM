package com.alexiscv.t11ej1_preferenciasparapantalladeconfiguracion;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ModPreferenciasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_preferencias);

        /**
         * Para poder guardar/modificar preferencias
         * podemos hacerlo usando el editor que nos ofrece el PreferenceManager
         */

        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean("jubilado", true);
        editor.putString("nombre", "Ana");
        editor.putInt("edad", 30);
        editor.commit();

        // De esta manera podemos almacenar información que no estará accesible
        // desde el apartado de SETTINGS
    }
}
