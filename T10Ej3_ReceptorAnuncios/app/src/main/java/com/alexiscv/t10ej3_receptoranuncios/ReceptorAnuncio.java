package com.alexiscv.t10ej3_receptoranuncios;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

public class ReceptorAnuncio extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        /**
         * NOTIFICACIÓN
         */
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);

        // Icono pequeño de la notificacion.
        mBuilder.setSmallIcon(android.R.drawable.stat_sys_warning);

        // Icono grande de la notificacion
        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.drawable.alerta);
        mBuilder.setLargeIcon(largeIcon);

        // Titulo de la notificacion
        mBuilder.setContentTitle("Cargador conectado");

        // Texto de la notificacion
        mBuilder.setContentText("El cable usb de carga ha sido conectado. Cargando...");

        // Texto que aparece a la izq. del icono pequeño en la notificación.
        mBuilder.setContentInfo("4");

        // Texto que aparece unos segundos en la barra de estado
        // al generarse una nueva notificacion.
        mBuilder.setTicker("CARGANDO!");

        // El seguno paso es establecer la actividad a la cual debemos dirigir al usuario
        // si pulsa en la notificación. Lo haremos con un Ojb. PendingIntent.
        //
        // Primero creamos un Intent normal
        Intent notIntent = new Intent(context, MainActivity.class);

        // Despues cargamos el Intent en el PendingIntent
        PendingIntent contIntent = PendingIntent.getActivity(context, 0, notIntent, 0);

        // Por último asociamos el PendingIntent a la notificación.
        mBuilder.setContentIntent(contIntent);

        // Lanzamos la notificación usando el NotificationManager
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(9999, mBuilder.build());


    }
}
