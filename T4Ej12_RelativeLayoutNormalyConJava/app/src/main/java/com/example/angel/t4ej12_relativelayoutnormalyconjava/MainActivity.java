package com.example.angel.t4ej12_relativelayoutnormalyconjava;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int idRojo = 1;
    private int idNaranja = 2;
    private int idAmarillo = 3;
    private int idVerde = 4;
    private int idAzul = 5;
    private int idMorado = 6;
    private int idVioleta = 7;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Creamos los params genericos
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        RelativeLayout.LayoutParams params2 = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // Creamos el layout
        RelativeLayout rl = new RelativeLayout(getApplicationContext());
        rl.setLayoutParams(params);
        rl.setBackgroundResource(R.drawable.fondo2);

        /**
         * VIEWS
         */
        // ROJO
        TextView rojo = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams paramsRojo = new RelativeLayout.LayoutParams(params2);
        paramsRojo.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        paramsRojo.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        rojo.setLayoutParams(paramsRojo);
        rojo.setId(idRojo);
        rojo.setText(R.string.rojo);
        rojo.setBackgroundColor(Color.RED);
        rojo.setPadding(20,20,20,20);

        // NARANJA
        // Necesitamos un params especifico porque va centrado en el padre y arriba
        RelativeLayout.LayoutParams paramsNaranja = new RelativeLayout.LayoutParams(params2);
        paramsNaranja.addRule(RelativeLayout.CENTER_HORIZONTAL);
        paramsNaranja.addRule(RelativeLayout.ALIGN_TOP);

        TextView naranja = new TextView(this);
        naranja.setText(getResources().getString(R.string.naranja));
        naranja.setId(idNaranja);
        naranja.setBackgroundColor(Color.rgb(239, 127, 26));
        naranja.setLayoutParams(paramsNaranja);
        naranja.setPadding(20,20,20,20);

        // AMARILLO
        // Necesitamos un params especifico, para ponerlo a la derecha
        RelativeLayout.LayoutParams paramsAmarillo = new RelativeLayout.LayoutParams(params2);
        paramsAmarillo.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        TextView amarillo = new TextView(this);
        amarillo.setText(R.string.amarillo);
        amarillo.setId(idAmarillo);
        amarillo.setBackgroundColor(Color.YELLOW);
        amarillo.setLayoutParams(paramsAmarillo);
        amarillo.setGravity(Gravity.RIGHT);
        amarillo.setPadding(20,20,20,20);

        // AZUL
        RelativeLayout.LayoutParams paramsAzul = new RelativeLayout.LayoutParams(params2);
        paramsAzul.addRule(RelativeLayout.CENTER_IN_PARENT);
        paramsAzul.setMargins(10,0,10,0);
        TextView azul = new TextView(this);
        azul.setText(R.string.azul);
        azul.setId(idAzul);
        azul.setBackgroundColor(Color.BLUE);
        azul.setLayoutParams(paramsAzul);
        azul.setGravity(Gravity.CENTER);
        azul.setPadding(20,20,20,20);

        // VERDE
        RelativeLayout.LayoutParams paramsVerde = new RelativeLayout.LayoutParams(params2);
        paramsVerde.addRule(RelativeLayout.LEFT_OF, azul.getId());
        paramsVerde.addRule(RelativeLayout.CENTER_VERTICAL);
        TextView verde = new TextView(this);
        verde.setText(R.string.verde);
        verde.setId(idVerde);
        verde.setBackgroundColor(Color.GREEN);
        verde.setLayoutParams(paramsVerde);
        verde.setGravity(Gravity.CENTER);
        verde.setPadding(20,20,20,20);

        // MORADO
        // Creamos su Params
        RelativeLayout.LayoutParams paramsMorado = new RelativeLayout.LayoutParams(params2);

        // Sus reglas
        paramsMorado.addRule(RelativeLayout.RIGHT_OF, azul.getId());
        paramsMorado.addRule(RelativeLayout.CENTER_VERTICAL);

        // El View...
        TextView morado = new TextView(this);
        morado.setText(R.string.morado);
        morado.setId(idMorado);
        morado.setBackgroundColor(Color.rgb(38, 71, 150));
        morado.setLayoutParams(paramsMorado);
        morado.setGravity(Gravity.CENTER);
        morado.setPadding(20,20,20,20);

        // VIOLETA
        // Creamos su Params
        RelativeLayout.LayoutParams paramsVioleta = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // Añadimos las reglas necesarias al params
        paramsVioleta.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);

        // Construimos el TextView y le aplicamos el params
        TextView violeta = new TextView(this);
        violeta.setText(R.string.violeta);
        violeta.setId(idVioleta);
        violeta.setBackgroundColor(Color.rgb(120, 40, 140));
        violeta.setTextColor(Color.WHITE);
        violeta.setPadding(20,20,20,20);
        violeta.setLayoutParams(paramsVioleta);
        violeta.setGravity(Gravity.CENTER); // Centrar texto

        // Añadimos los elementos al Layout
        rl.addView(rojo);
        rl.addView(naranja);
        rl.addView(amarillo);
        rl.addView(verde);
        rl.addView(azul);
        rl.addView(morado);
        rl.addView(violeta);

        // Establecemos el Layout
        setContentView(rl);
        //setContentView(R.layout.activity_main);

    }
}
