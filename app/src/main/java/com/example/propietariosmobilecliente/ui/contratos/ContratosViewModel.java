package com.example.propietariosmobilecliente.ui.contratos;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.propietariosmobilecliente.models.Contrato;
import com.example.propietariosmobilecliente.models.Inquilino;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class ContratosViewModel extends AndroidViewModel {
    private MutableLiveData<ArrayList<Contrato>> mListaContratos;

    public ContratosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Contrato>> getMListaContratos(){
        if(mListaContratos == null){
            mListaContratos = new MutableLiveData<>();
        }
        return mListaContratos;
    }

    public void cargarLista(){
        ArrayList<Contrato> listaContratos = new ArrayList<>();
        listaContratos.add(new Contrato("Perez gabriel", new Inquilino("123", "Mariana", "Tinelli", "Derqui 9","255312322", "maria@gmail"), LocalDate.now(), LocalDate.of(2024, 10, 24), 90000.));
        listaContratos.add(new Contrato("Perez cristian", new Inquilino("456", "Alexis",  "MacAllister", "Europa 123", "41412321312", "colorado@gmail"), LocalDate.now(), LocalDate.of(2024, 9, 24), 40000.));
        listaContratos.add(new Contrato("Perez Ana luz", new Inquilino("789", "Julieta", "Benitez", "Asia 5", "542334332", "bebelo@gmail"), LocalDate.now(), LocalDate.of(2024, 11, 24), 120000.));
        mListaContratos.setValue(listaContratos);
    }
}