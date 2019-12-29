package com.example.dagger2practicleexample.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.dagger2practicleexample.SessionManager;
import com.example.dagger2practicleexample.models.User;
import com.example.dagger2practicleexample.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private AuthApi authApi;
    private SessionManager sessionManager;
    private MediatorLiveData<AuthResource<User>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager)
    {
        this.authApi = authApi;
        this.sessionManager = sessionManager;
    }

    public void authenticateWithId(int id){



     //   final LiveData<User> source = LiveDataReactiveStreams.fromPublisher(
     //           authApi.getUser(id)
     //           .subscribeOn(Schedulers.io())
      //  );
        sessionManager.authenticateWithId(queryUserId(id));
    }

    private LiveData<AuthResource<User>> queryUserId(int userId){

        return LiveDataReactiveStreams.fromPublisher(

                authApi.getUser(userId)

                        .onErrorReturn(new Function<Throwable, User>() {
                            @Override
                            public User apply(Throwable throwable) throws Exception {

                                User errorUser =new User();
                                errorUser.setId(-1);

                                return errorUser;
                            }
                        })

                        .map(new Function<User, AuthResource<User>>() {
                            @Override
                            public AuthResource<User> apply(User user) throws Exception {


                                if (user.getId() == -1){
                                    return AuthResource.error("Could not Authenticated",(User)null);
                                }
                                return AuthResource.authenticated(user);
                            }
                        })

                        .subscribeOn(Schedulers.io())
        );
    }

    public LiveData<AuthResource<User>> observeAuthState(){
        return sessionManager.getAuthUser();
    }

}
