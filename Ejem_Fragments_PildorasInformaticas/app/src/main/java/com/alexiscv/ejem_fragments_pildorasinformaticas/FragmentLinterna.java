package com.alexiscv.ejem_fragments_pildorasinformaticas;


import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentLinterna extends Fragment {

    private ImageView botonCamara;
    private Boolean encendida = false;

    public FragmentLinterna() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragmento = inflater.inflate(R.layout.fragment_linterna, container, false);

        // Referenciamos los elementos XML
        botonCamara = fragmento.findViewById(R.id.linterna);

        // Creamos un Listener para saber cuando se pulsa en la imagen
        botonCamara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Cuando se pulsa, cambiamos el valor de encendido
                if (encendida) {
                    apagarFlash();
                } else {
                    encenderFlash();
                }

            }
        });

        return fragmento;
    }

    /**
     * Encender el Flash de la camara
     */
    private void encenderFlash() {
        botonCamara.setImageResource(R.drawable.linterna2);
        encendida = true;

        // Detecto en que actividad me encuanto
        Activity estaActividad = getActivity();

        // Para poder llamar desde la actividad actual al método
        // del interfaz ManejaFlashCamara, tenemos que hacer un CAST.
        ((ManejaFlashCamara) estaActividad).enciendeApaga(encendida);

    }

    /**
     * Apagar Flash de la camara
     */
    private void apagarFlash() {
        botonCamara.setImageResource(R.drawable.linterna);
        encendida = false;

        // Detecto en que actividad me encuanto
        Activity estaActividad = getActivity();

        // Para poder llamar desde la actividad actual al método
        // del interfaz ManejaFlashCamara, tenemos que hacer un CAST.
        ((ManejaFlashCamara) estaActividad).enciendeApaga(encendida);
    }

}
