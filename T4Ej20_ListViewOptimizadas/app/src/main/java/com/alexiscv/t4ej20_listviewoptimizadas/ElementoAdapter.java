package com.alexiscv.t4ej20_listviewoptimizadas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Adaptador personalizado
 * Nos permite cargar nuestro layout personalizado y gestionar como aparecerán los datos
 */
public class ElementoAdapter extends ArrayAdapter<Elemento> {

    // Atributos
    // Siempre necesitamos: contexto, layout y datos
    private Context context;
    private int layoutResourceId;
    private ArrayList<Elemento> datos;

    /**
     * Constructor
     * Primero cargamos los datos en el constructor padre
     * y luego en nuestro atributos.
     *
     * @param context
     * @param layoutResourceId
     * @param datos
     */
    public ElementoAdapter(Context context, int layoutResourceId, ArrayList<Elemento> datos) {
        super(context, layoutResourceId, datos);

        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.datos = datos;
    }

    /**
     * Debemos sobreescribir el método getView
     * Es llamado por el ListView para generar cada una de las vistas
     * se crea una vista por cada elemento a mostrar en el listado.
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Declaro variable ViewHolder
        // para manejar los datos de las vistas
        ViewHolder holder = null;

        // Recojo el valor de convertView
        View item = convertView;

        if (item == null) {
            // Es la primera vez que se llama a getView, debo crear el holder,
            // inflar la vista, cargar los datos en el holder y guardarlo
            // en la vista inflada.
            holder = new ViewHolder();

            // Inflo la vista
            LayoutInflater inflater = LayoutInflater.from(context);
            item = inflater.inflate(layoutResourceId, null);

            // Cargo las referencias en el HOLDER
            holder.imagen = item.findViewById(R.id.imagen);
            holder.nombre = item.findViewById(R.id.nombre);

            // Guardo el holder en la vista
            item.setTag(holder);

        } else {
            // Recupero el holder guardado antes en la vista
            holder = (ViewHolder) item.getTag();
        }

        // Asigno la imagen correspondiente
        holder.imagen.setImageDrawable(getItem(position).getImagen());
        holder.nombre.setText(getItem(position).getNombre());

        // Devuelvo la vista
        return item;

    }

    /**
     * Declaro un ViewHolder llamado ViewHolder
     * Esta clase nos permite ir reutilizando un espacio de memoria
     * donde cargaremos la vista que toque en cada iteración del ListView
     */
    static class ViewHolder {
        ImageView imagen;
        TextView nombre;
    }
}
