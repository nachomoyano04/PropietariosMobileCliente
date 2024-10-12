package com.example.propietariosmobilecliente.ui.inmuebles;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.databinding.FragmentInmueblesBinding;
import com.example.propietariosmobilecliente.models.Inmueble;

import java.util.ArrayList;

public class InmueblesFragment extends Fragment {

    private InmueblesViewModel vm;
    private FragmentInmueblesBinding binding;

    public static InmueblesFragment newInstance() {
        return new InmueblesFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentInmueblesBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(InmueblesViewModel.class);
        vm.getMListaInmuebles().observe(getViewLifecycleOwner(), new Observer<ArrayList<Inmueble>>() {
            @Override
            public void onChanged(ArrayList<Inmueble> inmuebles) {
                InmueblesAdapter adapter = new InmueblesAdapter(inmuebles, inflater);
                GridLayoutManager grid = new GridLayoutManager(getContext(), 1, GridLayoutManager.VERTICAL, false);
                binding.listaInmuebles.setAdapter(adapter);
                binding.listaInmuebles.setLayoutManager(grid);
            }
        });
        vm.cargarLista();
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(InmueblesViewModel.class);
        // TODO: Use the ViewModel
    }

}