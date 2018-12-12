package com.alexiscv.t7ejem_hilosencillo;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private EditText entrada;
    private TextView salida;
    private ProgressBar cargando;
    int res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciamos los elementos XML
        entrada = findViewById(R.id.entrada);
        salida = findViewById(R.id.salida);
        cargando = findViewById(R.id.cargando);
    }

    public void calcularOperacion(View view) {

        // Mostramos el ProgressBar
        cargando.setVisibility(View.VISIBLE);

        // Recogemos el valor escrito en el ET
        int n = Integer.parseInt(entrada.getText().toString());

        // Creamos el Hilo que calculará el factorial
        MiHilo thread = new MiHilo(n);

        // Lo iniciamos
        thread.start();

    }

    public int factorial(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= 1;
            SystemClock.sleep(1000);
        }

        return res;

    }

    class MiHilo extends Thread {
        private int n, res;

        public MiHilo(int n) {
            this.n = n;
        }

        @Override
        public void run() {

            res = factorial(n);

            // Mostramos el resultado, llamando al HiloPadre
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    // Ejecutamos la operación que va a tardar...
                    salida.append(res + "\n");

                    // Ocultamos el PrograssBar
                    cargando.setVisibility(View.GONE);
                }
            });

        }
    }
}
