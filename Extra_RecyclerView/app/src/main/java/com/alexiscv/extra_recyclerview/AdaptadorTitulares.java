package com.alexiscv.extra_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorTitulares extends RecyclerView.Adapter<AdaptadorTitulares.TitularesViewHolder> implements View.OnClickListener {

    private ArrayList<Titular> datos;
    private View.OnClickListener listener;

    /**
     * Constructor
     * Por el enviamos los datos que tenemos que manejar.
     *
     * @param datos
     */
    public AdaptadorTitulares(ArrayList<Titular> datos) {
        this.datos = datos;
    }

    /**
     * Inflamos la vista a partir del layout correspondiente
     * y crear y devolver un nuevo ViewHolder llamando al constructor de nuestra
     * clase TitularesViewHolder, pasandole dicha vista como parametro
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @Override
    public TitularesViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.listitem_titular, viewGroup, false);

        itemView.setOnClickListener(this);

        TitularesViewHolder manejador = new TitularesViewHolder(itemView);

        return manejador;
    }

    /**
     * Recuperamos el objeto titular correspondiente
     * a la posición recibida y asignamos sus datos sobre el ViewHolder,
     * que también nos llega por parametro.
     *
     * @param titularesViewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(TitularesViewHolder titularesViewHolder, int i) {
        Titular item = datos.get(i);
        titularesViewHolder.bindTitular(item);

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
    public void onClick(View view) {
        if (listener != null) {
            listener.onClick(view);
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
    public static class TitularesViewHolder extends RecyclerView.ViewHolder {

        // Referencias a los elementos que vamos a actualizar
        private TextView txtTitulo;
        private TextView txtSubtitulo;

        /**
         * Constructpr, aquí referenciamos los elementos del listado
         *
         * @param itemView
         */
        public TitularesViewHolder(View itemView) {
            super(itemView);

            // Referencias a los elementos del listado
            txtTitulo = (TextView) itemView.findViewById(R.id.LblTitulo);
            txtSubtitulo = (TextView) itemView.findViewById(R.id.LblSubTitulo);
        }


        /**
         * Se encarga de asignar los contenidos, usando las referencias anteriores
         *
         * @param t
         */
        public void bindTitular(Titular t) {
            txtTitulo.setText(t.getTitulo());
            txtSubtitulo.setText(t.getSubtitulo());
        }
    }
}