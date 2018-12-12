package com.alexiscv.t11ej1_preferenciasparapantalladeconfiguracion;

import android.app.Activity;
import android.os.Bundle;

public class SettingsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Esta actividad no lleva layout asociado,
        // porque va a cargar el fragment

        // Mostrar el fragment como contenido principal
        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();

    }
}
