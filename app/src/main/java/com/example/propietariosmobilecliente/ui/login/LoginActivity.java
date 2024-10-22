package com.example.propietariosmobilecliente.ui.login;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

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
        //BOTON INGRESAR
        binding.btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                vm.verificarDatos(binding.etCorreo.getText().toString(), binding.etPassword.getText().toString());
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
    }
}