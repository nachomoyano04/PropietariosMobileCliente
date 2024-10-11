package com.example.propietariosmobilecliente.ui.inicio;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class InicioViewModel extends AndroidViewModel {
    private MutableLiveData<Mapa> //Implementar permisos de google maps
    public InicioViewModel(@NonNull Application application) {
        super(application);
    }


}