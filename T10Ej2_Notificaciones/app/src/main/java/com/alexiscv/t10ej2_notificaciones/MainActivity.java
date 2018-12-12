package com.alexiscv.t10ej2_notificaciones;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    final int NOTIF_ALERTA_ID = 1999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(MainActivity.this);

        // Icono pequeño de la notificacion.
        mBuilder.setSmallIcon(android.R.drawable.stat_sys_warning);

        // Icono grande de la notificacion
        Bitmap largeIcon = BitmapFactory.decodeResource(getResources(), R.drawable.alerta);
        mBuilder.setLargeIcon(largeIcon);

        // Titulo de la notificacion
        mBuilder.setContentTitle("Mensaje de Alerta");

        // Texto de la notificacion
        mBuilder.setContentText("Ejemplo de notificación.");

        // Texto que aparece a la izq. del icono pequeño en la notificación.
        mBuilder.setContentInfo("4");

        // Texto que aparece unos segundos en la barra de estado
        // al generarse una nueva notificacion.
        mBuilder.setTicker("Alerta!");

        // El seguno paso es establecer la actividad a la cual debemos dirigir al usuario
        // si pulsa en la notificación. Lo haremos con un Ojb. PendingIntent.
        //
        // Primero creamos un Intent normal
        Intent notIntent = new Intent(MainActivity.this, MainActivity.class);

        // Despues cargamos el Intent en el PendingIntent
        PendingIntent contIntent = PendingIntent.getActivity(MainActivity.this, 0, notIntent, 0);

        // Por último asociamos el PendingIntent a la notificación.
        mBuilder.setContentIntent(contIntent);

        // Lanzamos la notificación usando el NotificationManager
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(NOTIF_ALERTA_ID, mBuilder.build());

    }
}
