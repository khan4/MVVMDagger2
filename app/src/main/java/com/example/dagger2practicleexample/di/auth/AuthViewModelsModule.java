package com.example.dagger2practicleexample.di.auth;

import androidx.lifecycle.ViewModel;

import com.example.dagger2practicleexample.di.ViewModelKeys;
import com.example.dagger2practicleexample.ui.auth.AuthViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKeys(AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel(AuthViewModel viewModel);
}
