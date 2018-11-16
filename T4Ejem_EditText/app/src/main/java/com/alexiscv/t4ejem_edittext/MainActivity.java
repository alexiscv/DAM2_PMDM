package com.alexiscv.t4ejem_edittext;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Si hemos establecido una acción de teclado usando imaOptions, por ejemplo
         * actionSend, podemos responder a estas cciones
         * con un Listener
         */
        EditText editText = (EditText) findViewById(R.id.imeSend);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;

                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendMessage();
                    handled = true;
                }

                return handled;
            }
        });

        /**
         * Para poder tener un campo AutoCompleteTextView
         * necesitamos configurar un Adaptador
         * que toma las sugerencias y las carga en el AutoCompleteTextView
         */
        // Vinculamos el AutoCompleteTextView
        AutoCompleteTextView textView = (AutoCompleteTextView) findViewById(R.id.autocompletar_paises);

        // Tomamos el array de strings
        String[] paises = getResources().getStringArray(R.array.paises_array);

        // Creamos el adaptador y lo asignamos al AutoCompleteTextView
        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, paises);
        textView.setAdapter(adaptador);

        /**
         * Escuchadores
         * También se puede implementar en la clase Main y hacer un escuchador generico
         * mirar apuntes: 11-5 Interfaz de usuario, COntroles de entrada EditText, página 26
         */
        EditText campoEscuchado = (EditText) findViewById(R.id.campoEscuchado);
        campoEscuchado.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.e("TEST", "beforeTextChanged [s: " + s + " | count: " + count + " | after: " + after + "]");

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.e("TEST", "onTextChanged [s: " + s + " | start: " + start + " | before: " + before + " | count: " + count + "]");

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.e("TEST", "afterTextChanged [s: " + s + "]");

            }
        });

        /**
         * Vamos añadir un texto con HTML a un EditText, no es recomendable
         */
        EditText campoHTML = findViewById(R.id.campoHTML);
        campoHTML.setText(Html.fromHtml("<h2>Título</h2><br>Este texto es una <b><u>prueba</u></b> de formato de texto"));

        /**
         * Ahora usaremos SPANS para dar formato a un texto
         * mirar apuntes: 11-5 Interfaz de usuario, COntroles de entrada EditText, página 33
         */
        // Defino el texto a formatear
        SpannableStringBuilder texto_a_formatear = new SpannableStringBuilder("Título con formato Este texto es una prueba de formato de texto");

        // Creamos los span (estilos) que vamos a usar.
        StyleSpan boldSpan1 = new StyleSpan(Typeface.BOLD);                // Negrita
        StyleSpan boldSpan2 = new StyleSpan(Typeface.BOLD);                // Negrita
        RelativeSizeSpan sizeSpan = new RelativeSizeSpan(1.2f); // Tamaño de fuente
        UnderlineSpan subrayadoSpan = new UnderlineSpan();                 // Subrayado
        StyleSpan italicSpan = new StyleSpan(Typeface.ITALIC);             // Italica

        // Aplico los estilos, indicando desde que posición se aplica, hasta que posición se deja de aplicar
        texto_a_formatear.setSpan(boldSpan1, 0, 18, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        texto_a_formatear.setSpan(sizeSpan, 0, 18, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        texto_a_formatear.setSpan(boldSpan2, 39, 45, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        texto_a_formatear.setSpan(subrayadoSpan, 39, 45, Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        texto_a_formatear.setSpan(italicSpan, 49, 56, Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        // Asignamos el texto al campo que queremos
        EditText campoFormateado = findViewById(R.id.campoSPAN);
        campoFormateado.setText(texto_a_formatear, TextView.BufferType.SPANNABLE);

        /**
         * Resaltar un texto seleccionado
         */
        Button bResaltar = findViewById(R.id.botonResaltar);
        bResaltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Referencio el TextEdit
                EditText cuadroTexto = findViewById(R.id.resaltaTexto);

                // Cojo el texto que contenga
                Spannable texto = cuadroTexto.getText();

                // Obtengo la posición inicial y final del texto seleccionado
                int ini = cuadroTexto.getSelectionStart();
                int fin = cuadroTexto.getSelectionEnd();

                // Aplico el formato, en este caso negrita
                texto.setSpan(new StyleSpan(Typeface.BOLD), ini, fin, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

            }
        });

    }

    /**
     * Método de sin desarrollar para simular el ejemplo
     */
    private void sendMessage() {
        Log.i("TEST", "ENVIAMOS EL MENSAJE...");
    }

}
