package com.example.propietariosmobilecliente.ui.inmuebles;

import static android.app.Activity.RESULT_OK;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.propietariosmobilecliente.models.Inmueble;
import com.example.propietariosmobilecliente.request.ApiCliente;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AltaModificacionViewModel extends AndroidViewModel {

    private MutableLiveData<Inmueble> mInmueble;
    private MutableLiveData<String> mAvatar;
    private Context context;

    public AltaModificacionViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Inmueble> getMInmueble(){
        if(mInmueble == null){
            mInmueble = new MutableLiveData<>();
        }
        return mInmueble;
    }

    public LiveData<String> getMAvatar(){
        if(mAvatar == null){
            mAvatar = new MutableLiveData<>();
        }
        return mAvatar;
    }

    public void setearAvatar(String urlImagen) {
        if(urlImagen.startsWith("http")){
            mAvatar.setValue(urlImagen);
        }else{
            mAvatar.setValue("http://192.168.1.9:5203/img/inmueble/"+urlImagen);
        }
    }

    public void llenarCampos(Bundle b){
        boolean desdeEditar = (boolean) b.getSerializable("Editar");
        if(desdeEditar){
            mInmueble.setValue((Inmueble) b.getSerializable("Inmueble"));
        }
    }

    public void recibirFoto(ActivityResult o) {
        if(o.getResultCode() == RESULT_OK){
            Uri uri = o.getData().getData();
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR2) {
                context.getContentResolver().takePersistableUriPermission (uri, Intent.FLAG_GRANT_READ_URI_PERMISSION|Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            }
            mAvatar.setValue(uri.toString());
        }
    }

    public void guardarInmueble(Bundle b, String calle, String altura, String ciudad, String tipo, String uso, String metros2, String descripcion, String ambientes, String precio, boolean mascotas, boolean cochera, boolean piscina) {
        boolean desdeEditar = (boolean) b.getSerializable("Editar");
        try {
            int cantidadAmbientes = Integer.parseInt(ambientes);
            double precioInmueble = Double.parseDouble(precio);
            int alturaDire = Integer.parseInt(altura);
            ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
            if(!desdeEditar){
                api.crearInmueble(ApiCliente.getToken(context), tipo, metros2, uso, cantidadAmbientes, precioInmueble,"imagen", descripcion, cochera, piscina, false, mascotas, calle, alturaDire, ciudad).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            int id = Integer.parseInt(response.body());
                            Toast.makeText(context, "IdCreado: "+id, Toast.LENGTH_SHORT).show();
                            subirImagen(id);
                        }else{
                            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable throwable) {
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("ErrorFailure", throwable.getMessage());
                    }
                });
            }else{
                Inmueble i = (Inmueble) b.getSerializable("Inmueble");
                api.editarInmueble(ApiCliente.getToken(context), i.getIdInmueble(), tipo, metros2, uso, cantidadAmbientes, precioInmueble,"imagen", descripcion, cochera, piscina, false, mascotas, calle, alturaDire, ciudad).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(context, response.body(), Toast.LENGTH_SHORT).show();
                            if(!mAvatar.getValue().startsWith("http")){
                                subirImagen(i.getIdInmueble());
                            }
                        }else{
                            Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable throwable) {
                        Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }catch (NumberFormatException ex){
            Toast.makeText(context, "los campos precio, ambientes y altura deben ser numeros", Toast.LENGTH_SHORT).show();
        }
    }

    private void subirImagen(int id) {
        try{
            InputStream inputStream = context.getContentResolver().openInputStream(Uri.parse(mAvatar.getValue()));
            String fileName = "inmueble"+System.currentTimeMillis()+".jpg";
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
            MultipartBody.Part imagen = MultipartBody.Part.createFormData("imagen", file.getName(), requestFile);
            ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
            api.editarImagenInmueble(ApiCliente.getToken(context), imagen, id).enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        Toast.makeText(context, response.body(), Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable throwable) {
                    Toast.makeText(context, throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }catch (Exception ex){
            Toast.makeText(context, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}