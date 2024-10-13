package com.example.propietariosmobilecliente.ui.inquilino;

import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.propietariosmobilecliente.models.Inquilino;

public class InquilinoViewModel extends AndroidViewModel {
    private MutableLiveData<Inquilino> mInquilino;
    public InquilinoViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<Inquilino> getMInquilino(){
        if(mInquilino == null){
            mInquilino = new MutableLiveData<>();
        }
        return mInquilino;
    }

    public void cargarInquilino(Bundle b){
        Inquilino i = (Inquilino) b.getSerializable("Inquilino");
        if(i != null){
            mInquilino.setValue(i);
        }
    }

}