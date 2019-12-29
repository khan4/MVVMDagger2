package com.example.dagger2practicleexample.ui.main.post;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dagger2practicleexample.R;
import com.example.dagger2practicleexample.models.Posts;
import com.example.dagger2practicleexample.ui.main.Resource;
import com.example.dagger2practicleexample.util.VerticalSpaceItemDecoration;
import com.example.dagger2practicleexample.viewmodels.ViewModelProviderFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class PostFragment extends DaggerFragment {
    private static final String TAG = "PostFragment";
    private PostViewModel viewModel;
    private RecyclerView recyclerView;

    @Inject
    PostRecyclerAdapter adapter;

    @Inject
    ViewModelProviderFactory providerFactory;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_post,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.recyclerView);

        viewModel = ViewModelProviders.of(this,providerFactory).get(PostViewModel.class);
        initRecyclerView();
        subscribeObserver();

    }
    private void subscribeObserver(){
        viewModel.observePosts().removeObservers(getViewLifecycleOwner());
        viewModel.observePosts().observe(getViewLifecycleOwner(), new Observer<Resource<List<Posts>>>() {
            @Override
            public void onChanged(Resource<List<Posts>> listResource) {

                if (listResource!= null){

                    switch (listResource.status){

                        case LOADING:
                            Log.d(TAG, "onChanged: Loading ...");
                            break;
                        case SUCCESS:
                            adapter.setPosts(listResource.data);
                            break;
                        case ERROR:
                            break;


                    }
                }
            }
        });
    }

    private void initRecyclerView(){

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        VerticalSpaceItemDecoration itemDecoration = new VerticalSpaceItemDecoration(15);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter(adapter);
    }
}
