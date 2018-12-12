package com.alexiscv.extra_recyclerview_equipos;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class AniadirActivity extends AppCompatActivity {

    private Button btAniadirFoto, btAniadirEquipo;
    private ImageView ivEscudo;
    private CheckBox cbSinFoto;
    private final int REQUEST_IMAGE_CAPTURE = 1;
    protected final static String RESULTADO_INSERCION = "RESULTADO_INSERCION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aniadir);

        // Obtengo las referencias
        btAniadirFoto = (Button) findViewById(R.id.btAniadirFoto);
        btAniadirEquipo = findViewById(R.id.btAniadirEquipo);
        ivEscudo = (ImageView) findViewById(R.id.ivFotoAniadir);
        cbSinFoto = (CheckBox) findViewById(R.id.cbSinFoto);

        // Creo un Listener para detectar el estado
        // del checkbox
        cbSinFoto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    // Si está marcado...
                    // No se desa la foto, asignamos una por defecto
                    ivEscudo.setImageResource(R.drawable.generica);

                    // Almaceno la imagen en el TAG del ImageView, lo usaremos despues
                    ivEscudo.setTag(R.drawable.generica);

                    // Anulamos el botón añadir boto
                    btAniadirFoto.setEnabled(false);

                } else {
                    // Si no está marcado...
                    // Activamos el botón añadir foto
                    btAniadirFoto.setEnabled(true);

                    // Quito la imagen genérica
                    // Solo si es la generica
                    if ((int) (ivEscudo.getTag()) == R.drawable.generica) {
                        ivEscudo.setImageDrawable(null);
                    }
                }

            }
        });

        // Listener para detectar pulsación
        // en el botón de AÑADIR FOTO
        btAniadirFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Creamos un INTENT
                Intent lanzarCamaraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                if (lanzarCamaraIntent.resolveActivity(getPackageManager()) != null) {
                    // Lanzamos la activity esperando un resultado
                    // el resultado que nos devuelve será la imagen en miniatura
                    startActivityForResult(lanzarCamaraIntent, REQUEST_IMAGE_CAPTURE);
                }
            }
        });

        // Listener que controla si pulsamos en AÑADIR EQUIPO
        btAniadirEquipo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //creo el equipo comprobando datos
                Equipo equipo = crearEquipoNuevoFromDatosActivity();

                if (equipo != null) {
                    //no hubo errores, por tanto, puedo añadirlo a mi arraylist.

                    // busco la posición donde debo insertar el nuevo equipo, según
                    // sus puntos.  Tener en cuenta que los equipos aparecen ordenados por
                    // puntos en el ArrayList
                    int pos = buscarPosInArrayList(MainActivity.arrayDatosEquipos, equipo.getPuntos());

                    //inserto el equipo en su pos
                    MainActivity.arrayDatosEquipos.add(pos, equipo);

                    //Desactivo botón AÑADIR EQUIPO
                    btAniadirEquipo.setEnabled(false);

                    //configuro el Intent a devolver a la activity principal
                    Intent iDatosDevueltos = new Intent();
                    iDatosDevueltos.putExtra(RESULTADO_INSERCION, "OK");
                    iDatosDevueltos.putExtra("posicion", pos);
                    setResult(RESULT_OK, iDatosDevueltos);

                    //cierro la activity
                    finish();
                }
            }
        });

    }

    /**
     * Recogemos el resultado de la captura de la Imagen
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            // Recuperamos los extras del Intent y los cargamos en un Bundle
            Bundle extras = data.getExtras();

            // Creamos un Bitmap
            Bitmap imageBitmap;

            // Cargamos los datos recibidos en el Bitmap
            imageBitmap = (Bitmap) extras.get("data");

            // Cargamos el Bitmap en el ImageView
            ivEscudo.setImageBitmap(imageBitmap);
        }
    }

    /**
     * Es llamada por el sistema para guardar elementos
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Bitmap fotoEscudo;
        super.onSaveInstanceState(outState);

        // guardo la imagen del escudo por si se destruye la activity
        // Por ejemplo si alguien pulsa de nuevo añadir foto y aborta, al volver,
        // habremos perdido la foto
        if (ivEscudo.getDrawable() != null)
            fotoEscudo = ((BitmapDrawable) ivEscudo.getDrawable()).getBitmap();
        else fotoEscudo = null;

        outState.putParcelable("IMAGEN_ESCUDO", fotoEscudo);
    }

    /**
     * Es llamada por el sistema para restaurar elementos
     */
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        ivEscudo.setImageBitmap((Bitmap) savedInstanceState.getParcelable("IMAGEN_ESCUDO"));
    }

    /**
     * Método que crea un objeto Equipo a partir de los datos introducidos en el
     * formulario de Añadir Equipo.
     * Se comprueba que el nombre no sea vacío, escudo no vacío y si los puntos están
     * en blanco se ponen a 0
     *
     * @return el nuevo Equipo o null si hubo errores
     */
    private Equipo crearEquipoNuevoFromDatosActivity() {
        Equipo equipo = null;

        EditText etNombreEquipo, etPuntosEquipo; //para el nombre equipo y puntos
        //obtengo referencias para los nuevos objetos definidos
        etNombreEquipo = (EditText) findViewById(R.id.etNombreEquipoAniadir);
        etPuntosEquipo = (EditText) findViewById(R.id.etPuntosEquipoAniadir);

        //obtengo los puntos en formato String comprobando que el string no sea nulo.
        //No necesito comprobar que sea numero porque lo hace Android con el
        //Edittext:inputType
        String puntos = etPuntosEquipo.getText().
                toString().isEmpty() ? "0" : etPuntosEquipo.getText().toString();

        try {
            equipo = new Equipo(etNombreEquipo.getText().toString(),
                    ivEscudo.getDrawable(), Integer.parseInt(puntos));

        } catch (IllegalArgumentException e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT)
                    .show();
        }

        return (equipo);
    }


    /**
     * Método que busca la pos adecuada para insertar un equipo en un ArrayList
     * ordenado por los putnos de los equipos
     *
     * @param array  ArrayList de Equipos donde se busca la pos
     * @param puntos los puntos a buscar
     * @return la posición correspondiente en la lista
     */
    private int buscarPosInArrayList(ArrayList<Equipo> array, int puntos) {
        for (Equipo equipo : array) {
            if (equipo.getPuntos() < puntos) {
                return (array.indexOf(equipo));
            }
        }
        return array.size();
    }
}
