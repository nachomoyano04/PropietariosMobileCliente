package com.example.propietariosmobilecliente.ui.inmuebles;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.models.Inmueble;

import java.util.ArrayList;

public class InmueblesAdapter extends RecyclerView.Adapter<InmueblesAdapter.ViewHolderInmueble> {

    private ArrayList<Inmueble>inmuebles = new ArrayList<Inmueble>();
    private LayoutInflater li;

    public InmueblesAdapter(ArrayList<Inmueble>inmuebles, LayoutInflater li){
        this.inmuebles = inmuebles;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderInmueble onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.card_inmuebles, parent, false);
        return new ViewHolderInmueble(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderInmueble holder, int position) {
        Inmueble i = inmuebles.get(position);
        holder.ivFotoInmueble.setImageResource(i.getImagen());
        holder.tvDireccion.setText(i.getDireccion());
        holder.tvTipo.setText(i.getTipo());
        holder.tvPrecio.setText("$"+i.getPrecio());
        //setear escuchador de click para la tarjeta y que navegue al detalleInmueble con el bundle de inmueble en el intent...
    }

    @Override
    public int getItemCount() {
        return inmuebles.size();
    }

    public class ViewHolderInmueble extends RecyclerView.ViewHolder{

        private TextView tvDireccion, tvTipo, tvPrecio;
        private ImageView ivFotoInmueble;

        public ViewHolderInmueble(@NonNull View itemView) {
            super(itemView);
            tvDireccion = itemView.findViewById(R.id.tvDireccionInmueble);
            tvTipo = itemView.findViewById(R.id.tvTipoInmueble);
            tvPrecio = itemView.findViewById(R.id.tvPrecioInmueble);
            ivFotoInmueble = itemView.findViewById(R.id.ivFotoInmueble);
        }
    }
}