package com.example.propietariosmobilecliente.ui.perfil;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.propietariosmobilecliente.request.ApiCliente;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarAvatarViewModel extends AndroidViewModel {

    public MutableLiveData<String> mAvatar;
    public Context context;

    public EditarAvatarViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getMAvatar(){
        if(mAvatar == null){
            mAvatar = new MutableLiveData<>();
        }
        return mAvatar;
    }


    public void recibirFoto(ActivityResult result) {
        if(result.getResultCode() == RESULT_OK){
            Intent data =result.getData();
            Uri uri = data.getData();
            mAvatar.setValue(uri.toString());
        }
    }

    public void setearAvatar(Bundle b) {
        String avatar = (String) b.getSerializable("avatar");
        mAvatar.setValue(avatar);
    }

    public void guardarAvatar(){
        ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
        api.editarAvatar(ApiCliente.getToken(context), mAvatar.getValue()).enqueue(new Callback<String>() {
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