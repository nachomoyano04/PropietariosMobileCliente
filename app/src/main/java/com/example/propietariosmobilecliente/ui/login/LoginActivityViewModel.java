package com.example.propietariosmobilecliente.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.propietariosmobilecliente.MainActivity;
import com.example.propietariosmobilecliente.request.ApiCliente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel {
    private Context context;
    public LoginActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public void login(String correo, String password){
        if(!correo.isEmpty() && !password.isEmpty()){
            ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
            Call<String> llamada = api.login(correo, password);
            llamada.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        String token = response.body();
                        ApiCliente.guardarToken(context, "Bearer " + token);
                        Intent i = new Intent(context, MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(i);
                    }else{
                        Toast.makeText(context, "Usuario y/o contraseña incorrecta.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable throwable) {
                    Toast.makeText(context, "Error del servidor", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            Toast.makeText(context, "Debe llenar los campos", Toast.LENGTH_SHORT).show();
        }
    }

    public void recuperarPassword() {
        Intent i = new Intent(context, RecuperarPassActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getApplication().startActivity(i);
    }

}
