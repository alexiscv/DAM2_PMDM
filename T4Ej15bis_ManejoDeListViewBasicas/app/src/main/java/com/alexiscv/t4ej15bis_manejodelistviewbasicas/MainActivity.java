package com.alexiscv.t4ej15bis_manejodelistviewbasicas;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Atributos
    private Spinner desplegable;
    private String[] listadoOpciones;
    private CheckBox checkResponder;
    private EditText nombre;
    private EditText email;
    private EditText mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creamos las referencias
        desplegable = findViewById(R.id.desplegable);
        listadoOpciones = getResources().getStringArray(R.array.opciones);
        checkResponder = findViewById(R.id.checkRespuesta);
        nombre = findViewById(R.id.nombre);
        email = findViewById(R.id.email);
        mensaje = findViewById(R.id.mensaje);

        // Creamos un ArrayAdapter usando el StringArray
        // Le asignaremos también una vista, de las que facilita android
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.opciones, android.R.layout.simple_spinner_item);

        // Indicamos el layout que queremos usar para la lista de opciones
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Asociamos el Prompt al Spinner
        desplegable.setPromptId(R.string.promptOpciones);

        // Aplicar el adaptador al Spinner
        // Con esto conseguimos que tome el Layout y las opciones que debe mostrar
        desplegable.setAdapter(adapter);

        /**
         * Controlar la selección del Spinner
         */
        desplegable.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Code..
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Code..

            }
        });

        /**
         * Cambiar color del CheckBox
         */
        checkResponder.setOnClickListener(new View.OnClickListener() {

            // Variable donde guardaremos el color original
            int colorOriginal;

            @Override
            public void onClick(View v) {
                // Comprobamos si está marcado o desmarcado
                if (checkResponder.isChecked()) {
                    // Guardo el color Original
                    colorOriginal = checkResponder.getCurrentTextColor();

                    // Lo cambio
                    checkResponder.setTextColor(Color.BLUE);
                } else {
                    checkResponder.setTextColor(colorOriginal);

                }
            }
        });

    }

    /**
     * Enviamos el Feedback
     *
     * @param view
     */
    public void enviarFeedback(View view) {

        if (!nombre.getText().toString().equals("") && !email.getText().toString().equals("") && !mensaje.getText().toString().equals("")) {
            // Construimos el mensaje y llamamos al método encargado de enviar
            String mensajeCompleto = nombre.getText().toString() + " (" + email.getText().toString() + ") ha enviado un mensaje: \n";
            mensajeCompleto += "TIPO FEEDBACK: " + desplegable.getSelectedItem().toString() + " \n";
            mensajeCompleto += "FEEDBACK: " + mensaje.getText().toString() + " \n";

            if (checkResponder.isChecked())
                mensajeCompleto += "Este mensaje requiere respuesta.";
            else
                mensajeCompleto += "Este mensaje NO requiere respuesta.";

            enviarMensajeFeedback("MENAJE DE FEEDBACK", mensajeCompleto);
        } else {
            Toast aviso = Toast.makeText(this, "RELLENA TODO EL FORMULARIO", Toast.LENGTH_SHORT);
            aviso.show();
        }

    }

    /**
     * Enviar el Feedback
     */
    public void enviarMensajeFeedback(String asunto, String mensaje) {

        Intent intentMensaje = new Intent(Intent.ACTION_SEND);
        intentMensaje.setType("plain/text");

        // Receptores
        String listaEmails[] = {"hogo.jp@gmail.com"};
        intentMensaje.putExtra(Intent.EXTRA_EMAIL, listaEmails);

        // Asunto del mensaje
        intentMensaje.putExtra(Intent.EXTRA_SUBJECT, asunto);

        // Mensaje
        intentMensaje.putExtra(Intent.EXTRA_TEXT, mensaje);

        // Enviar
        startActivity(intentMensaje);

    }
}
