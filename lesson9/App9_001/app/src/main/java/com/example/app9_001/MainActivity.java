package com.example.app9_001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.app9_001.databinding.ActivityMainBinding;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        // setOnCheckedStateChangeListener
        activityMainBinding.chipGroup.setOnCheckedChangeListener((group, checkedIds) -> {
            Log.d("xxx", "checkedIds: " + checkedIds);

            for (int i = 0; i < group.getChildCount(); i++) {
                Chip chip = (Chip) group.getChildAt(i);
                chip.setOnCheckedChangeListener((compoundButton, b) -> {
                    Log.d("xxx", "change");
                });
                if (chip.isChecked()) {

                }
            }
        });

        LayoutInflater inflater = MainActivity.this.getLayoutInflater();
        Chip chip = (Chip) inflater.inflate(R.layout.chip_item, null, false);
        chip.setText("text");

        chip.setOnCloseIconClickListener(v -> {
            activityMainBinding.chipGroup.removeView(v);
        });

        activityMainBinding.chipGroup.addView(chip);

        for (int i = 0; i < activityMainBinding.chipGroup.getChildCount(); i++) {

            View view2 = activityMainBinding.chipGroup.getChildAt(i);
            // if (chip.isChecked()){

            // }

            if (view2 instanceof Chip) {
                Chip chip2 = (Chip) view2;
                if (chip2.isChecked()) {
                    Log.d("xxx", "chip2: " + chip2.getText());
                }
            }

        }

    }
}