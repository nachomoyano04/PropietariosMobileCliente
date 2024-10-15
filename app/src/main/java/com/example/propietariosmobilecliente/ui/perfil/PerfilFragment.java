package com.example.propietariosmobilecliente.ui.perfil;

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
import com.example.propietariosmobilecliente.databinding.FragmentPerfilBinding;
import com.example.propietariosmobilecliente.models.Propietario;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel vm;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(PerfilViewModel.class);
        vm.getMPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario p) {
                binding.tvDniPerfil.setText(p.getDni());
                binding.tvApellidoPerfil.setText(p.getApellido());
                binding.tvNombrePerfil.setText(p.getNombre());
                binding.tvTelefonoPerfil.setText(p.getTelefono());
                binding.tvCorreoPerfil.setText(p.getCorreo());
                //logica para buscar el image resource con el name file que viene desde la api...
                //--
                //--
                binding.ivAvatarPerfil.setImageResource(R.drawable.ic_launcher_background);
            }
        });
        vm.cargarDatos();
        binding.btnGuardarEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dni = binding.tvDniPerfil.getText().toString();
                String apellido = binding.tvApellidoPerfil.getText().toString();
                String nombre = binding.tvNombrePerfil.getText().toString();
                String telefono = binding.tvTelefonoPerfil.getText().toString();
                String correo = binding.tvCorreoPerfil.getText().toString();
                Propietario p = new Propietario(-1, dni, apellido, nombre, telefono, correo, "", null,true);
                vm.guardarDatos(p);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(PerfilViewModel.class);
    }

}