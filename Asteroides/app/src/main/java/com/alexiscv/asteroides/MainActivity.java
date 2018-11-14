package com.alexiscv.asteroides;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button bAcercaDe;
    private Button bCerrar;

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



    }

    // Cuando pulsemos el botón acerca de...
    public void lanzarcercaDe(View view) {
        Intent i = new Intent(this, AcercaDe.class);
        startActivity(i);

    }

    /**
     * Al pulsar Salir
     */
    public void cerrarApp(View view){
        finish();
    }
}
