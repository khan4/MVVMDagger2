package com.example.dagger2practicleexample;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.dagger2practicleexample.models.User;
import com.example.dagger2practicleexample.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {

    private MediatorLiveData<AuthResource<User>> cachedUser = new MediatorLiveData<>();

    @Inject
    public SessionManager(){

    }

    public void authenticateWithId(final LiveData<AuthResource<User>> source ){

        if (cachedUser !=null){

            cachedUser.setValue(AuthResource.loading((User)null));

            cachedUser.addSource(source, new Observer<AuthResource<User>>() {
                @Override
                public void onChanged(AuthResource<User> userAuthResource) {

                    cachedUser.setValue(userAuthResource);
                    cachedUser.removeSource(source);
                }
            });

        }

    }

    public void logout(){
        cachedUser.setValue(AuthResource.<User>logout());
    }

    public LiveData<AuthResource<User>> getAuthUser(){
        return cachedUser;
    }


}
