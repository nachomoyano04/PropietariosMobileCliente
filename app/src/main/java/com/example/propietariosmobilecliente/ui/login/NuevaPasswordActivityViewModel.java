package com.example.propietariosmobilecliente.ui.login;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.propietariosmobilecliente.request.ApiCliente;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NuevaPasswordActivityViewModel extends AndroidViewModel {

    private MutableLiveData<String> mMensaje;
    private Context context;

    public NuevaPasswordActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getMMensaje(){
        if(mMensaje == null){
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void guardarNuevaPassword(String token, String nuevaPassword){
        if(!nuevaPassword.isEmpty()){
            Toast.makeText(context, token, Toast.LENGTH_SHORT).show();
            ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
            api.nuevaPassword(token, nuevaPassword).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(context, response.body(), Toast.LENGTH_SHORT).show();
                    }else{
                        try {
                            Toast.makeText(context, response.errorBody().string(), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable throwable) {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            mMensaje.setValue("Ingrese una password por favor");
        }
    }

}
