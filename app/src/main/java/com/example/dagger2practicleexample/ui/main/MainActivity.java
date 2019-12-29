package com.example.dagger2practicleexample.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dagger2practicleexample.MyBaseActivity;
import com.example.dagger2practicleexample.R;
import com.example.dagger2practicleexample.SessionManager;
import com.example.dagger2practicleexample.ui.main.post.PostFragment;
import com.example.dagger2practicleexample.ui.main.profile.ProfileFragment;

public class MainActivity extends MyBaseActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(this, "MainActivity", Toast.LENGTH_SHORT).show();

        setUpFragment();

    }

    private void setUpFragment(){

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container,new PostFragment())
        .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_resource,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()){
            case R.id.logout:
                sessionManager.logout();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
