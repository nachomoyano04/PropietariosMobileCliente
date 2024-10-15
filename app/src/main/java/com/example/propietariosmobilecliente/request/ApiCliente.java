package com.example.propietariosmobilecliente.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.propietariosmobilecliente.models.Inmueble;
import com.example.propietariosmobilecliente.models.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public class ApiCliente {
    private static final String URLBASE = "http://192.168.1.9:5203/api/";
    private static SharedPreferences sp;

    private static SharedPreferences conectar(Context context){
        if(sp == null){
            sp = context.getSharedPreferences("tokenPropietario", 0);
        }
        return sp;
    }

    public static InmobiliariaService getApiInmobiliaria(Context context){
        Gson gson = new GsonBuilder().setLenient().create();
        OkHttpClient cliente = new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    String token = getToken(context);
                    Request original = chain.request();
                    Request request = original.newBuilder()
                            .header("Authorization", "Bearer "+token)
                            .method(original.method(), original.body())
                            .build();
                    return chain.proceed(request);
                }).build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLBASE)
                .client(cliente)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(InmobiliariaService.class);
    }

    public static void guardarToken(Context context, String token) {
        SharedPreferences shared = conectar(context);
        SharedPreferences.Editor editor = shared.edit();
        editor.putString("token", token);
        editor.commit();
    }

    public static String getToken(Context context){
        SharedPreferences shared = conectar(context);
        return shared.getString("token", "");
    }

    public interface InmobiliariaService{
        //login
        @FormUrlEncoded
        @POST("propietarioapi/login")
        Call<String> login(@Field("Correo") String correo, @Field("Password") String password);

        //get datos para perfil
        @GET("propietarioapi")
        Call<Propietario> getPropietario();

        //get inmuebles del propietario logueado
        @GET("inmuebleapi/inmuebles")
        Call<ArrayList<Inmueble>> inmuebles();
    }
}