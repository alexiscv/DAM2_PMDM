package com.alexiscv.ejemplo2dosactividades;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SaludoActivity extends AppCompatActivity {

    // Atributos
    TextView saludo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo);

        // Creamos la referencia
        saludo = findViewById(R.id.saludo);

        // Este activity se abre desde un Intent,
        // Vamos a recuperar la información de ese intent.
        Bundle bundle = this.getIntent().getExtras();

        // Creamos el saludo con la información recuperada
        saludo.setText("¡HOLA "+bundle.getString("NOMBRE")+"!");

    }
}
