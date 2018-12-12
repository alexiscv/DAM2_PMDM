package com.alexiscv.extra_recyclerview_equipos;

import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorEquipos extends RecyclerView.Adapter<AdaptadorEquipos.EquiposViewHolder> implements View.OnClickListener {

    private ArrayList<Equipo> datos;
    private View.OnClickListener listener;

    /**
     * Constructor, en el recibimos los datos a manejar.
     *
     * @param datos
     */
    public AdaptadorEquipos(ArrayList<Equipo> datos) {
        this.datos = datos;
    }

    /**
     * Inflamos la vista a partir del layout correspondiente
     * y creamos y devolvemos un nuevo ViewHolder llamando al constructor de nuestra
     * clase EquiposViewHolder, pasandole dicha vista como parametro.
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @Override
    public EquiposViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_equipo, viewGroup, false);

        itemView.setOnClickListener(this);

        EquiposViewHolder manejador = new EquiposViewHolder(itemView);

        return manejador;
    }

    /**
     * Recuperamos el objeto equipo correspondiente
     * a la posición recibida y asignamos sus datos sobre el ViewHolder,
     * que también nos llega por parametro.
     *
     * @param equiposViewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(AdaptadorEquipos.EquiposViewHolder equiposViewHolder, int i) {
        Equipo item = datos.get(i);
        equiposViewHolder.bindEquipo(item);

    }

    /**
     * Devuelve el tamaño de la lista de datos
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return datos.size();
    }

    /**
     * Ejecuta el Listener si este no es NULL
     *
     * @param
     */
    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onClick(v);
        }

    }

    /**
     * Permite establecer un OnClickListener a la vista
     *
     * @param listener
     */
    public void setOnClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    /**
     * Nuestro Manejador
     */
    public static class EquiposViewHolder extends RecyclerView.ViewHolder {

        // Referencias a los elementos que vamos a actualizar
        private TextView tvNombreEquipo;
        private ImageView ivImagen;
        private TextView tvPuntosEquipo;

        /**
         * Constructpr, aquí referenciamos los elementos del listado
         *
         * @param itemView
         */
        public EquiposViewHolder(View itemView) {
            super(itemView);

            // Referencias a los elementos del listado
            tvNombreEquipo = itemView.findViewById(R.id.tvNombreEquipo);
            ivImagen = itemView.findViewById(R.id.ivImagenEquipo);
            tvPuntosEquipo = itemView.findViewById(R.id.tvPuntosEquipo);
        }

        /**
         * Se encarga de asignar los contenidos, usando las referencias anteriores
         * Es llamado desde onBindViewHolder
         *
         * @param item
         */
        public void bindEquipo(Equipo item) {

            tvNombreEquipo.setText(item.getNombre());
            tvPuntosEquipo.setText(String.valueOf(item.getPuntos()));
            ivImagen.setImageDrawable(item.getImagen());

        }
    }
}
