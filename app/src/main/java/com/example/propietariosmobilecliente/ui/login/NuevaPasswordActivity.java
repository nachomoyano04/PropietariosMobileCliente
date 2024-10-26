package com.example.propietariosmobilecliente.ui.login;

import android.content.Intent;
import android.net.Uri;
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
import com.example.propietariosmobilecliente.databinding.ActivityNuevaPasswordBinding;

public class NuevaPasswordActivity extends AppCompatActivity {
    private ActivityNuevaPasswordBinding binding;
    private NuevaPasswordActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNuevaPasswordBinding.inflate(getLayoutInflater());
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(NuevaPasswordActivityViewModel.class);
        vm.getMMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.tvMensajeNuevaPassword.setText(s);
            }
        });
        binding.btnNuevaPasswordRecuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = getIntent();
                Uri data = i.getData();
                String token = data.getQueryParameter("acces_token");
                vm.guardarNuevaPassword(token, binding.etNuevaPasswordRecuperar.getText().toString());
            }
        });
        setContentView(binding.getRoot());
    }
}