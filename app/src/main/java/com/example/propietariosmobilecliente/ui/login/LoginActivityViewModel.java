package com.example.propietariosmobilecliente.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

    public void verificarDatos(String correo, String password){
        //luego verificar que el correo y password devuelvan un usuario
        if(true){
            //navegar al fragment de inicio
            Intent i = new Intent(context, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }else{
            Toast.makeText(context, "Correo y/o password incorrectos", Toast.LENGTH_SHORT).show();
        }
    }

    public void login(String correo, String password){
        ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria();
        Call<String> llamada = api.login(correo, password);
        llamada.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    String token = response.body();
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
    }

}
