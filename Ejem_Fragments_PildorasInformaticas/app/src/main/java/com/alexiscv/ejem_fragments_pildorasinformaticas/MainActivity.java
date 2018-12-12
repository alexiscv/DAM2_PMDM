package com.alexiscv.ejem_fragments_pildorasinformaticas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements ComunicaMenu {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Implementación de la interfaz ComunicaMenu
     * Este método es llamado desde el Fragment Menu
     * como nos encontramos en el mainActivity
     * debemos lanzar herramientasActivity, pasandole que botón ha sido pulsado
     * para que la nueva actividad pueda realizar las acciones oportunas
     *
     * @param queboton
     */
    @Override
    public void menu(int queboton) {

        // Abrimos la actividad HerramientasActivity
        Intent intent = new Intent(this, HerramientasActivity.class);

        // Le pasamos un bundle que viajará en el Intent
        intent.putExtra("BOTONPULSADO", queboton);

        // Iniciamos el Intent
        startActivity(intent);

    }
}
