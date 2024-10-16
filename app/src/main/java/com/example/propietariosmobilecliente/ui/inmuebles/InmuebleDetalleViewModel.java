package com.example.propietariosmobilecliente.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.propietariosmobilecliente.models.Inmueble;
import com.example.propietariosmobilecliente.request.ApiCliente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleDetalleViewModel extends AndroidViewModel {
    public MutableLiveData<Inmueble> mInmueble;
    public Context context;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
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

    public void cambiarDisponibilidadInmueble() {
        ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
        api.cambiarDisponilidad(ApiCliente.getToken(context), mInmueble.getValue().getIdInmueble()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, response.body(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Error al cambiar la disponibilidad", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Toast.makeText(context, "Error en el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}