package com.example.propietariosmobilecliente.ui.contratos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.models.Contrato;

import java.util.ArrayList;

public class ContratosAdapter extends RecyclerView.Adapter<ContratosAdapter.ViewHolderContratos> {
    private ArrayList<Contrato> contratos = new ArrayList<>();
    private LayoutInflater li;

    public ContratosAdapter(ArrayList<Contrato> contratos, LayoutInflater li) {
        this.contratos = contratos;
        this.li = li;
    }

    @NonNull
    @Override
    public ViewHolderContratos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.card_contratos, parent, false);
        return new ViewHolderContratos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderContratos holder, int position) {
        Contrato c = contratos.get(position);
        holder.tvPropietario.setText(c.getPropietario());
        holder.tvInquilino.setText(c.getInquilino().nombreYApellido());
        holder.tvFechaInicio.setText("Fecha inicio: "+c.getFechaInicio());
        holder.tvFechaFin.setText("Fecha fin: "+c.getFechaFin());
        holder.tvImporteContrato.setText("Importe contrato: $"+c.getImporteContrato());
        //implementar listener en card para mostrar detalles del contrato pasandole en el bundle el contrato c...
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putSerializable("Contrato", c);
                Navigation.findNavController(view).navigate(R.id.nav_contrato_detalles, b);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contratos.size();
    }

    public class ViewHolderContratos extends RecyclerView.ViewHolder{

        private TextView tvPropietario, tvInquilino, tvFechaInicio, tvFechaFin, tvImporteContrato;

        public ViewHolderContratos(@NonNull View itemView) {
            super(itemView);
            tvPropietario = itemView.findViewById(R.id.tvPropietario);
            tvInquilino = itemView.findViewById(R.id.tvInquilino);
            tvFechaInicio = itemView.findViewById(R.id.tvFechaInicio);
            tvFechaFin = itemView.findViewById(R.id.tvFechaFin);
            tvImporteContrato = itemView.findViewById(R.id.tvImporteContrato);
        }
    }
}
