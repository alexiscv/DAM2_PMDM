package com.alexiscv.t5ejem_fragmentsestaticos;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdaptadorFilosofos extends RecyclerView.Adapter<AdaptadorFilosofos.FilosofosViewHolder> {

    // Como atributos
    // Un Listener y los Datos
    private View.OnClickListener listener;
    private ArrayList<Filosofo> listaDatos;

    /**
     * Constructor
     * Al crear el Adaptador se le pasará obligatoriamente los datos, el Listener no es obligatorio
     *
     * @param listaDatos
     */
    public AdaptadorFilosofos(ArrayList<Filosofo> listaDatos) {
        this.listaDatos = listaDatos;
    }

    /**
     * Permite establecer un clickListener
     *
     * @param clickListener
     */
    public void setItemClickListener(View.OnClickListener clickListener) {
        this.listener = clickListener;
    }

    /**
     * Encargado de crear los nuevos objetos ViewHolder necesarios para
     * los elementos de la colección.
     *
     * @param viewGroup
     * @param i
     * @return
     */
    @NonNull
    @Override
    public FilosofosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflamos la vista
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.filosofo_recyclerview_item, viewGroup, false);

        // Creamos un Obj FilosofosViewHolder y le pasamos la vista inflada.
        FilosofosViewHolder filosofosViewHolder = new FilosofosViewHolder(itemView);

        // Le asignamos un Listener a la vista
        itemView.setOnClickListener(listener);

        // devolvemos el viewHolder que contiene la vista
        return filosofosViewHolder;
    }

    /**
     * Encargado de actualizar los datos de un ViewHolder ya existente
     *
     * @param filosofosViewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(@NonNull FilosofosViewHolder filosofosViewHolder, int i) {

        // Recuperamos el filosofo necesario de la lisdaDatos
        Filosofo filosofo = listaDatos.get(i);

        // Y asignamos sus datos al ViewHolder
        // llamando al método que hemos creado en nuestro FilosofosViewHolder
        filosofosViewHolder.bindElementos(filosofo);

    }

    /**
     * Devuelve el número total de elementos
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return listaDatos.size();
    }

    /**
     * Nuestro manejador, para reutilizar los Views y no sobrecargar la memoria
     * debe extender de RecyclerView.ViewHolder
     */
    public static class FilosofosViewHolder extends RecyclerView.ViewHolder {

        // Atributos
        // Elementos que queremos controlar con el manejador
        private ImageView imagenFilosofo;
        private TextView nombreFilosofo;

        /**
         * Constructor
         *
         * @param itemView
         */
        public FilosofosViewHolder(@NonNull View itemView) {
            super(itemView);

            // Referenciamos los elementos que manejara nuestro manejador
            nombreFilosofo = itemView.findViewById(R.id.tvNombreFilosofo);
            imagenFilosofo = itemView.findViewById(R.id.ivImagenFilosofo);
        }

        /**
         * Enlazar elementos
         * Recibe los datos que tenemos que vincular a la View
         */
        public void bindElementos(Filosofo filosofo) {

            // Cargamos los valores del filosofo en los elementos XML
            nombreFilosofo.setText(filosofo.getNombre());
            imagenFilosofo.setImageResource(filosofo.getIdFoto());

        }
    }
}
