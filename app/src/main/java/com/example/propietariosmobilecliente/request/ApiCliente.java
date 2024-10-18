package com.example.propietariosmobilecliente.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.example.propietariosmobilecliente.models.Contrato;
import com.example.propietariosmobilecliente.models.Inmueble;
import com.example.propietariosmobilecliente.models.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
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
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLBASE)
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
        Call<Propietario> getPropietario(@Header("Authorization") String token);

        //get inmuebles del propietario logueado
        @GET("inmuebleapi")
        Call<ArrayList<Inmueble>> inmuebles(@Header("Authorization") String token);

        //cambiar password del propietario logueado
        @FormUrlEncoded
        @PUT("propietarioapi/password")
        Call<String> cambiarPassword(@Header("Authorization")String token, @Field("Password") String password);

        //cambiar la disponibilidad del inmueble que se esta viendo el detalle
        @PUT("inmuebleapi/disponibilidad/{id}")
        Call<String> cambiarDisponilidad(@Header("Authorization") String token, @Path("id") int id);

        //editar propietario
        @FormUrlEncoded
        @PUT("propietarioapi")
        Call<String> editarPropietario(@Header("Authorization") String token, @Field("dni") String dni, @Field("apellido") String apellido, @Field("nombre") String nombre, @Field("telefono") String telefono, @Field("correo") String correo);

        //editar avatar propietario
        @Multipart
        @PUT("propietarioapi/avatar")
        Call<String> editarAvatar(@Header("Authorization") String token, @Part String avatar);

        //obtener los contratos de x inmueble
        @GET("contratoapi/{id}")
        Call<String> getContratosPorInmueble(@Header("Authorization") String token, @Path("id") int id);

        //obtener todos los contratos que tengan los inmuebles del propietario logueado
        @GET("contratoapi")
        Call<ArrayList<Contrato>> getContratos(@Header("Authorization") String token);
    }
}