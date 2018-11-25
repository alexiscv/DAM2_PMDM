package com.alexiscv.t4ejem_toolbar;

import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // El paso más importante es indicar en JAVA que nuestra Toolbar
        // va a actuar como ActionBar (appBar) de la activity
        // Para ello, en el método onCreate() llamamos al método setSupportActionBar()
        // Y le pasamos la referencia de la ToolBar
        //
        // Asegurarse de importer
        // import android.support.v7.widget.Toolbar;
        Toolbar toolbar = findViewById(R.id.toolbarGrandeID);
        setSupportActionBar(toolbar);

        // Para ocultar la barra de accion
        //getSupportActionBar().hide();

        // Con esta linea ocultamos el titulo de la Activity
        // Para que solo salgan nuestros textos
        //getSupportActionBar().setDisplayShowTitleEnabled(false);


    }

}
