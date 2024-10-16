package com.example.propietariosmobilecliente.ui.perfil;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.propietariosmobilecliente.request.ApiCliente;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CambiarClaveViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> mMensaje;

    public CambiarClaveViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getMMensaje() {
        if (mMensaje == null) {
            mMensaje = new MutableLiveData<>();
        }
        return mMensaje;
    }

    public void leerMutable(String mensaje) {
        mMensaje.setValue(mensaje);
    }

    public void cambiarPassword(String password) {
        if (!password.isEmpty()) {
            ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
            api.cambiarPassword(ApiCliente.getToken(context), password).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        mMensaje.setValue(response.body());
                    } else {
                        mMensaje.setValue("Error en la respuesta");
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable throwable) {
                    mMensaje.setValue("Error en el servidor");
                }
            });
        } else {
            mMensaje.setValue("El campo es requerido");
        }
    }
}