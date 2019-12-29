package com.example.dagger2practicleexample.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.RequestManager;
import com.example.dagger2practicleexample.R;
import com.example.dagger2practicleexample.models.User;
import com.example.dagger2practicleexample.ui.main.MainActivity;
import com.example.dagger2practicleexample.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AuthActivity";
    private ImageView imageView;
    private AuthViewModel viewModel;
    private EditText etId;
    private Button btnOk;
    private ProgressBar progressBar;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Inject
    Drawable logo;

    @Inject
    RequestManager requestManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        imageView = findViewById(R.id.imageView);
        etId = findViewById(R.id.editText);
        btnOk = findViewById(R.id.btnOk);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);

        btnOk.setOnClickListener(this);
        setLogo();
        viewModel = ViewModelProviders.of(this,providerFactory).get(AuthViewModel.class);
        subscribeObserver();
    }

    private void setLogo(){
        requestManager.load(logo).into(imageView);
    }


    private void subscribeObserver(){

       // viewModel.observeUser().observe(this, new Observer<User>() {
      //      @Override
       //     public void onChanged(User user) {
        //        Log.d(TAG, "onChanged: User is "+user.getUsername()+"\n Email is "+user.getEmail());
        //    }
       // });
        viewModel.observeAuthState().observe(this, new Observer<AuthResource<User>>() {
            @Override
            public void onChanged(AuthResource<User> userAuthResource) {

                if (userAuthResource.status != null){

                    switch (userAuthResource.status){


                        case LOADING:
                            progressBar.setVisibility(View.VISIBLE);
                            break;

                        case AUTHENTICATED:
                            progressBar.setVisibility(View.INVISIBLE);
                            loginActivity();
                            break;

                        case NOT_AUTHENTICATED:
                            progressBar.setVisibility(View.INVISIBLE);
                            break;

                        case ERROR:
                            progressBar.setVisibility(View.INVISIBLE);
                            Toast.makeText(AuthActivity.this,"Do you Enter a number between 1 and 10 ?",Toast.LENGTH_LONG).show();
                            break;


                    }

                }

            }
        });


    }

    private void loginActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case R.id.btnOk:
                attemptLogin();
                break;

        }
    }

    private void attemptLogin(){
        if (!TextUtils.isEmpty(etId.getText().toString())){
            progressBar.setVisibility(View.VISIBLE);
            viewModel.authenticateWithId(Integer.parseInt(etId.getText().toString()));
        }
    }
}
