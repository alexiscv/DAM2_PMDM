package com.alexiscv.t4ejem_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AnimalAdapter extends ArrayAdapter {

    // Atributos
    private Context context;
    private ArrayList<Animal> animales;

    /**
     * Constructor
     *
     * @param context
     * @param animales
     */
    public AnimalAdapter(Context context, ArrayList<Animal> animales) {
        // Le pasamos al constructor de la clase padre
        // El contexto, el ID del layout personalizado y el array que
        // contiene los datos que vamos a mostrar
        super(context, R.layout.listado_animales, animales);

        this.context = context;
        this.animales = animales;
    }

    /**
     * Método getVIew
     * Devuelve una View inflada con nuestro layout personalizado.
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // En primer lugar "inflamos" una nueva vista, que será la que se
        // mostrará en la celda del ListView. Para ello primero creamos el
        // inflater, y despues inflamos la vista
        LayoutInflater inflater = LayoutInflater.from(context);

        View item = inflater.inflate(R.layout.listado_animales, null);

        // A partir de la vista, recogemos los controles que contiene
        // para poder manipularlos

        // Recogemos el ImageView de imagen y le asignamos el recurso
        ImageView imagen = item.findViewById(R.id.lblImagen);
        imagen.setImageResource(animales.get(position).getDrawableImageID());

        // Recogemos el TextView nombre y le asignamos un nombre
        TextView nombre = item.findViewById(R.id.lblNombre);
        nombre.setText(animales.get(position).getNombre());

        // Recogemos el TextView posicion y le asignamos la posición
        TextView posicion = item.findViewById(R.id.lblPosicion);
        posicion.setText(String.valueOf(position));

        // Devolvemos la vista para que se muestre en el ListView
        return item;

    }
}
