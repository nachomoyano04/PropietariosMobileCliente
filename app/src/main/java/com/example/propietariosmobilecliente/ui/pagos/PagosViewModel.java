package com.example.propietariosmobilecliente.ui.pagos;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.propietariosmobilecliente.models.Pago;
import com.example.propietariosmobilecliente.request.ApiCliente;

import java.time.LocalDate;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PagosViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<ArrayList<Pago>> mListaPagos;

    public PagosViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Pago>> getMListaPagos(){
        if(mListaPagos == null){
            mListaPagos = new MutableLiveData<>();
        }
        return mListaPagos;
    }

    public void cargarListaPagos(Bundle b){
        int idContrato = (int) b.getSerializable("IdContrato");
        ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
        api.getPagosPorContrato(ApiCliente.getToken(context), idContrato).enqueue(new Callback<ArrayList<Pago>>() {
            @Override
            public void onResponse(Call<ArrayList<Pago>> call, Response<ArrayList<Pago>> response) {
                if(response.isSuccessful()){
                    mListaPagos.setValue(response.body());
                }else{
                    if(response.code() != 401){
                        Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Pago>> call, Throwable throwable) {
                Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}