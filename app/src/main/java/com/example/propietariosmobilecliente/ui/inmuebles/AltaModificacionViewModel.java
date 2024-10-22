package com.example.propietariosmobilecliente.ui.inmuebles;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.propietariosmobilecliente.models.Inmueble;

public class AltaModificacionViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> mInmueble;

    public AltaModificacionViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inmueble> getMInmueble(){
        if(mInmueble == null){
            mInmueble = new MutableLiveData<>();
        }
        return mInmueble;
    }

    public void llenarCampos(Bundle b){
        boolean desdeEditar = (boolean) b.getSerializable("Editar");
        if(desdeEditar){
            mInmueble.setValue((Inmueble) b.getSerializable("Inmueble"));
        }
    }

}