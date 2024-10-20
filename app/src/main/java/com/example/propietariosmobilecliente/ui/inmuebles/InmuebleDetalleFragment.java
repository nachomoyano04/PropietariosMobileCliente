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
        vm.getMMascotas().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.ivIconMascotasInmDet.setImageResource(integer);
            }
        });
        vm.getMPiscina().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.ivIconPiscinaInmDet.setImageResource(integer);
            }
        });
        vm.getMCochera().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.ivIconCocheraInmDet.setImageResource(integer);
            }
        });
        vm.getMInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble i) {
                binding.switchDisponibleDetInm.setChecked(i.isDisponible());
                binding.tvDireccionDetInm.setText(i.getDireccion().toString());
                binding.tvPrecioDetInm.setText("$"+i.getPrecio());
                binding.tvAmbientesDetInm.setText(i.getCantidadAmbientes()+"");
                binding.tvMetrosDetInm.setText(i.getMetros2());
                binding.tvUsoDetInm.setText(i.getUso());
                vm.setearImagenCochera(i.isCochera());
                vm.setearImagenPiscina(i.isPiscina());
                vm.setearImagenMascotas(i.isMascotas());
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
        binding.btnEditarInmuebleDetInm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.irAEditarInmueble(view);
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