package com.example.viewbindingapp3_004.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.viewbindingapp3_004.adapters.UserAdapter;
import com.example.viewbindingapp3_004.databinding.ActivityMainBinding;
import com.example.viewbindingapp3_004.model.User;
import com.example.viewbindingapp3_004.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private UserAdapter adapter;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);


        userViewModel = new ViewModelProvider(MainActivity.this).get(UserViewModel.class);

        activityMainBinding.setUserViewModel(userViewModel);


        adapter = new UserAdapter(userViewModel);
        activityMainBinding.usersListView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        userViewModel.getObservedUserList().observe(MainActivity.this, l -> {
            adapter.notifyDataSetChanged();
        });
    }
}