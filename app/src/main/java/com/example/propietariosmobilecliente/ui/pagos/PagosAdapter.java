package com.example.propietariosmobilecliente.ui.pagos;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.models.Pago;

import java.util.ArrayList;

public class PagosAdapter extends RecyclerView.Adapter<PagosAdapter.ViewHolderPagos> {

    private ArrayList<Pago> pagos = new ArrayList<Pago>();
    private LayoutInflater li;

    @NonNull
    @Override
    public ViewHolderPagos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = li.inflate(R.layout.card_pagos, parent, false);
        return new ViewHolderPagos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPagos holder, int position) {
        Pago p = pagos.get(position);
        holder.tvNumeroPago.setText("Numero de pago: "+p.getNumeroPago());
        holder.tvFechaPago.setText("Fecha de pago: "+p.getFechaPago());
        holder.tvImportePago.setText("Importe: $"+p.getImportePago());
    }

    @Override
    public int getItemCount() {
        return pagos.size();
    }

    public class ViewHolderPagos extends RecyclerView.ViewHolder{

        private TextView tvNumeroPago, tvFechaPago, tvImportePago;

        public ViewHolderPagos(@NonNull View itemView) {
            super(itemView);
            tvNumeroPago = itemView.findViewById(R.id.tvNumeroPago);
            tvFechaPago = itemView.findViewById(R.id.tvFechaPago);
            tvImportePago = itemView.findViewById(R.id.tvImportePago);
        }
    }
}
