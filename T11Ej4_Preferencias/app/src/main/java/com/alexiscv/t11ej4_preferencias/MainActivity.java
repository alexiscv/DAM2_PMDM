package com.alexiscv.t11ej4_preferencias;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String preferencia1;
    private String preferencia2;
    boolean preferenciasGuardadas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Al cerrar la aplicación, guardamos las preferencias
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        guardarPreferencias();
    }

    /**
     * Al abrir la aplicación, cargamos las preferencias
     */
    @Override
    protected void onStart() {
        super.onStart();
        cargarPreferencias();

        // Para ver el funcionamiento, imprimimos preferencias si existen
        String mensaje = "";
        if (this.preferenciasGuardadas) {
            mensaje = "Las preferencias fueron guardadas ya";

        } else {
            mensaje = "Las preferencias todavía no se han guardado";
        }

        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    /**
     * Permite guardar las preferencias
     */
    public void guardarPreferencias() {
        SharedPreferences prefs = getSharedPreferences("PreferenciasMiApp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putBoolean("preferenciasGuardadas", true);
        editor.putString("preferencia1", "Hola estoy guardando esto");
        editor.putString("preferencia2", "y también esto");
        editor.commit();

        Toast.makeText(this, "Guardando preferencias", Toast.LENGTH_SHORT).show();

    }

    /**
     * Permite cargar las preferencias
     */
    public void cargarPreferencias() {

        SharedPreferences prefs = getSharedPreferences("PreferenciasMiApp", Context.MODE_PRIVATE);

        // Si no encuentra ningún valor, asignaría el string "valor por defecto" a preferencias 1 y 2
        this.preferencia1 = prefs.getString("preferencia1", "valor por defecto");
        this.preferencia2 = prefs.getString("preferencia2", "valor por defecto");
        preferenciasGuardadas = prefs.getBoolean("preferenciasGuardadas", false);

    }
}
