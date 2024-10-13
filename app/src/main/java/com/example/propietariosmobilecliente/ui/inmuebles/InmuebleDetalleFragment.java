package com.example.propietariosmobilecliente.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.databinding.FragmentInmuebleDetalleBinding;
import com.example.propietariosmobilecliente.models.Inmueble;

public class InmuebleDetalleFragment extends Fragment {
    private FragmentInmuebleDetalleBinding binding;
    private InmuebleDetalleViewModel vm;

    public static InmuebleDetalleFragment newInstance() {
        return new InmuebleDetalleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInmuebleDetalleBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);
        vm.getMInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble i) {
                binding.switchDisponibleDetInm.setChecked(i.isDisponible());
                binding.tvDireccionDetInm.setText(i.getDireccion());
                binding.tvAmbientesDetInm.setText(i.getAmbientes()+"");
                binding.tvMetrosDetInm.setText(i.getMetros2());
                binding.tvUsoDetInm.setText(i.getUso());
                binding.tvTipoDetInm.setText(i.getTipo());
                binding.tvDescripcionDetInm.setText(i.getDescripcion());
                binding.ivImagenDetInm.setImageResource(i.getImagen());
            }
        });
        vm.leerDatos(getArguments());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(InmuebleDetalleViewModel.class);
        // TODO: Use the ViewModel
    }

}