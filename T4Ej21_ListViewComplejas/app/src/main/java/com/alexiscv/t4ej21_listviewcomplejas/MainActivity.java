package com.alexiscv.t4ej21_listviewcomplejas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Button botonAdd, botonEdit, botonCancel;
    private EditText texto;
    private ListView listado;
    private ArrayList<String> datos;
    private ArrayAdapter<String> adaptador;
    private int posicionSeleccionada;
    private boolean isEditando = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciamos nuestros elementos XML
        botonAdd = findViewById(R.id.bAdd);
        botonEdit = findViewById(R.id.bEdit);
        botonCancel = findViewById(R.id.bCancel);
        texto = findViewById(R.id.etTexto);
        listado = findViewById(R.id.lvListado);

        /**
         * Inicializar array de datos
         * o recuperar su contenido si se ha destruido el activity
         */
        if (savedInstanceState == null) {
            // Inicializamos el ArrayList
            datos = new ArrayList<>();
        } else {
            // Hay estado guardado
            datos = savedInstanceState.getStringArrayList("datos");
            posicionSeleccionada = savedInstanceState.getInt("posicionSeleccionada");
            isEditando = savedInstanceState.getBoolean("isEditando");

        }

        // Asociamos un TextWatcher para controlar
        // Cuando el campo texto contiene texto
        texto.addTextChangedListener(mTextEditorWatcher);

        // Creamos un adaptaor
        adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datos);

        // Asignamos el adaptador a nuestro ListView
        listado.setAdapter(adaptador);

        // Muestro los botones
        mostrarBotones(isEditando);

        // Listener para detectar clicks en los elementos del listado
        listado.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Pasamos a modo edición
                isEditando = true;

                // Cargamos ese elemento en el EditText
                texto.setText(datos.get(position));

                // Cargamos la posición en nuestra variable comodin
                // Para acceder desde el método editItem()
                posicionSeleccionada = position;

                // Mostrar los botones
                mostrarBotones(isEditando);

            }
        });

    }

    /**
     * TextWatcher
     */
    private final TextWatcher mTextEditorWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 0) {
                botonAdd.setEnabled(false);

            } else {
                botonAdd.setEnabled(true);
            }

        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    /**
     * Nos permite añadir un elemento al Listado
     *
     * @param view
     */
    public void addItem(View view) {
        // Añadimos
        Log.e("TEST", "Se añadirá:" + texto.getText());
        datos.add(texto.getText().toString());

        // Vaciamos el TextView
        texto.setText("");

        // Refrescar datos
        adaptador.notifyDataSetChanged();

    }

    /**
     * Editar un elemento
     *
     * @param view
     */
    public void editItem(View view) {
        // Modificamos el texto
        // Cargando el nuevo String donde se almacenaba el viejo
        datos.set(posicionSeleccionada, texto.getText().toString());

        // Vaciamos el TextView
        texto.setText("");

        // Terminamos la edición
        terminarEdicion(view);

        // Refrescar datos
        adaptador.notifyDataSetChanged();

    }

    /**
     * Terminar edición y volver a modo añadir
     *
     * @param view
     */
    public void terminarEdicion(View view) {

        // Cancelamos el modo edición
        isEditando = false;

        // Reseteamos el campo EditText
        texto.setText("");

        // Muestros los botones
        mostrarBotones(isEditando);

    }

    /**
     * Muestra los botones necesarios
     *
     * @param isEditando
     */
    private void mostrarBotones(Boolean isEditando) {

        if (isEditando) {
            // Ocultamos el botón ADD
            botonAdd.setVisibility(View.INVISIBLE);

            // Y Mostramos los botones MODIFICAR Y CANCELAR
            botonEdit.setVisibility(View.VISIBLE);
            botonCancel.setVisibility(View.VISIBLE);

        } else {
            // Mostramos el boton ADD
            botonAdd.setVisibility(View.VISIBLE);

            // Ocultamos los botones MODIFICAR Y CANCELAR
            botonEdit.setVisibility(View.INVISIBLE);
            botonCancel.setVisibility(View.INVISIBLE);
        }

    }

    /**
     * Guardar el estado de las variables y arrays
     *
     * @param guardarEstado
     */
    @Override
    protected void onSaveInstanceState(Bundle guardarEstado) {
        super.onSaveInstanceState(guardarEstado);

        // Guardo el array con los datos, la posción del item elegido
        // y el el modo actual, editanco o añadiendo
        guardarEstado.putStringArrayList("datos", datos);
        guardarEstado.putInt("posicionSeleccionada", posicionSeleccionada);
        guardarEstado.putBoolean("isEditando", isEditando);

    }
}
