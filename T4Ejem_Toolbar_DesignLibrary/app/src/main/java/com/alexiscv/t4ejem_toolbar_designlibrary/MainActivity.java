package com.alexiscv.t4ejem_toolbar_designlibrary;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Referenciamos la ToolBar XML
        Toolbar toolbar = findViewById(R.id.appbar);
        setSupportActionBar(toolbar);

        // Referenciamos el viewPager
        ViewPager viewPager = findViewById(R.id.viewpager);
        viewPager.setAdapter(new MiFragmentPageAdapter(getSupportFragmentManager()));

        // Programar el TabLayout
        // Obtenemo la ref. al TabLayout
        TabLayout tabLayout = findViewById(R.id.appbartabs);

        // Indicamos el tipo de pestañas que queremos utilizar
        // mediante su método setTabMode()
        // Fijas (TabLayout.MODE_FIXED)
        // Deslizantes (TabLayout.MODE_SCROLLABLE)
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        // Enlazamos nuestro ViewPager con el TabLayout
        // llamando al método setupWithViewPager()
        // Este metodo se encarga de crear las pestañas necesarias en el TabLayout
        // con sus titulos a partir de la info proporcionada por el adaptador del ViewPager.
        // y controla los cambios de pestaña a pestaña
        tabLayout.setupWithViewPager(viewPager);
    }

}
