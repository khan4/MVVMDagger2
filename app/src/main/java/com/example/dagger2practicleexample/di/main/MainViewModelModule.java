package com.example.dagger2practicleexample.di.main;

import androidx.lifecycle.ViewModel;

import com.example.dagger2practicleexample.di.ViewModelKeys;
import com.example.dagger2practicleexample.ui.main.post.PostViewModel;
import com.example.dagger2practicleexample.ui.main.profile.ProfileFragment;
import com.example.dagger2practicleexample.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {


    @Binds
    @IntoMap
    @ViewModelKeys(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel profileViewModel);

    @Binds
    @IntoMap
    @ViewModelKeys(PostViewModel.class)
    public abstract ViewModel bindPostViewModel(PostViewModel postViewModel);

}
