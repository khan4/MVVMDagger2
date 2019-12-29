package com.example.dagger2practicleexample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.dagger2practicleexample.models.User;
import com.example.dagger2practicleexample.ui.auth.AuthActivity;
import com.example.dagger2practicleexample.ui.auth.AuthResource;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class MyBaseActivity extends DaggerAppCompatActivity {

    private static final String TAG = "MyBaseActivity";

    @Inject
    public SessionManager sessionManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        subscribeObserver();

    }

    private void subscribeObserver(){

        sessionManager.getAuthUser().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {
                if (userAuthResource.status != null){

                    switch (userAuthResource.status){

                        case LOADING:
                            break;

                        case AUTHENTICATED:
                            Log.d(TAG, "onChanged: User is authenticated ");
                            break;

                        case NOT_AUTHENTICATED:
                         navLoginScreen();
                            break;

                        case ERROR:

                            break;


                    }

                }
            }
        });
    }

    private void navLoginScreen(){
        Intent intent = new Intent(this,AuthActivity.class);
        startActivity(intent);
        finish();
    }
}
