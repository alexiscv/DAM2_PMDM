package com.alexiscv.t4ej21_listviewcomplejas_recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorElementos extends RecyclerView.Adapter<AdaptadorElementos.ElementosViewHolder> {

    // Como atributos
    // Un Listener y los Datos
    private View.OnClickListener listener;
    private ArrayList<String> listaDatos;

    /**
     * Constructor
     *
     * @param listaDatos
     */
    public AdaptadorElementos(ArrayList<String> listaDatos) {
        this.listaDatos = listaDatos;

    }


    /**
     * Permite establecer un ClickListener
     *
     * @param clickListener
     */
    public void setItemClickListener(View.OnClickListener clickListener) {
        this.listener = clickListener;

    }

    /**
     * Encargado de crear los nuevos objetos ViewHolder necesarios para los
     * elementos de la colección.
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @Override
    public ElementosViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recyclerview_layout, viewGroup, false);

        ElementosViewHolder elementosViewHolder = new ElementosViewHolder(itemView);

        itemView.setOnClickListener(listener);

        return elementosViewHolder;
    }

    /**
     * Encargado de actualizar los datos de un ViewHolder ya existente.
     *
     * @param elementosViewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(ElementosViewHolder elementosViewHolder, int i) {

        // Recuperamos el Objeto de la posición recibida
        String s = listaDatos.get(i);

        // Y asignamos sus datos al ViewHolder
        elementosViewHolder.bindElementos(s);

    }

    /**
     * Indica el número de elementos de la colección de datos
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    /**
     * Nuestro ViewHolder para reutilizar los Views y no crear un ciento
     */
    public static class ElementosViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTexto;

        public ElementosViewHolder(View itemView) {
            super(itemView);

            tvTexto = itemView.findViewById(R.id.tvTexto);
        }

        public void bindElementos(String s) {
            tvTexto.setText(s);

        }
    }
}
