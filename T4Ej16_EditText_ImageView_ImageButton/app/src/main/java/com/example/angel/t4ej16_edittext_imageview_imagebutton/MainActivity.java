package com.example.angel.t4ej16_edittext_imageview_imagebutton;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableRow;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Creamos el adapter para cargar las sugerencias en el AutocompleteTextView
         */
        // Referenciamos
        AutoCompleteTextView tvEscalados = findViewById(R.id.escalas);

        // Cargamos el Array de Strings
        String[] escalas = getResources().getStringArray(R.array.escalas);

        // Creamos el Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, escalas);

        // Aplicamos el Adapter al View
        tvEscalados.setAdapter(adapter);

        /**
         * Mostramos las imagenes del Array de Imagenes
         */
        // Referenciamos el elemento padre
        TableRow rowMiniaturas = findViewById(R.id.miniaturas);

        //obtengo el ancho máximo de la pantalla del dispositivo para luego situar los botones
        int anchoPantalla = getWindowManager().getDefaultDisplay().getWidth();

        // Array de imagenes
        Drawable[] imagenes = cargarImagenes();

        // Creamos el Params para las ImageView calculando el espacio que ocupará cada imagen
        // Lo calculamos dividiendo el tamaño de la pantalla entre el número de elementos
        TableRow.LayoutParams params = new TableRow.LayoutParams((anchoPantalla / imagenes.length), 100);

        // Recorremos las imagenes...
        for (Drawable img : imagenes) {

            // ... Por cada imagen, creamos un ImageView y cargamos el recurso
            ImageButton imagen = new ImageButton(this);
            imagen.setLayoutParams(params);
            imagen.setImageDrawable(img);
            imagen.setAdjustViewBounds(true); // Para que la imagen no se deforme
            imagen.setScaleType(ImageView.ScaleType.CENTER_INSIDE); // Tipo de escalado

            // Añadimos la imagen al contenedor
            rowMiniaturas.addView(imagen);

        }
    }

    private Drawable[] cargarImagenes() {

        // Cogemos los recursos del array xml
        TypedArray typedArray = getResources().obtainTypedArray(R.array.imagenes);

        // Construimos un Array de Drawrables
        Drawable[] arrayImagenes = new Drawable[typedArray.length()];

        // Cargamos loa valores
        for (int i = 0; i < arrayImagenes.length; i++) {

            arrayImagenes[i] = typedArray.getDrawable(i);

        }

        // Retornamos el array de Drawrables
        return arrayImagenes;

    }
}
