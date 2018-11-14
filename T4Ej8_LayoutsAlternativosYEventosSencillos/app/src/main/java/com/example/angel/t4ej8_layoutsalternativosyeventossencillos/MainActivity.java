package com.example.angel.t4ej8_layoutsalternativosyeventossencillos;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView pulsadoAnteriormente;
    private int contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Intercambiar nombre
     */
    private void intercambiarNombre(View v){

        // Recogemos la información del View pulsado
        TextView pulsadoAhora = (TextView) v;

        // Si no es la primera vez que se pulsa un color
        // Hacemos el intercambio
        if( contador > 0 ){
            // Intercambiamos el texto
            pulsadoAhora.setText(pulsadoAnteriormente.getText().toString());

            // Tras cambiar el nombre, llamaos al intercambio de color
            intercambiarColor(v);

            // Despues reseteamos el contador
            contador = 0;
            Log.e("TEST", "Ponemos contador a 0");

        }else{
            // Si es el primer color, lo guardamos e incrementamos el contador
            pulsadoAnteriormente = pulsadoAhora;
            contador++;

            Log.e("TEST", "Primera pulsación");

        }

    }

    /**
     * Intercambiar color
     */
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void intercambiarColor(View v){

        // Recogemos la información del View pulsado
        TextView pulsadoAhora = (TextView) v;

        // Intercambiamos el color
        pulsadoAhora.setBackground(pulsadoAnteriormente.getBackground());

    }

    /**
     * Notificar con TOAST
     */
    public void notificarConToast(View v){

        TextView tvPulsado = (TextView) v;

        // TOAST
        Toast toastRed = Toast.makeText(getApplicationContext(),"Has pulsado "+tvPulsado.getText().toString(), Toast.LENGTH_SHORT);
        toastRed.show();

        // Intercambiar
        intercambiarNombre(v);

    }
}
