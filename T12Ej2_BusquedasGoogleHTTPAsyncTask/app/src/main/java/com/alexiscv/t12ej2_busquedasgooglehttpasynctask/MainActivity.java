package com.alexiscv.t12ej2_busquedasgooglehttpasynctask;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class MainActivity extends AppCompatActivity {

    private EditText entrada;
    private TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        entrada = findViewById(R.id.edPalabraClave);
        salida = findViewById(R.id.tvResultado);

        // Configuramos StrictMode para que permita accesos
        // a internet desde el hilo principal
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());

    }

    /**
     * Recoge la palabra clave escrita en el EditText y llama al método
     * resultadosGoogle.
     * <p>
     * Muestra el resultado en el TextView.
     *
     * @param view
     */
    public void buscar(View view) {

        try {
            String palabraClave = entrada.getText().toString();
            String resultado = resultadosGoogle(palabraClave);
            salida.append(palabraClave + " -- " + resultado + "\n");
        } catch (Exception e) {
            salida.append("Error al conectar \n");
            Log.e("HTTP", e.getMessage(), e);

        }

    }

    /**
     * Realiza una busqueda en Google y retorna el número de resultados encontrados.
     *
     * @param palabraClave
     * @return
     */
    private String resultadosGoogle(String palabraClave) throws Exception {

        String pagina = "", devuelve = "";

        // Creo un Obj. de tipo URL con la página que quiero cargar
        URL url = new URL("http://www.google.es/search?hl=es&q=\"" + URLEncoder.encode(palabraClave, "UTF-8") + "\"");

        // Abro la conexión a la url creada en el paso anterior
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

        // Especifico al servidor cual es el User-Agent de mi petición
        // setRequestProperty permite añadir acabeceras a la conexión.
        conexion.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1)");

        // Si el servidor devuelve un OK (200)
        if (conexion.getResponseCode() == HttpURLConnection.HTTP_OK) {

            // Creo un buffer de lectura y le cargo la respuesta del servidor.
            BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()));

            // Creo un String que leera lineas
            String linea = reader.readLine();

            // Mientras la linea no sea NULL, voy leyendo y cargando
            // el resultado en pagina
            while (linea != null) {
                pagina += linea;
                linea = reader.readLine();
            }

            // Cierro el lector
            reader.close();

            // Con toda la página almacenada en la variable "pagina"
            // Llamamos a buscaAproximadamente para que nos cargue el número de
            // resultados encontrados en la variable "devuelve"
            devuelve = buscaAproximadamente(pagina);

        } else {

            // Si el servidor ha dado error, lo cargamos en la variable
            // devuelve
            devuelve = "ERROR: " + conexion.getResponseMessage();

        }

        // Cerramos la conexión y retornamos el valor de "devuelve"
        conexion.disconnect();
        return devuelve;

    }

    /**
     * Busca en una página
     *
     * @param pagina
     * @return
     */
    private String buscaAproximadamente(String pagina) {

        // Cargamos en ini la posición, dentro de pagina, donde aparece "Aproximadamente"
        int ini = pagina.indexOf("Aproximadamente");

        // Si es encontrado...
        if (ini != -1) {
            // Calculamos el final
            // Indicandole que el final es la posición del caracter " " que encuentre
            // 16 posiciones despues de "Aproximadamente"
            int fin = pagina.indexOf(" ", ini + 16);

            // Retornamos el String que hay entre ini+16 y fin
            // que debería de ser un número
            return pagina.substring(ini + 16, fin);

        } else {
            // Si no encontramos la palabra
            // ini = -1
            // Retornamos un texto de error.
            return "no encontrado";

        }

    }

    /**
     * Ejecuta la tarea asincrona BuscarGoogle()
     *
     * @param view
     */
    public void buscar_async(View view) {

        String palabraClave = entrada.getText().toString();

        salida.append(palabraClave + "--");

        new BuscaGoogle().execute(palabraClave);

    }

    class BuscaGoogle extends AsyncTask<String, Void, String> {

        private ProgressDialog progreso;

        /**
         * Antes de ejecutar la tarea asincrona
         */
        @Override
        protected void onPreExecute() {

            progreso = new ProgressDialog(MainActivity.this);
            progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progreso.setMessage("Accediendo a Google...");
            progreso.setCancelable(false); // No muestra el botón cancelar
            progreso.show();

        }

        /**
         * Tarea que se realizará en el background
         *
         * @param palabras
         * @return
         */
        @Override
        protected String doInBackground(String... palabras) {

            try {
                return resultadosGoogle(palabras[0]);

            } catch (Exception e) {
                // True: interrumpimos hilo, false: dejamos termine
                cancel(false);
                Log.e("HTTP", e.getMessage(), e);
                return null;
            }

        }

        /**
         * Cuando doInBackground termina llama a onPostExecute
         * con su resultado, que en este caso es un String.
         * Lo tomaremos y lo añadiremos al TextView salida.
         *
         * @param resultado
         */
        @Override
        protected void onPostExecute(String resultado) {
            progreso.dismiss();
            salida.append(resultado + "\n");
        }

        /**
         * Si el proceso es cancelado...
         */
        @Override
        protected void onCancelled() {
            progreso.dismiss();
            salida.append("Error al conextar\n");
        }
    }
}
