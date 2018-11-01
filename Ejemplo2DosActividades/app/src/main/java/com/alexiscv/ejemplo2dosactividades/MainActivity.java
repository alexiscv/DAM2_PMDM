package com.alexiscv.ejemplo2dosactividades;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Atributos
    private EditText nombre;
    private Button botonAceptar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creamos las referencias
        nombre = findViewById(R.id.nombre);
        botonAceptar = findViewById(R.id.botonAceptar);

        // OnClic Aceptar
        botonAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creamos un Intent
                Intent intent = new Intent(MainActivity.this, SaludoActivity.class);

                // Creamos la información a pasar entre activitys
                Bundle bundle = new Bundle();
                bundle.putString("NOMBRE", nombre.getText().toString());

                // Añadimos la información al intent
                intent.putExtras(bundle);

                // Iniciamos la nueva actividad
                startActivity(intent);

            }
        });
    }
}
