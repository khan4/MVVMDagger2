package com.example.dagger2practicleexample.network.main;

import com.example.dagger2practicleexample.models.Posts;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MainApi {


    @GET("posts")
    Flowable<List<Posts>> getPostsFromUser(
            @Query("userId") int id
    );

}
