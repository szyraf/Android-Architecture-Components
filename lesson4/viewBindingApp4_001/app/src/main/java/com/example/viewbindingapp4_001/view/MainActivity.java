package com.example.viewbindingapp4_001.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import android.view.View;

import com.example.viewbindingapp4_001.adapters.ItemsColorAdapter;
import com.example.viewbindingapp4_001.databinding.ActivityMainBinding;
import com.example.viewbindingapp4_001.viewmodel.ItemColorViewModel;
import com.example.viewbindingapp4_001.viewmodel.ItemViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private ItemViewModel itemViewModel;
    private ItemsColorAdapter itemsImageAdapter;
    private ItemColorViewModel itemImageViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        itemViewModel = new ViewModelProvider(MainActivity.this).get(ItemViewModel.class);
        activityMainBinding.setItemViewModel(itemViewModel);

        itemViewModel.getObservedItemList().observe(MainActivity.this, s -> {
            activityMainBinding.setItemViewModel(itemViewModel);
        });

        itemViewModel.getObservedIndex().observe(MainActivity.this, s -> {
            activityMainBinding.setItemViewModel(itemViewModel);
        });

        // spinner 3

        itemImageViewModel = new ViewModelProvider(MainActivity.this).get(ItemColorViewModel.class);
        activityMainBinding.setItemColorViewModel(itemImageViewModel);

        itemsImageAdapter = new ItemsColorAdapter(itemImageViewModel);
        activityMainBinding.spinner3.setAdapter(itemsImageAdapter);
        itemsImageAdapter.notifyDataSetChanged();

        itemImageViewModel.getObservedItemList().observe(MainActivity.this, l -> {
            activityMainBinding.setItemColorViewModel(itemImageViewModel);
            itemsImageAdapter.notifyDataSetChanged();
        });

    }
}