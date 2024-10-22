package com.example.propietariosmobilecliente.ui.login;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.databinding.ActivityRecuperarPassBinding;

public class RecuperarPassActivity extends AppCompatActivity {

    private RecuperarPassActivityViewModel vm;
    private ActivityRecuperarPassBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRecuperarPassBinding.inflate(getLayoutInflater());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RecuperarPassActivityViewModel.class);
        vm.getMMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMensajeRecuperarPassword.setText(s);
            }
        });
        binding.btnMandarMailRecuperarPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.enviarCorreo(binding.etCorreoRecuperarPassword.getText().toString());
            }
        });
        setContentView(binding.getRoot());
    }
}