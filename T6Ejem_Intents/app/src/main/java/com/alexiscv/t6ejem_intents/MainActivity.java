package com.alexiscv.t6ejem_intents;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView decision;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciamos
        decision = findViewById(R.id.tvDecision);

    }

    /**
     * Método para recoger la respuesta de un Activity
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        // Comprobamos el código de petición
        // y que la petición sea OK
        if (requestCode == 1234 && resultCode == RESULT_OK) {

            Boolean acepto = data.getExtras().getBoolean("aceptoCondiciones");

            if (acepto){
                decision.setText("EL USUARIO ACEPTA");

            }else{
                decision.setText("EL USUARIO NO ACEPTA");

            }

        }
    }

    public void web(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://alberguesasturias.com"));

        // Lanzamos el Intent
        startActivity(i);
    }

    public void web_WS(View view) {
        Intent i = new Intent(Intent.ACTION_WEB_SEARCH);
        i.setData(Uri.parse("https://alberguesasturias.com"));


        // Lanzamos el Intent
        startActivity(i);
    }

    public void localizacion(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:41.656313, -0.877351"));

        // Lanzamos el Intent
        startActivity(i);
    }

    public void localizacionStreetView(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("google.streetview:cbll=46.414382,10.013988"));

        // Lanzamos el Intent
        startActivity(i);
    }

    public void llamar(View view) {
        // Para ACTION_CALL
        // necesitamos el permiso android.permission.CALL_PHONE
        // Estos permisos se dan en el MANIFEST usando Intent Filters
        Intent i = new Intent(Intent.ACTION_CALL, Uri.parse("tel:666999666"));

        // Lanzamos el Intent
        startActivity(i);
    }

    public void marcar(View view) {
        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:666999666"));

        // Lanzamos el Intent
        startActivity(i);
    }

    public void foto(View view) {
        // Toma una foto y nos devuelve donde la guardó
        Intent i = new Intent("android.media.action.IMAGE_CAPTURE");

        // Lanzamos el Intent
        startActivity(i);
    }

    public void email(View view) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("text/plain");
        i.putExtra(Intent.EXTRA_SUBJECT, "Mi Asunto");
        i.putExtra(Intent.EXTRA_TEXT, "Contenido del correo.");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{"miemail@gmail.com"});

        // Lanzamos el Intent
        startActivity(i);
    }

    /**
     * Intent explicito
     *
     * @param view
     */
    public void abrirCondiciones(View view) {

        // Referenciamos el elemento XML
        TextView nombre = findViewById(R.id.etNombre);

        // Comprobamos que exista un nombre
        if (nombre.getText().length() > 0) {
            // Creamos el Intent
            Intent i = new Intent(MainActivity.this, CondicionesActivity.class);

            // Almacenamos el nombre como un EXTRA, para recuperarlo desde la nueva Activity
            i.putExtra("NOMBRE", nombre.getText().toString());

            // Lanzamos la activity
            //startActivity(i);

            /**
             * Si deseamos lanzar una Activity pero esperando por
             * una respuesta usaremos starActivityForResult()
             */
            // Este método informa al contexto de la aplicación
            // que deseamos obtener datos de la actividad que se llama
            // cuando sea terminada.
            // Indicamos el intent y un cod. de petición
            startActivityForResult(i, 1234);

        } else {
            Toast.makeText(this, "Debes escribir un nombre", Toast.LENGTH_SHORT).show();

        }

    }
}
