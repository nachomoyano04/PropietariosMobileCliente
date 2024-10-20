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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.databinding.FragmentAltaModificacionBinding;
import com.example.propietariosmobilecliente.models.Inmueble;

public class AltaModificacionFragment extends Fragment {

    private FragmentAltaModificacionBinding binding;
    private AltaModificacionViewModel vm;

    public static AltaModificacionFragment newInstance() {
        return new AltaModificacionFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentAltaModificacionBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(AltaModificacionViewModel.class);
        vm.getMInmueble().observe(getViewLifecycleOwner(), new Observer<Inmueble>() {
            @Override
            public void onChanged(Inmueble inmueble) {
                binding.etCalleAMInmueble.setText(inmueble.getDireccion().getCalle());
                binding.etAlturaAMInmueble.setText(inmueble.getDireccion().getAltura());
                binding.etCiudadAMInmueble.setText(inmueble.getDireccion().getCiudad());
                binding.etTipoAMInmueble.setText(inmueble.getTipo());
                binding.etUsoAMInmueble.setText(inmueble.getUso());
                binding.etMetros2AMInmueble.setText(inmueble.getMetros2());
                binding.etDescripcionAMInmueble.setText(inmueble.getDescripcion());
                binding.etAmbientesAMInmueble.setText(inmueble.getCantidadAmbientes()+"");
                binding.etPrecioAMInmueble.setText(inmueble.getPrecio()+"");
                binding.switchMascotasAMInmueble.setChecked(inmueble.isMascotas());
                binding.switchCocheraAMInmueble.setChecked(inmueble.isCochera());
                binding.switchPiscinaAMInmueble.setChecked(inmueble.isPiscina());
                Glide.with(getContext())
                        .load(inmueble.getUrlImagen())
                        .placeholder(R.drawable.ic_launcher_background)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.ivAvatarAMInmueble);
            }
        });
        vm.llenarCampos(getArguments());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(AltaModificacionViewModel.class);
        // TODO: Use the ViewModel
    }

}