package com.alexiscv.ejem_fragments_pildorasinformaticas;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class HerramientasActivity extends AppCompatActivity implements ComunicaMenu, ManejaFlashCamara {

    private Fragment[] misFragmentos;
    private CameraManager miCamara;
    private String idCamara;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herramientas);

        // Creamos un Obj. de tipo Bundle y recuperamos los Extras del Intent
        // que ha creado este Activity
        Bundle extras = getIntent().getExtras();

        // Almacenos los Fragments en el array de Fragments
        misFragmentos = new Fragment[3];
        misFragmentos[0] = new FragmentLinterna();
        misFragmentos[1] = new FragmentMusica();
        misFragmentos[2] = new FragmentNivel();

        // Llamamos al método menu() indicando el boton que ha sido pulsado
        // lo recuperamos de los extras que contiene el Bundle.
        menu(extras.getInt("BOTONPULSADO"));

        // PARA MANEJAR LA CAMARA
        // Cargamos en nuestro CameraManager un manejador de camaras
        miCamara = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        // Especificamos a que camara queremos acceder
        try {
            idCamara = miCamara.getCameraIdList()[0];
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Implementación del interfaz ComunicaMenú
     * Este método es llamado desde el FragmentMenu
     * Como estamos en el HerramientasActivity, debemos gestionar
     * que fragmento debemos cargar, dependiendo del botón que se ha pulsado.
     * <p>
     * Usaremos FragmentManager
     *
     * @param queboton
     */
    @Override
    public void menu(int queboton) {

        // Creamos un manejador (FragmentManager) y llamamos al método
        // getFragmentManager que nos devuelve un Obj. de tipo FragmentManager
        // que usaremos como manejador.
        FragmentManager miManejador = getFragmentManager();

        // Usando el manejador, recuperamos la transacción
        FragmentTransaction miTransaccion = miManejador.beginTransaction();

        // Utilizamos la transacción para cambiar un Fragment por otro
        // le pasamos el ID del contenedor y el Fragmento a cargar
        miTransaccion.replace(R.id.rlHerramientas, misFragmentos[queboton]);

        //.. Necesitamos también modificar el Fragment que funciona de Menú
        // Para que se ilumine el botón pulsado
        // Lo lograremos creando un Fragment nuevo y pasandole el botón pulsado
        // en un Bundle que contiene arguments
        // Despues usando FragmentTransaction remplazamos el fragment antiguo por el nuevo.
        Fragment menu_iluminado = new FragmentMenu();
        Bundle datos = new Bundle();
        datos.putInt("BOTONPULSADO", queboton);
        menu_iluminado.setArguments(datos);
        miTransaccion.replace(R.id.fMenu, menu_iluminado);
        //..

        // Comenzamos la transacción
        miTransaccion.commit();

    }

    /**
     * Implementación del interfaz ManejaFlashCamara
     * Que nos permite accionar el flash de la camara desde el FragmenteLinterna
     *
     * @param estadoFlash
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void enciendeApaga(boolean estadoFlash) {
        try {

            if (estadoFlash) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    miCamara.setTorchMode(idCamara, true);
                }
                Toast.makeText(this, "FLASH ENCENDIDO", Toast.LENGTH_SHORT).show();

            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    miCamara.setTorchMode(idCamara, false);
                }
                Toast.makeText(this, "FLASH APAGADO", Toast.LENGTH_SHORT).show();
            }

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }
}
