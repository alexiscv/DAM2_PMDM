package com.alexiscv.asteroides;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button bAcercaDe;
    private Button bCerrar;
    private Button bPuntuaciones;
    private MediaPlayer mp;
    // Al ser public podremos acceder sin necesidad de métodos y por ser static
    // el valor será compartido por todos los obetos de la clase.
    public static AlmacenPuntuaciones almacen = new AlmacenPuntuacionesArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "onCreate", Toast.LENGTH_SHORT).show();

        // Musica
        mp = MediaPlayer.create(this, R.raw.menu);

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

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();

        // Iniciamos la musica
        mp.start();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();

        // Iniciamos la musica
        mp.start();

    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        mp.pause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void
    onRestart() {
        super.onRestart();
        Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void
    onDestroy() {
        Toast.
                makeText
                        (
                                this
                                ,
                                "onDestroy"
                                , Toast.
                                        LENGTH_SHORT
                        ).show();
        super
                .onDestroy();
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
