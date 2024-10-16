package com.example.propietariosmobilecliente.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.util.Log;
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

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {

    private MutableLiveData<ArrayList<Inmueble>> mListaInmuebles;
    private Context context;
    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Inmueble>> getMListaInmuebles(){
        if(mListaInmuebles == null){
            mListaInmuebles = new MutableLiveData<>();
        }
        return mListaInmuebles;
    }

    public void cargarLista(){
        //logica para cargar lista cuando consumamos la api obteniendo los inmuebles del propietario logueado.
        String token = ApiCliente.getToken(context);
        ApiCliente.InmobiliariaService api = ApiCliente.getApiInmobiliaria(context);
        Call<ArrayList<Inmueble>> getInmuebles = api.inmuebles(ApiCliente.getToken(context));
        getInmuebles.enqueue(new Callback<ArrayList<Inmueble>>() {
            @Override
            public void onResponse(Call<ArrayList<Inmueble>> call, Response<ArrayList<Inmueble>> response) {
                if(response.isSuccessful()){
                    ArrayList<Inmueble> listaInmuebles = response.body();
                    if(!listaInmuebles.isEmpty()){
                        mListaInmuebles.setValue(listaInmuebles);
                    }else{
                        Toast.makeText(context, "La lista esta vacía", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "Error en la respuesta", Toast.LENGTH_SHORT).show();
                    if (!response.isSuccessful()) {
                        Log.e("ErrorRespuesta", "Código: " + response.code() + ", Mensaje: " + response.errorBody().toString());
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Inmueble>> call, Throwable throwable) {
                System.out.println("Errror en el servidor");
                Toast.makeText(context, "Error en el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void nuevoInmueble(View view) {
        Navigation.findNavController(view).navigate(R.id.nav_altamodificacion_inmueble);
    }
}