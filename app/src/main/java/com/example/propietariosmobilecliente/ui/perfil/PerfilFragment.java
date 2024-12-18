package com.example.propietariosmobilecliente.ui.perfil;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.databinding.FragmentPerfilBinding;
import com.example.propietariosmobilecliente.models.Propietario;
import com.example.propietariosmobilecliente.request.ApiCliente;

public class PerfilFragment extends Fragment {

    private FragmentPerfilBinding binding;
    private PerfilViewModel vm;
    private String avatar;

    public static PerfilFragment newInstance() {
        return new PerfilFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentPerfilBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(PerfilViewModel.class);
        vm.getMBoolean().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                binding.tvDniPerfil.setEnabled(aBoolean);
                binding.tvApellidoPerfil.setEnabled(aBoolean);
                binding.tvNombrePerfil.setEnabled(aBoolean);
                binding.tvTelefonoPerfil.setEnabled(aBoolean);
                binding.tvCorreoPerfil.setEnabled(aBoolean);
                binding.btnCambiarClave.setEnabled(!aBoolean);
                binding.btnPerfilEditarAvatar.setEnabled(!aBoolean);
                binding.btnCancelarEditarPerfil.setVisibility(aBoolean.compareTo(!aBoolean));
            }
        });

        vm.getMColor().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                binding.btnGuardarEditarPerfil.setBackgroundColor(getResources().getColor(integer));
            }
        });

        vm.getMText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.btnGuardarEditarPerfil.setText(s);
            }
        });

        vm.getMPropietario().observe(getViewLifecycleOwner(), new Observer<Propietario>() {
            @Override
            public void onChanged(Propietario p) {
                binding.tvDniPerfil.setText(p.getDni());
                binding.tvApellidoPerfil.setText(p.getApellido());
                binding.tvNombrePerfil.setText(p.getNombre());
                binding.tvTelefonoPerfil.setText(p.getTelefono());
                binding.tvCorreoPerfil.setText(p.getCorreo());
                Glide.with(getContext())
                        .load(ApiCliente.getUrlImagenAvatar()+p.getAvatar())
                        .placeholder(R.drawable.ic_launcher_background)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.ivAvatarPerfil);
                avatar = p.getAvatar();
            }
        });

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
                vm.setGuardar();
            }
        });
        binding.btnCambiarClave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_cambiar_clave);
            }
        });
        binding.btnPerfilEditarAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle b = new Bundle();
                b.putSerializable("avatar", avatar);
                Navigation.findNavController(view).navigate(R.id.nav_editar_avatar, b);
            }
        });
        binding.btnCancelarEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.setGuardar();
            }
        });

        vm.cargarDatos();

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(PerfilViewModel.class);
    }

}