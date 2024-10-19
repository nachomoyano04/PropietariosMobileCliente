package com.example.propietariosmobilecliente.ui.contratos;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.databinding.FragmentDetalleContratoBinding;
import com.example.propietariosmobilecliente.models.Contrato;

public class DetalleContratoFragment extends Fragment {
    private FragmentDetalleContratoBinding binding;
    private DetalleContratoViewModel vm;

    public static DetalleContratoFragment newInstance() {
        return new DetalleContratoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDetalleContratoBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(DetalleContratoViewModel.class);
        vm.getMContrato().observe(getViewLifecycleOwner(), new Observer<Contrato>() {
            @Override
            public void onChanged(Contrato c) {
                binding.tvInquilinoDetallesContrato.setText("Inquilino: "+c.getInquilino().nombreYApellido());
                binding.tvFechaInicioDetalleContrato.setText("Fecha de inicio: "+c.getFechaInicio().toLocalDate());
                binding.tvFechaFinDetalleContrato.setText("Fecha de fin: "+c.getFechaFin().toLocalDate());
                binding.tvMontoDetallesContrato.setText("Monto: $"+c.getMonto());
            }
        });
        vm.recibirContrato(getArguments());
        binding.btnVerPagos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.verPagos(view);
            }
        });
        binding.btnDetalleInquilino.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.detalleInquilino(view);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(DetalleContratoViewModel.class);
        // TODO: Use the ViewModel
    }

}