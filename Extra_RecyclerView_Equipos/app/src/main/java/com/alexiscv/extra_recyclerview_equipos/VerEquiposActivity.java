package com.alexiscv.extra_recyclerview_equipos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class VerEquiposActivity extends AppCompatActivity {

    private TextView nombre, puntos;
    private ImageView imagen;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_equipos);

        // Referencias
        nombre = findViewById(R.id.tvNombreEquipoDetalle);
        puntos = findViewById(R.id.tvPuntosEquipoDetalle);
        imagen = findViewById(R.id.ivImagenEquipoDetalle);


        // Comprobamos que nos llegase el extra posición
        if (getIntent().hasExtra("posicion")) {
            pos = getIntent().getIntExtra("posicion", 0);
        }

        if (pos != -1) {
            // Recuperamos la información del equipo
            Equipo equipo = MainActivity.arrayDatosEquipos.get(pos);

            // Cargamos los datos en los elementos XML
            nombre.setText(equipo.getNombre());
            puntos.setText(String.valueOf(equipo.getPuntos()));
            imagen.setImageDrawable(equipo.getImagen());
        }
    }
}
