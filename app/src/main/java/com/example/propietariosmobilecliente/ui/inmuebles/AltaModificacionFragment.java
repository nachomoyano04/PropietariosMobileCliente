package com.example.propietariosmobilecliente.ui.inmuebles;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.databinding.FragmentAltaModificacionBinding;

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
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(AltaModificacionViewModel.class);
        // TODO: Use the ViewModel
    }

}