package com.alexiscv.ejem_fragments_pildorasinformaticas;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentMenu extends Fragment {

    private final int[] BOTONESMENU = {R.id.bLinterna, R.id.bMusica, R.id.bNivel};
    private final int[] BOTONESILUMINADOS = {R.drawable.linterna2, R.drawable.musica2, R.drawable.nivel2};
    private int boton = -1;

    public FragmentMenu() {
        // Required empty public constructor
    }

    /**
     * Al crear la vista
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflamos la VISTA correspondiente
        // En este caso, el fragment_menu.xml
        View miMenu = inflater.inflate(R.layout.fragment_menu, container, false);

        // Comprobamos que tengamos argumentos
        // Solo tendremos argumentos si se ha ejecutado el Activity Herramientas
        if (getArguments() != null) {
            // Recuperamos el botón pulsado del Bundle
            // Esta información se crea en HerramientasActivity
            boton = getArguments().getInt("BOTONPULSADO");
        }

        // Referenciamos los botones
        ImageButton botonMenu;

        // Recorro los botones, creandolos junto con sus Listener
        for (int i = 0; i < BOTONESMENU.length; i++) {
            botonMenu = (ImageButton) miMenu.findViewById(BOTONESMENU[i]);

            // Si el botón de esta iteración es el mismo que se ha pulsado
            // le asignamos la imagen iluminada.
            if (boton == i) {
                botonMenu.setImageResource(BOTONESILUMINADOS[i]);
            }

            final int queBoton = i;

            /**
             * Listener para controlar cuando se pulsa un  botón
             * Al pulsar un botón recogemos la Activity que está en
             * ejecución, y llamamos al método menu()
             * La actividad en cuestión ejecutará su propia implementación
             * de menu().
             */
            botonMenu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Detecto en que actividad me encuanto
                    Activity estaActividad = getActivity();

                    // Para poder llamar desde la actividad actual al método
                    // del interfaz, tenemos que hacer un CAST.
                    ((ComunicaMenu) estaActividad).menu(queBoton);

                }
            });

        }

        // Devolvemos la vista
        return miMenu;
    }

}
