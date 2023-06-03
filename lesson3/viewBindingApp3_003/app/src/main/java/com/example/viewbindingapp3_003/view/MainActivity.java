package com.example.viewbindingapp3_003.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.example.viewbindingapp3_003.databinding.ActivityMainBinding;
import com.example.viewbindingapp3_003.model.Person;
import com.example.viewbindingapp3_003.viewmodel.PersonViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private PersonViewModel personViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        personViewModel = new ViewModelProvider(MainActivity.this).get(PersonViewModel.class);
        personViewModel.setupData();

        personViewModel.getObservedPerson().observe(MainActivity.this, s -> {
            activityMainBinding.setPersonViewModel(personViewModel);
        });

        activityMainBinding.btnName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sex = personViewModel.getPerson().getSex();
                String name = personViewModel.getPerson().getName();
                if (name.equals("Tomasz")) {
                    name = "Anna";
                } else {
                    name = "Tomasz";
                }
                personViewModel.changePerson(sex, name);
            }
        });

        activityMainBinding.btnSex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sex = personViewModel.getPerson().getSex();
                if (sex.equals("Man")) {
                    sex = "Woman";
                } else {
                    sex = "Man";
                }
                String name = personViewModel.getPerson().getName();
                personViewModel.changePerson(sex, name);
            }
        });

        activityMainBinding.btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String sex = personViewModel.getPerson().getSex();
                if (sex.equals("Man")) {
                    sex = "Woman";
                } else {
                    sex = "Man";
                }
                String name = personViewModel.getPerson().getName();
                if (name.equals("Tomasz")) {
                    name = "Anna";
                } else {
                    name = "Tomasz";
                }
                personViewModel.changePerson(sex, name);
            }
        });

    }
}