package com.alexiscv.t4ejem_textwatcher;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText password;
    TextView nivelFortaleza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creamos las referencias
        password = findViewById(R.id.password);
        nivelFortaleza = findViewById(R.id.nivelForzaleza);

        // Añadimos el TextWatcher al EditText
        password.addTextChangedListener(miTextWatcher);
    }

    /**
     * Implementación del EditTextWatcher
     */
    private final TextWatcher miTextWatcher = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {

            if(s.length() == 0){
                nivelFortaleza.setText("No hay contraseña.");
            }

            else if(s.length() < 6){
                nivelFortaleza.setText("BAJO");
            }

            else if(s.length() < 10){
                nivelFortaleza.setText("MEDIO");
            }

            else if(s.length() < 15){
                nivelFortaleza.setText("ALTO");
            }

            else{
                nivelFortaleza.setText("MUY ALTO");
            }

            if (s.length() == 20){
                nivelFortaleza.setText("Máxima longitud alcanzada.");
            }

        }
    };
}
