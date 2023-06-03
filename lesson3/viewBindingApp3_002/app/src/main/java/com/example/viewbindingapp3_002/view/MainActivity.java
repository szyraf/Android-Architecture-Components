package com.example.viewbindingapp3_002.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.viewbindingapp3_002.R;
import com.example.viewbindingapp3_002.databinding.ActivityMainBinding;
import com.example.viewbindingapp3_002.viewmodel.TestViewModel;

import java.time.Instant;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private TestViewModel testViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        testViewModel = new ViewModelProvider(MainActivity.this).get(TestViewModel.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            testViewModel.setupData(22, Instant.now().getEpochSecond());
        }

        activityMainBinding.setTestViewModel(testViewModel);

        testViewModel.getObservedAge().observe(MainActivity.this, s -> {
            Log.d("xxx", "data changed" + s);
            activityMainBinding.setTestViewModel(testViewModel);
        });

        testViewModel.getObservedTimestamp().observe(MainActivity.this, s -> {
            Log.d("xxx", "data changed" + s);
            activityMainBinding.setTestViewModel(testViewModel);
        });

        // Instant.now().getEpochSecond()
    }
}