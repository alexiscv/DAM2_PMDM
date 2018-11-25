package com.alexiscv.t4ejem_recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdaptadorUsuarios extends RecyclerView.Adapter<AdaptadorUsuarios.UsuariosViewHolder> {

    private List<Usuario> listaUsuarios;
    private View.OnClickListener listener;

    /**
     * COnstructor
     * @param listaUsuarios
     */
    public AdaptadorUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;

    }

    /**
     * Nos permite inflar un vista a partir del layout correspondiente
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public UsuariosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Referenciamos e inflamos el layout
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_usuarios, parent, false);

        // Se lo pasamos al manejador
        UsuariosViewHolder usuariosViewHolder = new UsuariosViewHolder(itemView);

        // Implementamos el onClickListener
        itemView.setOnClickListener(listener);

        // Revolvemos el manejador
        return usuariosViewHolder;

    }

    /**
     * Recuperamos un objeto Usuario
     * correspontiente a la posición recibida por parametro
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull UsuariosViewHolder holder, int position) {
        Usuario usuario = listaUsuarios.get(position);
        holder.bindUsuario(usuario);

    }

    /**
     * Retorna el tamaño de la lista de datos
     * @return
     */
    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    /**
     * Es el contenedor
     * Sirve para referenciar los elementos XML con JAVA de manera optimizada
     * como vimos en las ListView optimizadas, además, también nos permite
     * recoger los valores.
     */
    public static class UsuariosViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNombre, tvApellidos, tvDireccion;

        public UsuariosViewHolder(View itemView) {
            super(itemView);

            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvApellidos = itemView.findViewById(R.id.tvApellidos);
            tvDireccion = itemView.findViewById(R.id.tvDireccion);
        }

        public void bindUsuario(Usuario usuario) {
            tvNombre.setText(usuario.getNombre());
            tvApellidos.setText(usuario.getApellidos());
            tvDireccion.setText(usuario.getDireccion());

        }

    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;

    }
}
