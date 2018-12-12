package com.alexiscv.t7ej1_asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

// <params, progress, result>
// params se carga en doInBackground(String... string)
// progress se carga en el proceso/ejecución del hilo, se le pasa a publishProgress() y onProgressUpdate()
// result es el tipo de variable que devuelve nuestro doInBackground, la usabn también onPostExecute y onCancelled
public class OrdenarAsync extends AsyncTask<Integer, Float, Integer[]> {

    // Atributos
    private Activity activity; //lo necesito para llamar a findViewById. Es el contexto
    private Button botonCancelar, botonOrdenar;
    private TextView indicadorProceso, listadoElementosOrdenados;

    /**
     * Contructor
     * Le pasamos el arreglo que vamos a ordenar
     *
     * @param activity
     */
    public OrdenarAsync(Activity activity) {

        this.activity = activity;

    }

    /**
     * Se ejecuta antes de empezar el hilo en segundo plano.
     * Después de este se ejecuta el método "doInBackground" en Segundo Plano
     * <p>
     * Se ejecuta en el hilo: PRINCIPAL
     */
    @Override
    protected void onPreExecute() {

        botonOrdenar = activity.findViewById(R.id.ordenar);
        botonCancelar = activity.findViewById(R.id.cancelar);
        indicadorProceso = activity.findViewById(R.id.indicadorProgreso);
        listadoElementosOrdenados = activity.findViewById(R.id.listadoElementosOrdenados);

        // Desactivo el botón ordenar
        botonOrdenar.setEnabled(false);

        // Muestro el botón cancelar
        botonCancelar.setVisibility(View.VISIBLE);

        // Cambio el texto para informar que he comenzado a ordenar
        indicadorProceso.setText("Ordenando...");

    }

    /**
     * Se ejecuta después de "onPreExecute". Se puede llamar al hilo Principal con el método
     * "publishProgress" que ejecuta el método "onProgressUpdate" en hilo Principal
     * <p>
     * Se ejecuta en el hilo: EN SEGUNDO PLANO
     *
     * @param arregloDesordenado array con los valores pasados en "execute"
     * @return devuelve un valor al terminar de ejecutar este segundo plano.
     * Se lo envía y ejecuta "onPostExecute" si ha termiado, o a "onCancelled" si se
     * ha cancelado con "cancel"
     */
    @Override
    protected Integer[] doInBackground(Integer... arregloDesordenado) {

        Log.i("TEST", "doInBackground()");

        Integer[] arreglo = arregloDesordenado;
        float progreso = 0.0f;

        /**
         * Ordenación con Burbuja
         */
        int aux = 0;
        for (int i = 1; i < arreglo.length; i++) {
            for (int j = arreglo.length - 1; j >= i; j--) {
                if (arreglo[j - 1] > arreglo[j]) {
                    aux = arreglo[j - 1];
                    arreglo[j - 1] = arreglo[j];
                    arreglo[j] = aux;
                }
            }

            //Simula el tiempo aleatorio de descargar una imagen,
            // al dormir unos milisegundos aleatorios al hilo en segundo plano
            try {
                Thread.sleep((long) (Math.random() * 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //actualizo el porcentaje de ordenacion
            progreso = (((float) (i + 1)) / (arreglo.length - 1)) * 100;
            publishProgress(progreso);

        }

        return arreglo;

    }

    /**
     * Se ejecuta después de que en "doInBackground" ejecute el método "publishProgress".
     * <p>
     * Se ejecuta en el hilo: PRINCIPAL
     *
     * @param porcentajeProgreso array con los valores pasados en "publishProgress"
     */
    @Override
    protected void onProgressUpdate(Float... porcentajeProgreso) {

        Log.i("TEST", "onProgressUpdate() + Progreso: " + Math.round(porcentajeProgreso[0]) + "%");
        indicadorProceso.setText(Math.round(porcentajeProgreso[0]) + "%");

    }

    /**
     * Se ejecuta después de terminar "doInBackground".
     * <p>
     * Se ejecuta en el hilo: PRINCIPAL
     *
     * @param arregloOrdenado array con los valores pasados por el return de "doInBackground".
     */
    @Override
    protected void onPostExecute(Integer[] arregloOrdenado) {

        // Construimos un String con los valores ordenados del arregloOrdenado
        String stringElementos = "";
        for (int i = 0; i < arregloOrdenado.length; i++) {
            stringElementos += arregloOrdenado[i] + "-";

        }

        // Muestro el TextView donde cargaré los elementos ordenados
        listadoElementosOrdenados.setVisibility(View.VISIBLE);

        // Muestro el arregloOrdenado en el TextView
        listadoElementosOrdenados.setText(stringElementos);

        // Deshabilito el botón cancelar
        botonCancelar.setVisibility(View.INVISIBLE);

        // Activo el botón ordenar
        botonOrdenar.setEnabled(true);

        // Cambio el texto para informar que he terminado
        indicadorProceso.setText("Terminado");

    }

    /**
     * Se ejecuta si se ha llamado al método "cancel" y después de terminar "doInBackground".
     * Por lo que se ejecuta en vez de "onPostExecute"
     * Nota: Este onCancelled solo funciona a partir de Android 3.0 (Api Level 11 en adelante).
     * En versiones anteriores onCancelled no funciona
     * <p>
     * Se ejecuta en el hilo: PRINCIPAL
     *
     * @param arregloOrdenado array con los valores pasados por el return de "doInBackground".
     */
    @Override
    protected void onCancelled(Integer[] arregloOrdenado) {

        // Las acciones aquí escritas no son ejecutas inmediatamente
        // Puede haber un tiempo de latencia, mientras se cierra doInBackground
        // Por eso voy a programar la siguiente acciones en el MAIN.
        /*
        // Oculto el botón cancelar
        botonCancelar.setVisibility(View.INVISIBLE);

        // Activo el botón ordenar
        botonOrdenar.setEnabled(true);
        */

    }

}
