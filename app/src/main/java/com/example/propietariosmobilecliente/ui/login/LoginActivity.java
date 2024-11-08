package com.example.propietariosmobilecliente.ui.login;

import static android.Manifest.permission.CALL_PHONE;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.propietariosmobilecliente.MainActivity;
import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {
    private LoginActivityViewModel vm;
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginActivityViewModel.class);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        //permiso para llamada
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.M
                && checkSelfPermission(CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED){
            requestPermissions(new String[]{CALL_PHONE},1000);
        }
        Intent i = getIntent();
        vm.mostrarMensaje(i.getSerializableExtra("mensaje"));
        binding.btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.login(binding.etCorreo.getText().toString(), binding.etPassword.getText().toString());
                binding.etCorreo.setText("");
                binding.etPassword.setText("");
            }
        });
        binding.tvRecuperarPasswordLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.recuperarPassword();
            }
        });
        //LOGICA PARA QUE SI AGITA EL CELULAR HAGA UNA LLAMADA
        SensorManager sensor = (SensorManager) getSystemService(SENSOR_SERVICE);
        Sensor acelerometro = sensor.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensor.registerListener(new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {
                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];
                float aceleracion = (float) (Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH);
                vm.realizarLLamada(aceleracion);
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        }, acelerometro, SensorManager.SENSOR_DELAY_UI);
    }
}