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

import com.example.propietariosmobilecliente.MainActivity;
import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.models.Propietario;
import com.example.propietariosmobilecliente.request.ApiCliente;
import com.google.android.gms.common.api.Api;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {
    private Context context;
    private MutableLiveData<Propietario> mPropietario;
    private MutableLiveData<Boolean> mBoolean = new MutableLiveData<>(false);
    private MutableLiveData<String> mText = new MutableLiveData<>("Editar campos");
    private MutableLiveData<Integer> mColor = new MutableLiveData<>(R.color.primary);

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

    public LiveData<Boolean> getMBoolean(){
        return mBoolean;
    }

    public LiveData<String> getMText(){
        return mText;
    }

    public LiveData<Integer> getMColor(){
        return mColor;
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
                        if(response.code() != 401){
                            Toast.makeText(context, "No existe informacion de este propietario", Toast.LENGTH_SHORT).show();
                        }
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
        if(mBoolean.getValue()){
            ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
            api.editarPropietario(ApiCliente.getToken(context), p.getDni(), p.getApellido(), p.getNombre(), p.getTelefono(), p.getCorreo()).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, response.body(), Toast.LENGTH_SHORT).show();
                    } else {
                        if(response.code() != 401){
                            try {
                                String errorBody = response.errorBody().string();
                                Toast.makeText(context, "Error: "+errorBody, Toast.LENGTH_SHORT).show();
                            } catch (IOException e) {
                                Log.e("HTTP_ERROR", "Error al procesar la respuesta", e);
                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<String> call, Throwable throwable) {
                    Toast.makeText(context, "Error en el servidor", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void setGuardar() {
        if(mBoolean.getValue()){
            mBoolean.setValue(false);
            mText.setValue("Editar campos");
            mColor.setValue(R.color.primary);
        }else{
            mBoolean.setValue(true);
            mText.setValue("Guardar");
            mColor.setValue(R.color.success);
        }
    }
}