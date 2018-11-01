package com.alexiscv.t3ej1_enlazaractivitys;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Atributos
    private static final String LOGTAG = "LogsAndroid";
    private Button boton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Si pulsamos el último botón...
        // Lo detectamos con un Listener
        boton3 = findViewById(R.id.botonA3);
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creamos el Intent y lo abrimos
                Intent intent = new Intent(MainActivity.this, TresActivity.class);
                startActivity(intent);

            }
        });

    }

    public void abrirActivity1(View v){
        // Mensaje que se muestra en el LOG
        Log.d(LOGTAG, "Se ha pulsado el boton");

        // Creamos un Intent
        Intent intent = new Intent(MainActivity.this, UnoActivity.class);

        // Abrimos el Activity que corresponda
        startActivity(intent);

    }

    public void abrirActivity2(View v){
        // Mensaje que se muestra en el LOG
        Log.d(LOGTAG, "Se ha pulsado el boton");

        // Creamos un Intent
        Intent intent = new Intent(MainActivity.this, DosActivity.class);

        // Abrimos el Activity que corresponda
        startActivity(intent);

    }
}
