package com.example.propietariosmobilecliente.ui.perfil;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.propietariosmobilecliente.R;
import com.example.propietariosmobilecliente.databinding.FragmentEditarAvatarBinding;

public class EditarAvatarFragment extends Fragment {
    private FragmentEditarAvatarBinding binding;
    private EditarAvatarViewModel vm;
    private Intent intent;
    private ActivityResultLauncher<Intent> launcher;

    public static EditarAvatarFragment newInstance() {
        return new EditarAvatarFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentEditarAvatarBinding.inflate(inflater, container, false);
        vm = new ViewModelProvider(this).get(EditarAvatarViewModel.class);
        abrirGaleria();
        vm.getMAvatar().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String avatar) {
                Glide.with(getContext())
                        .load(avatar.startsWith("http")?avatar:Uri.parse(avatar))
                        .placeholder(R.drawable.ic_launcher_background)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.ivAvatarEditarAvatar);
            }
        });
        vm.setearAvatar(getArguments());
        binding.btnEditarAvatarElegirOtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                launcher.launch(intent);
            }
        });
        binding.btnEditarAvatarGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.guardarAvatar();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        vm = new ViewModelProvider(this).get(EditarAvatarViewModel.class);
        // TODO: Use the ViewModel
    }

    private void abrirGaleria() {
        intent = new Intent(Intent.ACTION_OPEN_DOCUMENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                vm.recibirFoto(result);
            }
        });
    }

}