package com.example.propietariosmobilecliente.ui.contratos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.propietariosmobilecliente.models.Contrato;
import com.example.propietariosmobilecliente.models.Inquilino;
import com.example.propietariosmobilecliente.request.ApiCliente;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContratosViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<ArrayList<Contrato>> mListaContratos;

    public ContratosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Contrato>> getMListaContratos(){
        if(mListaContratos == null){
            mListaContratos = new MutableLiveData<>();
        }
        return mListaContratos;
    }

    public void cargarLista(){
        ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
        api.getContratos(ApiCliente.getToken(context)).enqueue(new Callback<ArrayList<Contrato>>() {
            @Override
            public void onResponse(Call<ArrayList<Contrato>> call, Response<ArrayList<Contrato>> response) {
                if(response.isSuccessful()){
                    mListaContratos.setValue(response.body());
                }else{
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Contrato>> call, Throwable throwable) {
                Toast.makeText(context, "Error en el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}