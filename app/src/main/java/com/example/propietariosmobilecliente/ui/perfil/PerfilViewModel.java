package com.example.propietariosmobilecliente.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.propietariosmobilecliente.models.Propietario;
import com.example.propietariosmobilecliente.request.ApiCliente;
import com.google.android.gms.common.api.Api;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    public Context context;
    public MutableLiveData<Propietario> mPropietario;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Propietario> getMPropietario(){
        if(mPropietario == null){
            mPropietario = new MutableLiveData<>();
        }
        return mPropietario;
    }

    public void cargarDatos(){
        ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
        Call<Propietario> getPropietario = api.getPropietario(ApiCliente.getToken(context));
        getPropietario.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if(response.isSuccessful()){
                    if(response.body() != null){
                        mPropietario.setValue(response.body());
                    }else{
                        Toast.makeText(context, "No existe informacion de este propietario", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Error al obtener el propietario", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable throwable) {
                Toast.makeText(context, "Error en la api...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void guardarDatos(Propietario p){
        ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
        api.editarPropietario(ApiCliente.getToken(context), p.getDni(), p.getApellido(), p.getNombre(), p.getTelefono(), p.getCorreo()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, response.body(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Toast.makeText(context, "Error en el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}