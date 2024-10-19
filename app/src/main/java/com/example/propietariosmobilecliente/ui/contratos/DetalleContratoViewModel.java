package com.example.propietariosmobilecliente.ui.contratos;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.models.Contrato;

public class DetalleContratoViewModel extends AndroidViewModel {
    private MutableLiveData<Contrato> mContrato;

    public DetalleContratoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Contrato> getMContrato(){
        if(mContrato == null){
            mContrato = new MutableLiveData<>();
        }
        return mContrato;
    }

    public void recibirContrato(Bundle b){
        Contrato c = (Contrato) b.getSerializable("Contrato");
        if(c != null){
            mContrato.setValue(c);
        }
    }

    public void verPagos(View view){
        Bundle p = new Bundle();
        p.putSerializable("IdContrato", mContrato.getValue().getIdContrato());
        Navigation.findNavController(view).navigate(R.id.nav_pagos, p);
    }

    public void detalleInquilino(View view){
        Bundle b = new Bundle();
        b.putSerializable("Inquilino", mContrato.getValue().getInquilino());
        Navigation.findNavController(view).navigate(R.id.nav_inquilino_detalles, b);
    }

    public void detalleInmueble(View view){
        Bundle i = new Bundle();
        i.putSerializable("Inmueble", mContrato.getValue().getInmueble());
        Navigation.findNavController(view).navigate(R.id.nav_inmueble_detalles, i);
    }
}