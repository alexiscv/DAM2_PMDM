package com.alexiscv.ejemplo1oneactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity{

    // Declaramos las variables
    EditText texto;
    Button botonLimpiar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Código generado por android..
        super.onCreate(savedInstanceState);     // Llamada a su implementación en la clase padre
        setContentView(R.layout.activity_main); // Establecemos la interfaz gráfica
        // ..Fin código generado por android

        // Creamos unas refencias a nuestros elementos, usando sus identificadores
        texto = (EditText) findViewById(R.id.txtNombre);
        botonLimpiar = (Button) findViewById(R.id.botonLimpiar);

        // Creamos un evento OnClickListener
        botonLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                texto.setText("");
            }
        });

        // Creamos  
    }

}
