package com.example.dagger2practicleexample.di;



import com.example.dagger2practicleexample.di.auth.AuthModule;
import com.example.dagger2practicleexample.di.auth.AuthViewModelsModule;
import com.example.dagger2practicleexample.di.main.MainFragmentBuilderModule;
import com.example.dagger2practicleexample.di.main.MainModule;
import com.example.dagger2practicleexample.di.main.MainViewModelModule;
import com.example.dagger2practicleexample.ui.auth.AuthActivity;
import com.example.dagger2practicleexample.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule  {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class, AuthModule.class}
    )
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(
            modules = {MainFragmentBuilderModule.class, MainViewModelModule.class, MainModule.class}
    )
    abstract MainActivity contributeMainActivity();

}
