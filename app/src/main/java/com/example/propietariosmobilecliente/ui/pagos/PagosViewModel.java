package com.example.propietariosmobilecliente.ui.pagos;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.propietariosmobilecliente.models.Pago;

import java.time.LocalDate;
import java.util.ArrayList;

public class PagosViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Pago>> mListaPagos;

    public PagosViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<ArrayList<Pago>> getMListaPagos(){
        if(mListaPagos == null){
            mListaPagos = new MutableLiveData<>();
        }
        return mListaPagos;
    }

    public void cargarListaPagos(){
        //aca consumiriamos la api para ver los pagos del contrato x del inmueble x del propietario logueado
        //---
        ArrayList<Pago> pagos = new ArrayList<>();
        pagos.add(new Pago(1, LocalDate.now(), 2000.));
        pagos.add(new Pago(2, LocalDate.now().plusDays(1), 3000.));
        pagos.add(new Pago(3, LocalDate.now().plusDays(1), 4000.));
        pagos.add(new Pago(4, LocalDate.now().plusDays(1), 5000.));
        mListaPagos.setValue(pagos);
    }

}