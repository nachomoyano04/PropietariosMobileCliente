package com.example.propietariosmobilecliente.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.propietariosmobilecliente.request.ApiCliente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecuperarPassActivityViewModel extends AndroidViewModel {

    private MutableLiveData<String> mMensaje;
    private Context context;

    public RecuperarPassActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getMMensaje(){
        if(mMensaje == null){
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void enviarCorreo(String correo){
        if(!correo.isEmpty()){
            ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
            api.recuperarPassword(correo).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        mMensaje.postValue(response.body());
                    }else{
                        mMensaje.postValue(response.message());
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable throwable) {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            mMensaje.postValue("Debe ingresar un correo");
        }
    }

}
