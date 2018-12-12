package com.alexiscv.t5ejem_fragmentsestaticos;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListadoFilosofosFragment extends Fragment {

    private RecyclerView rvlistadoFilosofos;
    private ArrayList<Filosofo> arrayFilosofos;
    private AdaptadorFilosofos adaptadorFilosofos;
    private Filosofo filosofo;

    public ListadoFilosofosFragment() {
        // Required empty public constructor
    }


    /**
     * El Fragment va a cargar su layout, el cual debemos especificar
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos el Layout para este fragmento
        View viewListadoFilosofos = inflater.inflate(R.layout.fragment_listado_filosofos, container, false);

        // Devolvemos el Layout inflado
        return viewListadoFilosofos;
    }

    /**
     * El Activity que contiene el Fragment ha terminado su creación
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Obtengo los datos para la listview de los recursos
        arrayFilosofos = getDatosFromResources();

        // Buscamos y Referenciamos los elementos XML dentro del View
        rvlistadoFilosofos = getView().findViewById(R.id.rvFilosofos);   // Ref. RecyclerView

        // El adaptador. Se encargará de crear los views de cada ITEM del ReciclerView con sus datos facilitados
        adaptadorFilosofos = new AdaptadorFilosofos(arrayFilosofos);

        // Asigno el adaptador al RecyclerView
        rvlistadoFilosofos.setAdapter(adaptadorFilosofos);

        // LayoutManager. Maneja todas las vistas dentro del RecyvlerView
        // Le pasamos contexto y disposición de los elementos, en este caso VERTICAL
        rvlistadoFilosofos.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.VERTICAL, false));

        // Listener para detectar clicks en los elementos
        adaptadorFilosofos.setItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Recogemos la posición del item seleccionado
                int posicion = rvlistadoFilosofos.getChildAdapterPosition(v);

                // Mensaje de prueba
                Toast.makeText(getActivity(), "CLICK: Filosofo = " + arrayFilosofos.get(posicion).getNombre(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Méteodo que carga los datos de los filósofos
     */
    private ArrayList<Filosofo> getDatosFromResources() {

        // Creo un ArrayList donde almacenar los filosofos y sus citas
        ArrayList<Filosofo> datos = new ArrayList<Filosofo>();

        // Cargamos el array de nombes de filosofos desde el XML
        String[] nombresFilosofosArray = getResources().getStringArray(R.array.NombresFilosofos);

        // Recorremos el listado de Filosofos
        for (String nombre : nombresFilosofosArray) {
            // Creamos cada filósofo
            switch (nombre) {
                case "Socrates":
                    filosofo = new Filosofo(R.drawable.socrates, nombre, Arrays.asList(getResources().getStringArray(R.array.CitasSocrates)));
                    break;

                case "Confucio":
                    filosofo = new Filosofo(R.drawable.confucio, nombre, Arrays.asList(getResources().getStringArray(R.array.CitasConfuncio)));
                    break;

                case "Descartes":
                    filosofo = new Filosofo(R.drawable.descartes, nombre, Arrays.asList(getResources().getStringArray(R.array.CitasDescartes)));
                    break;

                case "Platon":
                    filosofo = new Filosofo(R.drawable.platon, nombre, Arrays.asList(getResources().getStringArray(R.array.CitasPlaton)));
                    break;

            }

            // Añado al filósofo
            datos.add(filosofo);
        }

        // Devuelvo el ArrayList de datos
        return datos;

    }

}
