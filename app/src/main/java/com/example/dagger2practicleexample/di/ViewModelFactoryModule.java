package com.example.dagger2practicleexample.di;

import androidx.lifecycle.ViewModelProvider;

import com.example.dagger2practicleexample.viewmodels.ViewModelProviderFactory;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelFactoryModule {

    @Binds
    public abstract ViewModelProvider.Factory bindsViewModelFactory(ViewModelProviderFactory modelProviderFactory);


}
