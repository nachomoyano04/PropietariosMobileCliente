package com.example.propietariosmobilecliente.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.navigation.Navigation;

import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.models.Inmueble;
import com.example.propietariosmobilecliente.request.ApiCliente;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmuebleDetalleViewModel extends AndroidViewModel {
    private MutableLiveData<Inmueble> mInmueble;
    private MutableLiveData<Integer> mPiscina;
    private MutableLiveData<Integer> mMascotas;
    private MutableLiveData<Integer> mCochera;
    private MutableLiveData<String> mImagen;
    private Context context;

    public InmuebleDetalleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Integer> getMPiscina() {
        if(mPiscina == null){
            mPiscina = new MutableLiveData<>();
        }
        return mPiscina;
    }

    public LiveData<Integer> getMMascotas() {
        if(mMascotas == null){
            mMascotas = new MutableLiveData<>();
        }
        return mMascotas;
    }

    public LiveData<Integer> getMCochera() {
        if(mCochera == null){
            mCochera = new MutableLiveData<>();
        }
        return mCochera;
    }

    public LiveData<Inmueble> getMInmueble(){
        if(mInmueble == null){
            mInmueble = new MutableLiveData<>();
        }
        return mInmueble;
    }

    public LiveData<String> getMImagen(){
        if(mImagen == null){
            mImagen = new MutableLiveData<>();
        }
        return mImagen;
    }

    public void setearImagen(String imagen){
        if(imagen.startsWith("http")){
            mImagen.setValue(imagen);
        }else{
            mImagen.setValue("http://192.168.1.9:5203/img/inmueble/"+imagen);
        }
    }

    public void leerDatos(Bundle b){
        Inmueble i = (Inmueble) b.getSerializable("Inmueble");
        if(i != null){
            mInmueble.setValue(i);
        }
    }

    public void cambiarDisponibilidadInmueble() {
        ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
        api.cambiarDisponilidad(ApiCliente.getToken(context), mInmueble.getValue().getIdInmueble()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response.isSuccessful()){
                    Toast.makeText(context, response.body(), Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(context, "Error al cambiar la disponibilidad", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable throwable) {
                Toast.makeText(context, "Error en el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setearImagenCochera(boolean cochera) {
        if(cochera){
            mCochera.setValue(R.drawable.cochera_icon);
        }else{
            mCochera.setValue(R.drawable.not_cochera_icon);
        }
    }

    public void setearImagenPiscina(boolean piscina) {
        if(piscina){
            mPiscina.setValue(R.drawable.piscina_icon);
        }else{
            mPiscina.setValue(R.drawable.not_piscina_icon);
        }
    }

    public void setearImagenMascotas(boolean mascotas) {
        if(mascotas){
            mMascotas.setValue(R.drawable.mascotas_icon);
        }else{
            mMascotas.setValue(R.drawable.not_mascotas_icon);
        }
    }

    public void irAEditarInmueble(View view) {
        Bundle b = new Bundle();
        b.putSerializable("Editar", true);
        b.putSerializable("Inmueble", mInmueble.getValue());
        Navigation.findNavController(view).navigate(R.id.nav_altamodificacion_inmueble, b);
    }
}