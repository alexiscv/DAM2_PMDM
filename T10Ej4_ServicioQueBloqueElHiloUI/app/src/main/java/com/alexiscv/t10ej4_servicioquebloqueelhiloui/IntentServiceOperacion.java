package com.alexiscv.t10ej4_servicioquebloqueelhiloui;

import android.app.IntentService;
import android.content.Intent;
import android.os.SystemClock;

public class IntentServiceOperacion extends IntentService {

    /**
     * COnstructor
     */
    public IntentServiceOperacion() {
        super("IntentServiceOperacion");
    }

    /**
     * Método lanzado cada vez que se arranque el servicio,
     * pero en este caso se lanzará un hilo nuevo.
     * A través del Intent podremos enviar datos como Extras.
     *
     * @param intent
     */
    @Override
    protected void onHandleIntent(Intent intent) {

        // Recuperamos el Extra que ha viajado en el Intent
        double n = intent.getExtras().getDouble("numero");

        // Generamos una espera, para simular una tarea muy pesada
        SystemClock.sleep(5000);

        // Enviamos el resultado para que sea capturado por
        // el Receptor Broadcast
        Intent i = new Intent();
        i.setAction(MainActivity.ReceptorOperacion.ACTION_RESP);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.putExtra("resultado", n * n);
        sendBroadcast(i);

    }
}
