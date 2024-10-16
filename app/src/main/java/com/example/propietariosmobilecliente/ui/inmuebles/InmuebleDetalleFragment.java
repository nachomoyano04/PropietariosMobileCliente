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
import android.widget.CompoundButton;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
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
                binding.tvDireccionDetInm.setText(i.getDireccion().toString());
                binding.tvPrecioDetInm.setText("$"+i.getPrecio());
                binding.tvAmbientesDetInm.setText(i.getCantidadAmbientes()+"");
                binding.tvMetrosDetInm.setText(i.getMetros2());
                binding.tvUsoDetInm.setText(i.getUso());
                binding.ivIconCocheraInmDet.setImageResource(R.drawable.not_cochera_icon);
                binding.ivIconMascotasInmDet.setImageResource(R.drawable.not_mascotas_icon);
                binding.ivIconPiscinaInmDet.setImageResource(R.drawable.not_piscina_icon);
                binding.tvTipoDetInm.setText(i.getTipo());
                binding.tvDescripcionDetInm.setText(i.getDescripcion());
                Glide.with(getContext())
                        .load(i.getUrlImagen())
                        .placeholder(R.drawable.ic_launcher_background)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.ivImagenDetInm);
            }
        });
        binding.switchDisponibleDetInm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                vm.cambiarDisponibilidadInmueble();
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