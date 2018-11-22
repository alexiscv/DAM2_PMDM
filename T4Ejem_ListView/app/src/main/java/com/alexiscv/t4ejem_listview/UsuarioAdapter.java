package com.alexiscv.t4ejem_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

        // En primer lugar "inflamos" una nueva vista, que será la que se
        // mostrará en la celda del ListView. Para ello primero creamos el
        // inflater, y despues inflamos la vista
        LayoutInflater inflater = LayoutInflater.from(context);

        View item = inflater.inflate(R.layout.listado_usuarios, null);

        // A partir de la vista, recogemos los controles que contiene
        // para poder manipularlos

        // Recogemos el TextView nombre y le asignamos un nombre
        TextView nombre = item.findViewById(R.id.lblNombre);
        nombre.setText(usuarios.get(position).getNombre());

        // Recogemos el TextView apellidos y le asignamos los apellidos
        TextView apellidos = item.findViewById(R.id.lblApellidos);
        apellidos.setText(usuarios.get(position).getNombre());

        // Recogemos el TextView dirección y le asignamos la dirección
        TextView direccion = item.findViewById(R.id.lblDireccion);
        direccion.setText(usuarios.get(position).getDireccion());

        // Devolvemos la vista para que se muestre en el ListView
        return item;

    }
}
