package com.example.viewbindingapp3_001.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.viewbindingapp3_001.R;
import com.example.viewbindingapp3_001.databinding.ActivityMainBinding;
import com.example.viewbindingapp3_001.viewmodel.SettingsViewModel;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    private SettingsViewModel settingsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        settingsViewModel = new ViewModelProvider(MainActivity.this).get(SettingsViewModel.class);
        settingsViewModel.setupSettings();

        settingsViewModel.getObservedSettings().observe(MainActivity.this, s -> {
            //teraz każda zmiana w livedata będzie się odwzorowywać na pole txt
            activityMainBinding.tv.setText(s.getUrl() + ":" + s.getPort());
        });

        /*
        anyViewModel.anyMutableLiveData.observe(context, new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                // reakcja na zmainę danych w viewmodel
            }
        });
        */

        activityMainBinding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = activityMainBinding.et.getText().toString();
                String port = activityMainBinding.et2.getText().toString();
                settingsViewModel.changeSettings(url, port);
            }
        });
    }
}