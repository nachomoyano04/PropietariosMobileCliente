package com.example.propietariosmobilecliente.ui.perfil;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.propietariosmobilecliente.request.ApiCliente;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditarAvatarViewModel extends AndroidViewModel {

    private MutableLiveData<String> mAvatar;
    private Context context;


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
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
                context.getContentResolver().takePersistableUriPermission (uri, Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            mAvatar.setValue(uri.toString());
        }
    }

    public void setearAvatar(Bundle b) {
        String avatar = (String) b.getSerializable("avatar");
        mAvatar.setValue("http://192.168.1.9:5203/img/avatar/"+avatar);
    }

    public void guardarAvatar() {
        Uri avatarUri = Uri.parse(mAvatar.getValue());
        try {
            InputStream inputStream = context.getContentResolver().openInputStream(avatarUri);
            String fileName = "avatar"+System.currentTimeMillis()+".jpg";
            File file = new File(context.getCacheDir(), fileName);
            FileOutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }
            outputStream.close();
            inputStream.close();

            RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), file);
            MultipartBody.Part avatar = MultipartBody.Part.createFormData("avatar", file.getName(), requestFile);

            ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
            api.editarAvatar(ApiCliente.getToken(context), avatar).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(context, response.body(), Toast.LENGTH_SHORT).show();
                    } else {
                        if(response.code() != 401){
                            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable throwable) {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception ex) {
            Toast.makeText(context, "Exception: " + ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}