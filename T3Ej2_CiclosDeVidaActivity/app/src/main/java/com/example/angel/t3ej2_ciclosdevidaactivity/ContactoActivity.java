package com.example.angel.t3ej2_ciclosdevidaactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ContactoActivity extends AppCompatActivity {

    private static final String LOGTAG = "TEST";
    private TextView estadoActual;
    private TextView nombreForm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);

        Log.i(LOGTAG, "CONTACTO Activity CREATE.");

        // Referenciamos los widgets con su ID
        estadoActual = findViewById(R.id.estadoActual);
        nombreForm = findViewById(R.id.nombreForm);

        // Almacenamos un estado en la variable LOG
        MainActivity.log += "CONTACTO Activity CREATE.";

        // Mostramos el Log en el TextView
        estadoActual.setText(MainActivity.log);

    }

    /**
     * Se ejecuta la primera vez, despues de onCreate() y tb despues de onPuase()
     * en este caso, se ejecuta onRestart() y luego onStart()
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.i(LOGTAG, "CONTACTO Activity START.");
        // Almacenamos un estado en la variable LOG
        MainActivity.log += " CONTACTO Activity START.";
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
        Log.i(LOGTAG, "CONTACTO Activity PAUSE.");
        MainActivity.log += " CONTACTO Activity PAUSE.";

    }

    /**
     * Si la actividad deja de estar visible, se ejecuta onPAUSE y luego onSTOP
     * En onStop() haremos tareas que consumnen más CPU como almacenar datos en una DB
     */
    @Override
    protected void onStop() {
        super.onStop();
        Log.i(LOGTAG, "CONTACTO Activity STOP.");
        MainActivity.log += " CONTACTO Activity STOP.";
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
        Log.i(LOGTAG, "CONTACTO Activity RESUME.");
        MainActivity.log += " CONTACTO Activity RESUME.";
    }

    /**
     * No se suele programar, se suele usar onStart().
     * onRestart() se ejecuta cuando una activity vuelve al primer plano despues de estar onStop()
     * Es util, para realizar operaciones de restauración tras un stopped.
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(LOGTAG, "CONTACTO Activity RESTART.");
        MainActivity.log += " CONTACTO Activity RESTART.";
    }

    @Override
    protected void onSaveInstanceState( Bundle saveInstanceState ){

        Log.i(LOGTAG, "CONTACTO Activity ¡¡onSaveInstanceState!!.");
        MainActivity.log += " CONTACTO Activity ¡¡onSaveInstanceState!!.";

        // Guardamos lo que queremos guardar en el Bundle
        saveInstanceState.putInt("PUNTOS DE VIDA", 300);
        saveInstanceState.putString("NOMBRE", nombreForm.getText().toString());

        // Lo guardamos llamando al método padre y pasandole el paquete de datos
        super.onSaveInstanceState(saveInstanceState);

        // Guardar datos de un widget

    }

    @Override
    protected void onRestoreInstanceState(Bundle saveInstanceState){

        int vida = saveInstanceState.getInt("PUNTOS DE VIDA");
        String nombre = saveInstanceState.getString("NOMBRE");

        Log.i(LOGTAG, "CONTACTO Activity RECUPERADO: NOMBRE = "+nombre+" VIDA = "+vida);
        MainActivity.log += " CONTACTO Activity RECUPERADO: NOMBRE = \"+nombre+\" VIDA = \"+vida";

        // Cargamos los valores desde el Bundle
        nombreForm.setText(nombre);

        // Cargamos los valores desde el log
        estadoActual.setText(MainActivity.log);

    }
}
