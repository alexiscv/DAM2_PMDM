package com.alexiscv.t10ej4_servicioquebloqueelhiloui;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class ServicioOperacion extends Service {

    @Override
    public int onStartCommand(Intent i, int flags, int idArranque) {

        // Recuperamos el Extra que ha viajado en el Intent
        double n = i.getExtras().getDouble("numero");

        // Generamos una espera, para simular una tarea muy pesada
        SystemClock.sleep(5000);

        // Escribimos más contenido en el TextView salida
        MainActivity.salida.append(n * n + "\n");

        // Especificamos que debe suceder en caso de
        // que el sistema mate este servicio
        // En este caso, indicamos que si es destruido
        // no es necesario volver a crearlo
        return START_NOT_STICKY;

    }


    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

}
