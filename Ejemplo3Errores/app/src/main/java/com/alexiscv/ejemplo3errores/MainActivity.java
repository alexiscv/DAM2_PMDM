package com.alexiscv.ejemplo3errores;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLogTags;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    private static final String LOGTAG = "EjemploLogsApli";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(LOGTAG, "Mensaje de Error");
        Log.w(LOGTAG, "Mensaje de warning");
        Log.i(LOGTAG, "Mensaje de información");
        Log.d(LOGTAG, "Mensaje de depuración");
        Log.v(LOGTAG, "Mensaje de verbose");

        // Vamos a generar un error
        try {
            int a = 1/0;
        }catch (Exception ex){
            Log.e(LOGTAG, "División por cero!", ex);
        }
    }
}
