package com.alexiscv.t7ej1_asynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Integer[] arregloEnteros;
    private TextView arregloDesordenado;
    private static final int MAX_NUMEROS = 200;
    private OrdenarAsync miOrdenacionAsincrona;
    private Button botonCancelar, botonOrdenar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referencias
        arregloDesordenado = findViewById(R.id.listadoElementos);
        botonOrdenar = findViewById(R.id.ordenar);
        botonCancelar = findViewById(R.id.cancelar);

        //generar array de números desordenados entre 0 y 100
        generarNumerosDesordenados(MAX_NUMEROS);

        // Muestro la lista desordenada
        // Mostrar numeros en textview
        arregloDesordenado.setText(getStringFromArrayInt(arregloEnteros));

    }

    /**
     * Crea y ejecuta la tarea asincrona
     *
     * @param view
     */
    public void ordenar(View view) {

        miOrdenacionAsincrona = new OrdenarAsync(MainActivity.this);
        miOrdenacionAsincrona.execute(arregloEnteros);

    }

    /**
     * Cancela la tarea asincrona
     *
     * @param view
     */
    public void cancelar(View view) {

        miOrdenacionAsincrona.cancel(true);
        botonCancelar.setVisibility(View.INVISIBLE);
        botonOrdenar.setEnabled(true);

    }

    /**
     * Genera un array de números aleatorios
     *
     * @param max
     */
    private void generarNumerosDesordenados(int max) {
        Random r = new Random();
        this.arregloEnteros = new Integer[max];


        for (int i = 0; i < max; i++) {
            this.arregloEnteros[i] = r.nextInt(100);
        }
    }

    /**
     * Método que obtiene un String de un array de enteros que se le pasa
     *
     * @param numeros array de enteros que se debe pasar a String
     * @return el String que representa el array de numeros. Los número se separan por "-"
     */
    public static String getStringFromArrayInt(Integer[] numeros) {
        String textoArrayNumeros = "";
        for (int i = 0; i < numeros.length; i++) {
            textoArrayNumeros += numeros[i];
            if (i != numeros.length - 1) {
                //no es el último número
                textoArrayNumeros += "-";
            }

        }
        return textoArrayNumeros;
    }
}
