package com.example.propietariosmobilecliente.ui.pagos;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.propietariosmobilecliente.R;

public class DetallePagoFragment extends Fragment {

    private DetallePagoViewModel mViewModel;

    public static DetallePagoFragment newInstance() {
        return new DetallePagoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_pago, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetallePagoViewModel.class);
        // TODO: Use the ViewModel
    }

}