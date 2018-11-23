package com.alexiscv.t4ejem_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextClock;
import android.widget.TextView;

import java.util.ArrayList;

public class UsuarioAdapter extends ArrayAdapter {
    private Context context;
    private ArrayList<Usuario> usuarios;

    /**
     * Constructor
     *
     * @param context
     * @param usuarios
     */
    public UsuarioAdapter(Context context, ArrayList<Usuario> usuarios) {
        // Le pasamos al constructor de la clase padre
        // El contexto, el ID del layout personalizado y el array que
        // contiene los datos que vamos a mostrar
        super(context, R.layout.listado_usuarios, usuarios);

        this.context = context;
        this.usuarios = usuarios;
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

        // Declaramos la variable ViewHolder
        // La usaremos para ahorrar recursos del sistema
        UsuarioHolder holder = null;

        // Para ahorrar recursos, en vez de crear una nueva View para cada
        // registro del array, usaremos el convertView, siempre que este no sea null
        // Así, cargaremos los nuevos registros, en los Views que no se ven en pantalla
        View item = convertView;

        if (item == null) {
            // En primer lugar "inflamos" una nueva vista, que será la que se
            // mostrará en la celda del ListView. Para ello primero creamos el
            // inflater, y despues inflamos la vista
            LayoutInflater inflater = LayoutInflater.from(context);
            item = inflater.inflate(R.layout.listado_usuarios, null);

            // Instanciamos el ViewHolder y almacenamos las referencias a sus 3 hijos
            holder = new UsuarioHolder();
            holder.nombre = item.findViewById(R.id.lblNombre);
            holder.apellidos = item.findViewById(R.id.lblApellidos);
            holder.direccion = item.findViewById(R.id.lblDireccion);

            // Guardamos el holder en la Vista item
            item.setTag(holder);
        }else{
            // Obtenemos el ViewHolder guardado previamente, para tener accedo
            // rápido a los TextView
            holder = (UsuarioHolder) item.getTag();
        }

        // A partir de la vista, recogemos los controles que contiene
        // para poder manipularlos

        // Asignamos un nombre
        holder.nombre.setText(usuarios.get(position).getNombre());

        // Recogemos el TextView apellidos y le asignamos los apellidos
        holder.apellidos.setText(usuarios.get(position).getNombre());

        // Recogemos el TextView dirección y le asignamos la dirección
        holder.direccion.setText(usuarios.get(position).getDireccion());

        // Devolvemos la vista para que se muestre en el ListView
        return item;

    }

    /**
     * UsuarioHolder
     */
    static class UsuarioHolder{
        TextView nombre;
        TextView apellidos;
        TextView direccion;
    }
}
