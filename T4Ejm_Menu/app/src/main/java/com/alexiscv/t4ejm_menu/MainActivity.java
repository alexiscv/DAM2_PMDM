package com.alexiscv.t4ejm_menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Para crear y mostrar el menú
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflamos el menu; esto añade elementos al action bar
        // si este está presente
        getMenuInflater().inflate(R.menu.mimenu, menu);
        return true;
    }

    /**
     * Para reaccionar a una pulsación en una opción
     * del menú
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_new:
                Log.d("TEST", "Nuevo!");
                return true;

            case R.id.menu_save:
                Log.d("TEST", "Guardar!");
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }
}
