package com.example.viewbindingapp003;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.viewbindingapp003.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        User user = new User("Jan", "Kowalski");
        user.fName = "Anna";
        activityMainBinding.setUser(user);

        activityMainBinding.button1.setOnClickListener(v->{
            if (user.fName.equals("Anna")) {
                user.fName = "Jan";
            } else {
                user.fName = "Anna";
            }
            activityMainBinding.setUser(user);
        });

        Movie movie = new Movie("Insomnia");
        activityMainBinding.setMovie(movie);

        activityMainBinding.button2.setOnClickListener(v->{
            Snackbar s = Snackbar.make(view, movie.getTitle(), Snackbar.LENGTH_LONG);
            s.setAction("OK", a -> Log.d("xxx", "snackbar clicked"));
            s.show();
        });

        State state = new State(true);
        activityMainBinding.setState(state);
    }
}