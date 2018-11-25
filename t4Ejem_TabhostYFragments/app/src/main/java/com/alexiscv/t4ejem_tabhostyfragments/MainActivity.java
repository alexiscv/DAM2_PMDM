package com.alexiscv.t4ejem_tabhostyfragments;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TabHost;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtenemos la referencia al TabHost y lo preparamos para su
        // configuración, llamando al método setup
        TabHost pestanias = (TabHost) findViewById(android.R.id.tabhost);
        pestanias.setup();

        // Para cada pestaña...
        /**
         * Pestaña #1
         */
        // Creamos un objeto TabSpec al que pasaremos el ID de la pestaña
        TabHost.TabSpec spec = pestanias.newTabSpec("miTab1");

        // Asignamos el contenido, su layout
        spec.setContent(R.id.tab1);

        // Asignamos el indicador de la pestaña, su texto e icono
        spec.setIndicator("TAB1", getResources().getDrawable(android.R.drawable.ic_btn_speak_now));

        // Añadimos la pestaña al TabHost
        pestanias.addTab(spec);

        /**
         * Pestaña #2
         */
        spec = pestanias.newTabSpec("miTab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("", getResources().getDrawable(android.R.drawable.ic_dialog_map));
        pestanias.addTab(spec);

        /**
         * Pestaña #3
         */
        spec = pestanias.newTabSpec("miTab3");
        spec.setContent(R.id.tab3);
        spec.setIndicator("TAB3", getResources().getDrawable(android.R.drawable.ic_dialog_alert));
        pestanias.addTab(spec);

        /**
         * OnTabChanged
         * Se lanza cada vez que se cambia de pestaña y nos informa
         * de la nueva pestaña visualzada
         */
        pestanias.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
                Log.i("TEST", "Pulsada la pestaña " + tabId);

            }
        });

    }

    /**
     * Ir al otro view
     * @param view
     */
    public void verFragmentTabHost(View view) {
        Intent intent = new Intent(this, FragmentTabHostActivity.class);
        startActivity(intent);
    }
}
