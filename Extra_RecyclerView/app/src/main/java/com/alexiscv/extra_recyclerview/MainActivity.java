package com.alexiscv.extra_recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * IMPORTANTE
 * antes de nada, aadir esto al Grandle APP
 * implementation 'com.android.support:recyclerview-v7:28.0.0'
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView rvListaTitulares;
    private ArrayList<Titular> datos;
    private AdaptadorTitulares adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //inicialización de la lista de datos de ejemplo
        datos = new ArrayList<Titular>();
        for (int i = 0; i < 50; i++)
            datos.add(new Titular("Título " + i, "Subtítulo item " + i));

        // Obtenemos las referencias
        rvListaTitulares = findViewById(R.id.listaEquipos);

        // Creamos el adaptador
        adaptador = new AdaptadorTitulares(datos);

        // Inicialización RecyclerVIew
        rvListaTitulares.setHasFixedSize(true); // Opcional, probarlo, es algo del tamaño
        rvListaTitulares.setAdapter(adaptador);

        // Indica al RecyclerView como distribuir los items/elementos del listado
        // 3 opciones a continuaciób...
        //rvListaTitulares.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        //rvListaTitulares.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvListaTitulares.setLayoutManager(new GridLayoutManager(this,3));

        // Extras, ItemDecoration & Animator
        rvListaTitulares.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvListaTitulares.setItemAnimator(new DefaultItemAnimator());

        // Listener
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("DemoRecView", "Pulsado el elemento " + rvListaTitulares.getChildAdapterPosition(v));
                Toast.makeText(MainActivity.this, "Pulsado el elemento " + rvListaTitulares.getChildAdapterPosition(v), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void edit(View view) {
        Titular aux = datos.get(1);
        datos.set(1, datos.get(2));
        datos.set(2, aux);

        adaptador.notifyItemMoved(1, 2);

    }

    public void del(View view) {
        datos.remove(1);
        adaptador.notifyItemRemoved(1);
    }

    public void add(View view) {
        datos.add(1, new Titular("Nuevo titular", "Subtitulo nuevo titular"));
        adaptador.notifyItemInserted(1);
    }
}
