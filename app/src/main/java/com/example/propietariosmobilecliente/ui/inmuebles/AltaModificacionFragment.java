package com.example.propietariosmobilecliente.ui.inmuebles;

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

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.databinding.FragmentAltaModificacionBinding;
import com.example.propietariosmobilecliente.models.Inmueble;

public class AltaModificacionFragment extends Fragment {

    private FragmentAltaModificacionBinding binding;
    private AltaModificacionViewModel vm;
//    private imagenU

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
        binding.btnGuardarEditAltaInmueble.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String calle = binding.etCalleAMInmueble.getText().toString();
                String altura = binding.etAlturaAMInmueble.getText().toString();
                String ciudad = binding.etCiudadAMInmueble.getText().toString();
                String tipo = binding.etTipoAMInmueble.getText().toString();
                String uso = binding.etUsoAMInmueble.getText().toString();
                String metros2 = binding.etMetros2AMInmueble.getText().toString();
                String descripcion = binding.etDescripcionAMInmueble.getText().toString();
                String ambientes = binding.etAmbientesAMInmueble.getText().toString();
                String precio = binding.etPrecioAMInmueble.getText().toString();
                boolean mascotas = binding.switchMascotasAMInmueble.isChecked();
                boolean cochera = binding.switchCocheraAMInmueble.isChecked();
                boolean piscina = binding.switchPiscinaAMInmueble.isChecked();
//                int avatar = binding.ivAvatarAMInmueble.
//                vm.guardarInmueble(calle, altura, ciudad, tipo, uso, metros2, descripcion, ambientes, precio, mascotas, cochera, piscina, avatar);
            }
        });
        vm.llenarCampos(getArguments());
        return binding.getRoot();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(AltaModificacionViewModel.class);
        // TODO: Use the ViewModel
    }

}