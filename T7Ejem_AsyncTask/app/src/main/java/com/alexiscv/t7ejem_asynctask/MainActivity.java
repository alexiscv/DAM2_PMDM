package com.alexiscv.t7ejem_asynctask;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final boolean CANCELAR_SI_MAS_DE_100_IMAGENES = true;
    private final String TAG_LOG = "test";
    private TextView TV_mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciamos el TextView
        TV_mensaje = (TextView) findViewById(R.id.TextView_mensajesAlUsuario);

        // Al pulsar sobre el boton
        // Mostramos un mensaje en el LOG y en pantalla con TOAST
        Button B_probarHacerDosCosasALaVez = (Button) findViewById(R.id.button_probarComoPodemosHacerOtraCosa);
        B_probarHacerDosCosasALaVez.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v(TAG_LOG, "...Haciendo otra cosa el usuario sobre el hilo PRINCIPAL a la vez que carga...");
                Toast toast = Toast.makeText(getApplicationContext(), "...Haciendo otra cosa el usuario sobre el hilo PRINCIPAL a la vez que carga...", Toast.LENGTH_SHORT);
                toast.show();
            }
        });


        // Creamos el Obj. AsyncTask
        // y lo ejecutamos
        // En execute podríamos enviar datos a doInBackground
        // Ejemplo:
        // miTareaAsincrona.execute("Pablo", "Manolo", "Antonio", "Lo que sea")
        // Los recogeriamos como un array nombreVariable[posicion] ejemplo: datos[2]
        DescargarImagenesDeInternetEnOtroHilo miTareaAsincrona = new DescargarImagenesDeInternetEnOtroHilo(CANCELAR_SI_MAS_DE_100_IMAGENES);
        miTareaAsincrona.execute();

    }

    /**
     * Ejemplo de una AsyncTask
     */
    private class DescargarImagenesDeInternetEnOtroHilo extends AsyncTask<String, Float, Integer> {

        private boolean cancelarSiHayMas100Archivos;
        private ProgressBar miBarraDeProgreso;


        /**
         * Contructor de ejemplo que podemos crear en el AsyncTask
         * en este ejemplo le pasamos un booleano que indica si hay más de 100 archivos o no.
         * Si le pasas true se cancela por la mitad del progreso, si le pasas false seguirá
         * hasta el final sin cancelar la descarga simulada
         *
         * @param cancelarSiHayMas100Archivos
         */
        public DescargarImagenesDeInternetEnOtroHilo(boolean cancelarSiHayMas100Archivos) {
            this.cancelarSiHayMas100Archivos = cancelarSiHayMas100Archivos;
        }

        /**
         * Se ejecuta antes de empezar el hilo en segundo plano.
         * Después de este se ejecuta el método "doInBackground" en Segundo Plano
         * <p>
         * Se ejecuta en el hilo: PRINCIPAL
         */
        @Override
        protected void onPreExecute() {
            TV_mensaje.setText("ANTES de EMPEZAR la descarga. Hilo PRINCIPAL");
            Log.v(TAG_LOG, "ANTES de EMPEZAR la descarga. Hilo PRINCIPAL");

            miBarraDeProgreso = findViewById(R.id.progressBar_indicador);

        }

        /**
         * Se ejecuta después de "onPreExecute". Se puede llamar al hilo Principal con el método
         * "publishProgress" que ejecuta el método "onProgressUpdate" en hilo Principal
         * <p>
         * Se ejecuta en el hilo: EN SEGUNDO PLANO
         *
         * @param variableNoUsada array con los valores pasados en "execute"
         * @return devuelve un valor al terminar de ejecutar este segundo plano.
         * Se lo envía y ejecuta "onPostExecute" si ha termiado, o a "onCancelled" si se
         * ha cancelado con "cancel"
         */
        @Override
        protected Integer doInBackground(String... variableNoUsada) {

            int cantidadImagenesDescargadas = 0;
            float progreso = 0.0f;

            // Suponemos que tenemos 200 imágenes en algún laso de Internet.
            // isCancelled() comprueba si hemos cancelado con cancel()
            // el hilo en segundo plano.
            while (!isCancelled() && cantidadImagenesDescargadas < 200) {
                cantidadImagenesDescargadas++;

                Log.v(TAG_LOG, "Imagen descargada número " + cantidadImagenesDescargadas + ". Hilo en SEGUNDO PLANO");

                // Simulamos la descarga de una imagen. Iría aquí el código
                // ........................
                try {
                    //Simula el tiempo aleatorio de descargar una imagen,
                    // al dormir unos milisegundos aleatorios al hilo en segundo plano
                    Thread.sleep((long) (Math.random() * 100));
                } catch (InterruptedException e) {
                    cancel(true); // Cancelamos si entramos al catch porque algo ha ido mal
                    e.printStackTrace();
                }
                // Simulamos la descarga de una imagen. Iría aquí el código
                // ........................

                progreso += 0.5;

                // Enviamos el progreso a "onProgressUpdate" para que se lo muestre al usuario,
                // pues en el hilo principal no podemos llamar a nada de la interfaz
                publishProgress(progreso);

                //Si hemos decidido cancelar al pasar de 100 imágenes descargadas entramos aquí.
                if (cancelarSiHayMas100Archivos && cantidadImagenesDescargadas > 100) {
                    cancel(true);
                }
            }

            return cantidadImagenesDescargadas;

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
            TV_mensaje.setText("Progreso descarga: " + porcentajeProgreso[0] + "%. Hilo PRINCIPAL");
            Log.v(TAG_LOG, "Progreso descarga: " + porcentajeProgreso[0] + "%. Hilo PRINCIPAL");

            miBarraDeProgreso.setProgress(Math.round(porcentajeProgreso[0]));

        }

        /**
         * Se ejecuta después de terminar "doInBackground".
         * <p>
         * Se ejecuta en el hilo: PRINCIPAL
         *
         * @param cantidadProcesados array con los valores pasados por el return de "doInBackground".
         */
        @Override
        protected void onPostExecute(Integer cantidadProcesados) {
            TV_mensaje.setText("DESPUÉS de TERMINAR la descarga. Se han descarcado " + cantidadProcesados + " imágenes. Hilo PRINCIPAL");
            Log.v(TAG_LOG, "DESPUÉS de TERMINAR la descarga. Se han descarcado " + cantidadProcesados + " imágenes. Hilo PRINCIPAL");

            TV_mensaje.setTextColor(Color.GREEN);

        }

        /**
         * Se ejecuta si se ha llamado al método "cancel" y después de terminar "doInBackground".
         * Por lo que se ejecuta en vez de "onPostExecute"
         * Nota: Este onCancelled solo funciona a partir de Android 3.0 (Api Level 11 en adelante).
         * En versiones anteriores onCancelled no funciona
         * <p>
         * Se ejecuta en el hilo: PRINCIPAL
         *
         * @param cantidadProcesados array con los valores pasados por el return de "doInBackground".
         */
        @Override
        protected void onCancelled(Integer cantidadProcesados) {
            TV_mensaje.setText("DESPUÉS de CANCELAR la descarga. Se han descarcado " + cantidadProcesados + " imágenes. Hilo PRINCIPAL");
            Log.v(TAG_LOG, "DESPUÉS de CANCELAR la descarga. Se han descarcado " + cantidadProcesados + " imágenes. Hilo PRINCIPAL");

            TV_mensaje.setTextColor(Color.RED);
        }


    }

}
