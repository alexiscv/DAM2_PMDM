package com.alexiscv.t4ejem_imagenviewytextview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Establecer una imagen mediante Java
        ImageView img = findViewById(R.id.imagen);
        img.setImageResource(R.drawable.mar);

        // Params
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        // Crear una imagen completa
        ImageView imagen = new ImageView(this);
        imagen.setImageResource(android.R.drawable.star_big_on);
        imagen.setBackgroundColor(Color.RED);
        imagen.setLayoutParams(params);

        // Añadir la imagen al Layout
        LinearLayout ll = findViewById(R.id.layoutPrincipal);
        ll.addView(imagen);

    }
}
