package com.example.propietariosmobilecliente.ui.inmuebles;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.propietariosmobilecliente.models.Inmueble;

public class InmuebleDetalleViewModel extends AndroidViewModel {
    public MutableLiveData<Inmueble> mInmueble;
    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inmueble> getMInmueble(){
        if(mInmueble == null){
            mInmueble = new MutableLiveData<>();
        }
        return mInmueble;
    }

    public void leerDatos(Bundle b){
        Inmueble i = (Inmueble) b.getSerializable("Inmueble");
        if(i != null){
            mInmueble.setValue(i);
        }
    }
}