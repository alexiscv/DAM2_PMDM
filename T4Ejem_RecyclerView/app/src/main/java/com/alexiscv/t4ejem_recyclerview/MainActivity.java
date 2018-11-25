package com.alexiscv.t4ejem_recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * MIRAR EN GRANDLE, HAY QUE AÑADIR UNA DEPENDENCIA!!
 */
public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<Usuario> listaUsuarios;
    private AdaptadorUsuarios adaptadorUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Cargamos los datos de ejemplo
        initDatosEjemplo();

        // Inicializar RecyclerView
        recyclerView = findViewById(R.id.rvListaUsuarios);
        adaptadorUsuarios = new AdaptadorUsuarios(listaUsuarios);
        recyclerView.setAdapter(adaptadorUsuarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        // Listener para nuestro RecyclerView
        adaptadorUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Has pulsado en la posición " + recyclerView.getChildAdapterPosition(view), Toast.LENGTH_SHORT).show();

            }

        });
    }

    /**
     * Permite cargar unos datos de Ejemplo
     */
    private void initDatosEjemplo() {
        listaUsuarios = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            listaUsuarios.add(new Usuario("Nombre " + i, "Apellido " + i, "Dirección postal " + i));
        }
    }
}
