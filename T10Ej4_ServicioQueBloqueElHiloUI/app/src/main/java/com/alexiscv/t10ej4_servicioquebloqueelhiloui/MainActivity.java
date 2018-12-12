package com.alexiscv.t10ej4_servicioquebloqueelhiloui;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText entrada;
    public static TextView salida; // public static nos permite acceder desde otras clases

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrada = (EditText) findViewById(R.id.entrada);
        salida = (TextView) findViewById(R.id.salida);
    }

    public void calcularOperacion(View view) {
        double n = Double.parseDouble(entrada.getText().toString());

        // Añadimos texto al TextView salida
        salida.append(n + "^2 = ");

        // Creamos una Intención al ServicioOperacion
        //Intent i = new Intent(this, ServicioOperacion.class);

        // Creamos una Intención al IntentServiceOperacion
        Intent i = new Intent(this, IntentServiceOperacion.class);

        // Le añadimos un Extra a la Intención
        // en concreto, el número que hay en "entrada"
        i.putExtra("numero", n);

        // Comenzamos el servicio, adjuntando la Intención
        startService(i);

        // Necesario para que funcione el Receptor Broadcast
        // Lo que hacemos es crear un filtro
        // indicar su categoria
        // y registrarlo en el sistema.
        // Esto se puede hacer en el Manifest como se hizo en el otro ejercicio
        IntentFilter filtro = new IntentFilter(ReceptorOperacion.ACTION_RESP);
        filtro.addCategory(Intent.CATEGORY_DEFAULT);
        registerReceiver(new ReceptorOperacion(), filtro);
    }

    /**
     * Receptor Broadcast
     */
    public class ReceptorOperacion extends BroadcastReceiver {

        // Nombre de nuestra ACCION
        public static final String ACTION_RESP = "com.alexiscv.intent.action.RESPUESTA_OPERACION";

        @Override
        public void onReceive(Context context, Intent intent) {

            // Recogemos el valor que nos llega como Extra dentro
            // del Intent
            Double resultado = intent.getDoubleExtra("resultado", 0.0);

            // Escribimos el resultado
            salida.append(" " + resultado);

        }
    }
}
