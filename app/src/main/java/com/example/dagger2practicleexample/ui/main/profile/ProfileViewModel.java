package com.example.dagger2practicleexample.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.dagger2practicleexample.SessionManager;
import com.example.dagger2practicleexample.models.User;
import com.example.dagger2practicleexample.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {

    private static final String TAG = "ProfileViewModel";
    private final SessionManager sessionManager;


    @Inject
    public ProfileViewModel(SessionManager sessionManager){
        Log.d(TAG, "ProfileViewModel: is properly working ...");
        this.sessionManager = sessionManager;
    }

    public LiveData<AuthResource<User>> getAuthenticatedUser(){
        return sessionManager.getAuthUser();
    }
}
