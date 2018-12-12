package com.alexiscv.t10ej1_servicios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * IMPORTANTE:
 * Para los servicios, es necesario pedir permisos en el manifest.
 * Pediremos permisos usando el nombre de clase de nuestro servicio.
 * Ejem.: <service android:name=".ServicioMusica" />
 */
public class MainActivity extends AppCompatActivity {

    private Button arrancar;
    private Button detener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrancar = findViewById(R.id.arrancar);
        detener = findViewById(R.id.detener);

        arrancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startService(new Intent(MainActivity.this, ServicioMusica.class));
            }
        });

        detener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this, ServicioMusica.class));
            }
        });

    }
}
