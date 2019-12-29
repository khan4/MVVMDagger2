package com.example.dagger2practicleexample.ui.main.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.dagger2practicleexample.R;
import com.example.dagger2practicleexample.models.User;
import com.example.dagger2practicleexample.ui.auth.AuthResource;
import com.example.dagger2practicleexample.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {

    private ProfileViewModel viewModel;
    private TextView email,username,website;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_profile,container,false);
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);

        Toast.makeText(getActivity(), "This is the Fragment Module", Toast.LENGTH_SHORT).show();

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(ProfileViewModel.class);
        subscribeObserver();

    }

    private void subscribeObserver(){

        viewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                if (userAuthResource != null){

                    switch (userAuthResource.status){


                        case AUTHENTICATED:
                            setUserDetails(userAuthResource.data);
                            break;

                        case ERROR:
                            setErrorDetail(userAuthResource.message);
                            break;


                    }

                }
            }
        });

    }

    private void setErrorDetail(String message) {
        email.setText("error");
        website.setText("error");
        username.setText("error");
    }

    private void setUserDetails(User data) {
        email.setText(data.getEmail());
        website.setText(data.getWebsite());
        username.setText(data.getUsername());
    }
}
