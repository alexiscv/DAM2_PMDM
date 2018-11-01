package com.alexiscv.t3ej3_activityestadosyciclosdevida;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AndroidException;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Atributos
    private int contador1;
    private int contador2;
    private int contador3;
    private String txtContador;
    private TextView contador;
    private TextView registro;
    private TextView texto;
    private CheckBox check1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creamos las referencias para poder manipular los elementos XML
        contador = findViewById(R.id.contador);
        registro = findViewById(R.id.registro);
        texto = findViewById(R.id.texto);
        check1 = findViewById(R.id.check1);

        // Registro ConsoleLog
        Log.d("TEST", "ACTIVITY CREATE");

        // Otra manera de controlar el checkbox
        check1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("TEST", "CHECK#1 is "+isChecked);
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Registro ConsoleLog
        Log.d("TEST", "ACTIVITY START");
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Registro ConsoleLog
        Log.d("TEST", "ACTIVITY RESUME");

        // Forzar al campo texto a que aparezca en blanco
        texto.setText("");
    }

    @Override
    protected void onPause() {
        super.onPause();

        // Registro ConsoleLog
        Log.d("TEST", "ACTIVITY PAUSE");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        // Registro ConsoleLog
        Log.d("TEST", "ACTIVITY RESTART");
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Registro ConsoleLog
        Log.d("TEST", "ACTIVITY STOP");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Registro ConsoleLog
        Log.d("TEST", "ACTIVITY DESTROY");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Registro ConsoleLog
        Log.e("TEST", "ACTIVITY SAVE" + txtContador);

        // Almacenamos la información a persistir
        outState.putString("txtContador", txtContador);
        outState.putString("texto", texto.getText().toString());
        outState.putInt("contador1", contador1);
        outState.putInt("contador2", contador2);
        outState.putInt("contador3", contador3);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        // Registro ConsoleLog
        Log.e("TEST", "ACTIVITY RESTORE" + txtContador);

        // Restauramos los textos en pantalla
        contador.setText(savedInstanceState.getString("txtContador"));
        txtContador = savedInstanceState.getString("txtContador");
        registro.setText(savedInstanceState.getString("texto"));
        contador1 = savedInstanceState.getInt("contador1");
        contador2 = savedInstanceState.getInt("contador2");
        contador3 = savedInstanceState.getInt("contador3");
    }

    /**
     * Método para contabilizar los clics en los checkbox
     */
    public void pulsarCheck(View view) {

        if (view.getId() == R.id.check1) {
            Log.d("TEST", "Pulsado CheckBox #1. Pulsado " + contador1 + " veces");
            contador1++;
        }

        if (view.getId() == R.id.check2) {
            Log.d("TEST", "Pulsado CheckBox #2 Pulsado " + contador2 + " veces");
            contador2++;
        }

        if (view.getId() == R.id.check3) {
            Log.d("TEST", "Pulsado CheckBox #3 Pulsado " + contador3 + " veces");
            contador3++;
        }

        // Actualizamos el mensaje de conteo
        txtContador = "Checkbox#1 pulsado " + contador1 + " veces | Checkbox#2 pulsado " + contador2 + " veces | Checkbox#3 pulsado " + contador3 + " veces";
        contador.setText(txtContador);

    }

}
