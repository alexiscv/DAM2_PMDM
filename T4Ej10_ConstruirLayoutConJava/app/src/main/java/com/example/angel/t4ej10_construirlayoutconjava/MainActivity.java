package com.example.angel.t4ej10_construirlayoutconjava;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // Creamos el params para poder pasarselo a los elementos que lo necesiten
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        // Creamos otro params para los elementos que MATCH_PARENT
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, (float)1.0);

        // Creamos los TextView
        TextView rojo = new TextView(this);
        rojo.setText(R.string.rojo);
        rojo.setBackgroundColor(Color.RED);
        rojo.setLayoutParams(params2);
        rojo.setGravity(Gravity.CENTER);

        // Creamos el resto de TextViews
        TextView naranja = new TextView(this);
        naranja.setText(R.string.naranja);
        naranja.setBackgroundColor(Color.rgb(239, 127, 26));
        naranja.setLayoutParams(params2);
        naranja.setGravity(Gravity.CENTER);

        TextView amarillo = new TextView(this);
        amarillo.setText(R.string.amarillo);
        amarillo.setBackgroundColor(Color.YELLOW);
        amarillo.setLayoutParams(params2);
        amarillo.setGravity(Gravity.CENTER);

        TextView verde = new TextView(this);
        verde.setText(R.string.verde);
        verde.setBackgroundColor(Color.GREEN);
        verde.setLayoutParams(params2);
        verde.setGravity(Gravity.CENTER);

        TextView azul = new TextView(this);
        azul.setText(R.string.azul);
        azul.setBackgroundColor(Color.BLUE);
        azul.setLayoutParams(params2);
        azul.setGravity(Gravity.CENTER);

        TextView morado = new TextView(this);
        morado.setText(R.string.morado);
        morado.setBackgroundColor(Color.rgb(38, 71, 150));
        morado.setLayoutParams(params2);
        morado.setGravity(Gravity.CENTER);

        TextView violeta = new TextView(this);
        violeta.setText(R.string.violeta);
        violeta.setBackgroundColor(Color.rgb(120, 40, 140));
        violeta.setLayoutParams(params2);
        violeta.setGravity(Gravity.CENTER);

        // Creamos el LinearLayout
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(params);

        // Añadimos los Views al Layout
        ll.addView(rojo);
        ll.addView(naranja);
        ll.addView(amarillo);
        ll.addView(verde);
        ll.addView(azul);
        ll.addView(morado);
        ll.addView(violeta);

        // Asignamos este Layouot a esta Activity
        setContentView(ll);


    }
}
