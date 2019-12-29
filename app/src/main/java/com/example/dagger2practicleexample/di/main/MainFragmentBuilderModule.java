package com.example.dagger2practicleexample.di.main;

import com.example.dagger2practicleexample.ui.main.post.PostFragment;
import com.example.dagger2practicleexample.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuilderModule {


    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

    @ContributesAndroidInjector
    abstract PostFragment contributePostFragment();
}
