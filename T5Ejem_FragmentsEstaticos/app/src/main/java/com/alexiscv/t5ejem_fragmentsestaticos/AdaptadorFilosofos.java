package com.alexiscv.t5ejem_fragmentsestaticos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AdaptadorFilosofos extends ArrayAdapter<Filosofo> {

    private Context context;
    private List<Filosofo> datos;

    /**
     * Constructor
     *
     * @param context
     * @param datos
     */
    public AdaptadorFilosofos(Context context, List<Filosofo> datos) {
        super(context, R.layout.list_item_filosofo, datos);
        this.context = context;
        this.datos = datos;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Declaro variable ViewHolder
        FilosofosHolder holder = null;

        // Recojo el valor del convertView
        View item = convertView;

        if (item == null) {
            // Nunca se infló la vista aún
            // En primer lugar "inflamos" una nueva vista
            LayoutInflater inflater = LayoutInflater.from(context);
            item = inflater.inflate(R.layout.list_item_filosofo, null);

            // Instanciamos el holder y le almacenamos las referencias al
            // imageView y al TextView
            holder = new FilosofosHolder();
            holder.imagen = item.findViewById(R.id.ivFotoFilosofoItem);
            holder.nombre = item.findViewById(R.id.tvNombreFilosofoItem);

            // Guardamos el holder en la vista item
            item.setTag(holder);

        } else {
            // Obtenemos el holder almacenado previamente
            holder = (FilosofosHolder) item.getTag();

        }

        // Asigno la imagen al filosofo
        holder.imagen.setImageResource(datos.get(position).getIdFoto());

        // Establecemos el nombre
        holder.nombre.setText(datos.get(position).getNombre());

        // Devolvemos la vista para que se muestre en el ListView
        return item;

    }

    static class FilosofosHolder {
        ImageView imagen;
        TextView nombre;
    }
}
