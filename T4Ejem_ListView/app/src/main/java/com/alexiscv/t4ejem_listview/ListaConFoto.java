package com.alexiscv.t4ejem_listview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class ListaConFoto extends Activity {

    private ArrayList<Animal> animales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_con_foto);
    }

    private void rellenarArrayAnimales(){
        String[] animalesArray = getResources().getStringArray(R.array.array_animales);

        animales.add(new Animal(animalesArray[0], R.drawable.perro));
        animales.add(new Animal(animalesArray[0], R.drawable.gato));
        animales.add(new Animal(animalesArray[0], R.drawable.raton));
        animales.add(new Animal(animalesArray[0], R.drawable.loro));
        animales.add(new Animal(animalesArray[0], R.drawable.leon));
    }
}
