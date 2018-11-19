package com.example.angel.t4ej15_controlesdeentradaedittext;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Adapter para campo autocompletable
         */
        // Referenciamos el campo
        AutoCompleteTextView tvComunidades = findViewById(R.id.comunidadautonoma);

        // Cargamos el array de strings
        String[] comunidades = getResources().getStringArray(R.array.comunidadautonoma);

        // Creamos el adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, comunidades);
        tvComunidades.setAdapter(adapter);

        // Referenciar el email
        final TextView email = findViewById(R.id.email);

        TextView comentario = findViewById(R.id.comentario);
        comentario.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    Toast mensaje = Toast.makeText(getApplicationContext(), "ENVIADO: Tu email es "+email.getText(), Toast.LENGTH_SHORT);
                    mensaje.show();
                    return true;
                } else {
                    return false;
                }

            }
        });

    }

}
