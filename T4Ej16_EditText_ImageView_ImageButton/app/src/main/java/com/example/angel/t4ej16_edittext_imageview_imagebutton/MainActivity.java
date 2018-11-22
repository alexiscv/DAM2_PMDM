package com.example.angel.t4ej16_edittext_imageview_imagebutton;

import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Atributos
    private int cuenta = 0;
    String[] escalas;
    ImageView imagenPrincipal;
    TableRow rowMiniaturas = null;
    Drawable[] imagenes = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias
        imagenPrincipal = findViewById(R.id.fotoPrincipal);
        AutoCompleteTextView tvEscalados = findViewById(R.id.escalas);
        rowMiniaturas = findViewById(R.id.miniaturas);

        /**
         * Creamos el adapter para cargar las sugerencias en el AutocompleteTextView
         */
        // Cargamos el Array de Strings
        escalas = getResources().getStringArray(R.array.escalas);

        // Creamos el Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, escalas);

        // Aplicamos el Adapter al View
        tvEscalados.setAdapter(adapter);

        /**
         * Mostramos las imagenes del Array de Imagenes
         */
        // Obtengo el ancho máximo de la pantalla del dispositivo para luego situar los botones
        int anchoPantalla = getWindowManager().getDefaultDisplay().getWidth();

        // Array de imagenes
        imagenes = cargarImagenes();

        // Creamos el Params para las ImageView (miniaturas) calculando el espacio que ocupará cada imagen
        // Lo calculamos dividiendo el tamaño de la pantalla entre el número de elementos
        TableRow.LayoutParams params = new TableRow.LayoutParams((anchoPantalla/imagenes.length), 100);

        // Cargamos la primera imagen del array de imagenes como imagen principal por defecto
        imagenPrincipal.setImageDrawable(imagenes[0]);

        // Recorremos las imagenes...
        for (Drawable img : imagenes) {

            // ... Por cada imagen, creamos un ImageView y cargamos el recurso
            ImageButton imagen = new ImageButton(this);         // Creamos la imagen
            imagen.setId(cuenta);                                       // ID

            params.leftMargin = 3;                                      // Margen izquierdo
            params.rightMargin = 3;                                     // Margen derecho

            imagen.setLayoutParams(params);                             // Asignamos el params
            imagen.setImageDrawable(img);                               // Indicamos el recurso gráfico

            imagen.setAdjustViewBounds(true);                           // Para que la imagen no se deforme
            imagen.setScaleType(ImageView.ScaleType.CENTER_INSIDE);     // Tipo de escalado

            imagen.setBackgroundResource(R.color.verdeOscuro);          // Fondo de color

            // Creamos un Listener para esta imagen
            imagen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    // Sustituimos la imagen principal, por la imagen que hemos pulsado
                    imagenPrincipal.setImageDrawable(((ImageView) v).getDrawable());

                }
            });

            // Generamos el siguiente ID de la IMG
            cuenta++;

            // Añadimos la imagen al contenedor
            rowMiniaturas.addView(imagen);

        }

        /**
         * Imagen principal
         */
        // Como cada row reparte el espacio automáticamente
        // Tenemos que asignarle a la imagen principal el número total de columnas que debe ocupar
        // Que será, el numero de miniaturas del array de imagenes
        // Lo primero es cargar sus parametros en un Params, para poder modificarlos
        TableRow.LayoutParams paramsImgPrincipal = (TableRow.LayoutParams) imagenPrincipal.getLayoutParams();

        // Ahora indicamos el número de columnas que debe de ocupar el ImageView (img principal)
        paramsImgPrincipal.span = imagenes.length;

        // Y lo aplicamos
        imagenPrincipal.setLayoutParams(paramsImgPrincipal);

        /**
         * Creamos un escuchador WATCHER para el EditText
         */
        tvEscalados.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                // Cuando el texto cambie, comprobamos que el valor del
                // AutoCompleteTextView sea válido
                if (recorteValido(s)) {
                    // Aplicamos el recorte
                    imagenPrincipal.setScaleType(ImageView.ScaleType.valueOf(s.toString()));
                }

            }
        });

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

    private boolean recorteValido(Editable recorte) {

        // Buscamos el recorte escrito con las opciones disponibles
        for (String tipoEscalado : escalas) {

            // Miramos si la opción es válida...
            if (recorte.toString().equalsIgnoreCase(tipoEscalado)) {
                return true;
            }

        }

        // Notificamos que no hay coincidencias
        Toast mensaje = Toast.makeText(this, "No existe este recorte", Toast.LENGTH_SHORT);
        mensaje.show();

        // Si no hemos encontrado coincidencias...
        return false;

    }
}
