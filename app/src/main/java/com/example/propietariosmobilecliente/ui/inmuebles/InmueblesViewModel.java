package com.example.propietariosmobilecliente.ui.inmuebles;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.models.Inmueble;

import java.util.ArrayList;

public class InmueblesViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Inmueble>> mListaInmuebles;

    public InmueblesViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Inmueble>> getMListaInmuebles(){
        if(mListaInmuebles == null){
            mListaInmuebles = new MutableLiveData<>();
        }
        return mListaInmuebles;
    }

    public void cargarLista(){
        //logica para cargar lista cuando consumamos la api obteniendo los inmuebles del propietario logueado.
        // - ArrayList<Inmueble> listaInmuebles = ...
        // -
        // -
        //Logica harcodeando
        ArrayList<Inmueble> listaInmuebles = new ArrayList<>();
        listaInmuebles.add(new Inmueble("Mitre 2002", R.drawable.keyicon, "Departamento", 30000., "20", true, 1, "Residencial", "Depto en nueva"));
        listaInmuebles.add(new Inmueble("Mitre 2003", R.drawable.keyicon, "Casa", 320000., "120", false, 2, "Comercial", "Casa para poner local de ropa"));
        listaInmuebles.add(new Inmueble("Mitre 2004", R.drawable.keyicon, "Campo", 5670000., "10", true, 3, "Comercial", "Campo hermoso para las carreras"));
        listaInmuebles.add(new Inmueble("Mitre 2005", R.drawable.keyicon, "Hotel", 7440000., "21", false, 4, "Residencial", "Hotel en Las Vegas Nevada"));
        mListaInmuebles.setValue(listaInmuebles);
    }
}