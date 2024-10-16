package com.example.propietariosmobilecliente.ui.inmuebles;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
        Glide.with(holder.itemView)
                .load(i.getUrlImagen())
                .placeholder(R.drawable.ic_launcher_background)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.ivFotoInmueble);
        holder.tvDireccion.setText(i.getDireccion().toString());
        holder.tvTipo.setText(i.getTipo());
        holder.tvPrecio.setText("$"+i.getPrecio());
        //setear escuchador de click para la tarjeta y que navegue al detalleInmueble con el bundle de inmueble en el intent...
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putSerializable("Inmueble", i);
                Navigation.findNavController(view).navigate(R.id.nav_inmueble_detalles, b);
            }
        });
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
