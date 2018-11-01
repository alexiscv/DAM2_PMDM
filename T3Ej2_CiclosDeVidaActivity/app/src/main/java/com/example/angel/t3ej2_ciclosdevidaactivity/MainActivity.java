package com.example.angel.t3ej2_ciclosdevidaactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String LOGTAG = "TEST";
    private Button botonContacto;
    public static String log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(LOGTAG, "MAIN Activity CREATE.");

        // Enlazamos los botones del XML con nuestro JAVA
        botonContacto = findViewById(R.id.botonContacto);

        // Listener Boton Contacto
        botonContacto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creamos el Intent y abrimos el Activity
                Intent intent = new Intent(MainActivity.this, ContactoActivity.class);
                startActivity(intent);

            }
        });
    }

    /**
     * Se ejecuta la primera vez, despues de onCreate() y tb despues de onPuase()
     * en este caso, se ejecuta onRestart() y luego onStart()
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOGTAG, "MAIN Activity START.");
    }

    /**
     * Cuando lanzamos otra actividad, la actual se pausa.
     * Por una llamada, una alarma del despertador, abrimos otra app...
     * Podemos comprobar si la actividad es visible y si no lo es
     * parar animaciones y acciones que gasten recursos.
     * También podemos guardar cosas puntuales, como borradores de email.
     * Liberar camara, sensores, broadcast receivers, recursos...
     */
    @Override
    protected void onPause() {
        super.onPause();
        Log.i(LOGTAG, "MAIN Activity PAUSE.");

    }

    /**
     * Si la actividad deja de estar visible, se ejecuta onPAUSE y luego onSTOP
     * En onStop() haremos tareas que consumnen más CPU como almacenar datos en una DB
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOGTAG, "MAIN Activity STOP.");
    }

    /**
     * Cuando una actividad se reauna, se ejecuta onResume
     * También es llamado al crear la activity por primera vez.
     * En este paso, iniciaremos componentes que se puedan haber liberado en onPause()
     * como camara, sensores, broadcast receivers, ...
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.i(LOGTAG, "MAIN Activity RESUME.");
    }

    /**
     * No se suele programar, se suele usar onStart().
     * onRestart() se ejecuta cuando una activity vuelve al primer plano despues de estar onStop()
     * Es util, para realizar operaciones de restauración tras un stopped.
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOGTAG, "MAIN Activity RESTART.");
    }

    /**
     * Abrir LogIn
     */
    public void abrirLogIn(View view){

        Log.i(LOGTAG, "---> Has pulsado el botón: "+view.getId());
        startActivity(new Intent(MainActivity.this, LoginActivity.class));

    }

}
