package com.alexiscv.t6ejem_intents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CondicionesActivity extends AppCompatActivity {

    private TextView saludo;
    private String nombreUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_condiciones);

        // Referenciamos
        saludo = findViewById(R.id.tvSaludo);

        // Recuperamos el nombre que ha escrito el usuario
        // Y que se nos ha pasado en el Intent
        Bundle extras = getIntent().getExtras();

        nombreUsuario = extras.getString("NOMBRE");
        // Mostramos un mensaje personalizado
        saludo.setText("Hola " + nombreUsuario + " ¿Aceptas las condiciones?");

        Toast.makeText(this, "NOMBRE = " + nombreUsuario, Toast.LENGTH_SHORT).show();

    }

    public void aceptar(View view) {
        decisionUsuario(true);

    }

    public void cancelar(View view) {
        decisionUsuario(false);
    }

    /**
     * Recoge la decisión del usuario y la notifica de vuelta al MainActivity
     * @param acepto
     */
    private void decisionUsuario(Boolean acepto) {

        // Creamos un nuevo Intent de RESPUESTA
        Intent intent = new Intent();

        // Añadimos como Extra lo que queremos enviar
        intent.putExtra("aceptoCondiciones", acepto);

        // Devolvemos por el canal de forma exitosa el mensaje del intent
        setResult(RESULT_OK, intent);

        // Terminar la actividad actual
        finish();
    }
}
