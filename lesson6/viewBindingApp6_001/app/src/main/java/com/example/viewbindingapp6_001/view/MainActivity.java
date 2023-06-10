package com.example.viewbindingapp6_001.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.viewbindingapp6_001.database.UserDatabase;
import com.example.viewbindingapp6_001.databinding.ActivityMainBinding;
import com.example.viewbindingapp6_001.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    private UserDatabase userDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        userDatabase = UserDatabase.getInstance(MainActivity.this);

        User user = new User("a", "b");
        userDatabase.userDAO().insertOne(user);
        userDatabase.userDAO().insertAll(user, user, user);

        List<User> users = userDatabase.userDAO().getAll();

        Log.d("xxx", String.valueOf(users.size()));
        
        for (User u : users) {
            Log.d("xxx", u.getFirstName() + " " + u.getLastName());
        }
    }
}