package com.example.viewbindingapp2_003;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.example.viewbindingapp2_003.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        activityMainBinding.bottomNavigation.setOnItemSelectedListener(v -> {
            switch (v.getItemId()) {

                case R.id.textview:
                    replaceFragment(new textview());
                    break;
                case R.id.image:
                    replaceFragment(new image());
                    break;
                case R.id.buttons:
                    replaceFragment(new buttons());
                    break;
            }
            return true;
        });
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }
}