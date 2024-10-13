package com.example.propietariosmobilecliente.ui.inquilino;

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
import com.example.propietariosmobilecliente.databinding.FragmentInquilinoBinding;
import com.example.propietariosmobilecliente.models.Inquilino;

public class InquilinoFragment extends Fragment {

    private FragmentInquilinoBinding binding;
    private InquilinoViewModel vm;

    public static InquilinoFragment newInstance() {
        return new InquilinoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInquilinoBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(InquilinoViewModel.class);
        vm.getMInquilino().observe(getViewLifecycleOwner(), new Observer<Inquilino>() {
            @Override
            public void onChanged(Inquilino i) {
                binding.tvNombreDetInqui.setText(i.getNombre());
                binding.tvApellidoDetInqui.setText(i.getApellido());
                binding.tvDniDetInqui.setText(i.getDni());
                binding.tvDireccionDetInqui.setText(i.getDireccion());
                binding.tvTelefonoDetInqui.setText(i.getTelefono());
                binding.tvCorreoDetInqui.setText(i.getCorreo());
            }
        });
        vm.cargarInquilino(getArguments());
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(InquilinoViewModel.class);
    }

}