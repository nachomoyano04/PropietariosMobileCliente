package com.example.propietariosmobilecliente.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.propietariosmobilecliente.MainActivity;

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
}
