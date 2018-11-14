package com.example.angel.t4ejem_layoutconjava;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // Como el layout padre de la etiqueta y del botçon va a ser un
        // LinearLayout y quiero que esta etiqueta se ajuste al tamaño de su contenido
        // en el ancho y alto (WRAP_CONTENT); y lo mismo para la imagen, entonces,
        // puedo crear un solo LinearLayout.LayoutParam y usarlo para ambas views.
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        // Creando la etiqueta
        TextView label = new TextView(this);
        label.setText(R.string.miTexto_label);
        label.setTextSize(20);
        label.setBackgroundColor(Color.BLUE);
        label.setGravity(Gravity.CENTER_HORIZONTAL);

        // Asigno el tamaño a la etiqueta
        label.setLayoutParams(params);

        // Creando el ImageView
        ImageView pic = new ImageView(this);
        pic.setImageResource(android.R.drawable.star_big_on);
        pic.setBackgroundColor(Color.RED);
        pic.setLayoutParams(params);

        // Para mantener las proporciones
        pic.setAdjustViewBounds(true);
        pic.setScaleType(ImageView.ScaleType.FIT_XY);
        pic.setMaxHeight(250);
        pic.setMaxWidth(250);

        // Creamos el LinearLayout
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
        ));
        ll.setGravity(Gravity.CENTER);

        // Añadimos la etiqueta y la imagen al layout
        ll.addView(label);
        ll.addView(pic);

        // Asignamos este layout a esta Activity
        setContentView(ll);
    }
}
