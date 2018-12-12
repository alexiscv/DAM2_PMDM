package com.alexiscv.t7ejem_multithread;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * IMPORTANTE:
 * Para que esta aplicación funcione, necesitamos pedir permisos en el Manifest
 * para poder tener acceso a internet.
 * <uses-permission android:name="android.permission.INTERNET"/>
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private EditText editText;
    private ListView listView;
    private String[] urls;
    private ProgressBar progressBar;
    private LinearLayout progressLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciamos los elementos XML
        editText = findViewById(R.id.descargaURL);
        listView = findViewById(R.id.listurls);
        listView.setOnItemClickListener(this);
        urls = getResources().getStringArray(R.array.URLs);
        progressBar = findViewById(R.id.progressbar);
        progressLayout = findViewById(R.id.progresslayout);
    }

    /**
     * Descarga
     *
     * @param view
     */
    public void descarga(View view) {
        String enlace = editText.getText().toString();

        Thread mThread = new Thread(new mRunn(enlace));
        mThread.start();
    }

    /**
     * Implementación del onItemClick
     * Llena el campo de texto al clicar cualquiera de los urls contenidos en la lista.
     *
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        editText.setText(urls[position]);

    }

    /**
     * A partir de una URL
     * Si intentasemos ejecutar este método al pulsar el botón, la aplicación
     * se detendría por no poder ser ejecutada en el main thread.
     *
     * @param link
     * @return
     */
    public boolean descargarUsandoThreads(String link) {
        boolean confirmacion = false;
        URL descargarEnlace = null;
        HttpURLConnection conexion = null;
        InputStream inputStream = null;
        FileOutputStream archOutputStream = null;
        File archivo = null;    // Donde almacenaremos la URL leida

        try {
            descargarEnlace = new URL(link);
            conexion = (HttpURLConnection) descargarEnlace.openConnection();
            inputStream = conexion.getInputStream();
            archivo = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) + "/" + Uri.parse(link).getLastPathSegment());
            archOutputStream = new FileOutputStream(archivo);
            int Lectura = -1;
            byte[] buffer = new byte[1024];
            while ((Lectura = inputStream.read(buffer)) != -1) {
                archOutputStream.write(buffer, 0, Lectura);

            }

            confirmacion = true;

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            // Ocultar la progresssBar
            this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressLayout.setVisibility(View.GONE);
                }
            });

            if (conexion != null) {
                conexion.disconnect();
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
            if (archOutputStream != null) {
                try {
                    archOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();

                }
            }
        }

        return confirmacion;
    }

    /**
     * Clase que implementa Runnable
     */
    private class mRunn implements Runnable {

        private String enlace;

        /**
         * Constructor
         *
         * @param enlace
         */
        public mRunn(String enlace) {
            this.enlace = enlace;
        }

        /**
         * Arrancamos el Hilo
         */
        @Override
        public void run() {

            // Mostrar la progresssBar
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    progressLayout.setVisibility(View.VISIBLE);
                }
            });

            descargarUsandoThreads(enlace);
        }
    }
}
