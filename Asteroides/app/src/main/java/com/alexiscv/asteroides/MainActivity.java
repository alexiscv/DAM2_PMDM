package com.alexiscv.asteroides;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bAcercaDe;
    private Button bCerrar;
    private Button bPuntuaciones;
    // Al ser public podremos acceder sin necesidad de métodos y por ser static
    // el valor será compartido por todos los obetos de la clase.
    public static AlmacenPuntuaciones almacen = new AlmacenPuntuacionesArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Listener para detectar click en el botón Acerca de...
        bAcercaDe = findViewById(R.id.acercade);
        bAcercaDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarcercaDe(null);
            }
        });

        // Listener para cerrar App
        bCerrar = findViewById(R.id.salir);
        bCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cerrarApp(null);
            }
        });

        // Listener para lanzar las puntuaciones
        bPuntuaciones = findViewById(R.id.puntuaciones);
        bPuntuaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarPuntuaciones(null);

            }
        });


    }

    /**
     * Permite iniciar el Activity Puntuaciones
     *
     * @param o
     */
    private void lanzarPuntuaciones(Object o) {
        Intent i = new Intent(this, Puntuaciones.class);
        startActivity(i);
    }

    /**
     * Permite lanzar AcercaDe
     *
     * @param view
     */
    public void lanzarcercaDe(View view) {
        Intent i = new Intent(this, AcercaDe.class);
        startActivity(i);

    }

    /**
     * Permite cerrar al APP.
     *
     * @param view
     */
    public void cerrarApp(View view) {
        finish();
    }
}
