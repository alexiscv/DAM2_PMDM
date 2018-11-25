package com.alexiscv.t4ejem_tabhostyfragments;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;

// IMPORTANTE!! Debe extender de FragmentActivity
public class FragmentTabHostActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tab_host);

        // Obtenemos la referencia al TabHost y lo preparamos para su
        // configuración, llamando al método setup
        FragmentTabHost pestanias = (FragmentTabHost) findViewById(android.R.id.tabhost);
        pestanias.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);

        // Para cada pestaña...
        /**
         * Pestaña #1
         */
        pestanias.addTab(pestanias.newTabSpec("tab1").setIndicator("PESTAÑA 1"), Tab1.class, null);

        /**
         * Pestaña #2
         */
        pestanias.addTab(pestanias.newTabSpec("tab2").setIndicator("PESTAÑA 2"), Tab2.class, null);

        /**
         * Pestaña #3
         */
        pestanias.addTab(pestanias.newTabSpec("tab3").setIndicator("PESTAÑA 3"), Tab3.class, null);

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
}
