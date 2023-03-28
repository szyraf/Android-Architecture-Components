package com.example.viewbindingapp2_001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.viewbindingapp2_001.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        Fragment fragment1 = new Fragment1();
        replaceFragment(fragment1);
        activityMainBinding.btn1.setOnClickListener(v -> {
            replaceFragment(fragment1);
        });

        Fragment fragment2 = new Fragment2();
        activityMainBinding.btn2.setOnClickListener(v -> {
            replaceFragment(fragment2);
        });

        activityMainBinding.btn3.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putString("data", String.valueOf(activityMainBinding.editText1.getText()));
            getSupportFragmentManager().setFragmentResult("datafromactivity", bundle);
        });
    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }
}