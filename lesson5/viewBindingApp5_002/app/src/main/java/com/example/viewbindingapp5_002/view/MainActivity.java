package com.example.viewbindingapp5_002.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.viewbindingapp5_002.adapters.MovieAdapter;
import com.example.viewbindingapp5_002.databinding.ActivityMainBinding;
import com.example.viewbindingapp5_002.viewmodel.MovieViewModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;
    private MovieViewModel movieViewModel;
    private MovieAdapter adapter;

    public static String API_KEY;
    private Map<String, String> envMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        API_KEY = readFirstLineFromAssets(getApplicationContext(), "apikey.txt");

        //API_KEY = MainActivity.getApiKey();
        Log.d("xxx", "API_KEY: " + API_KEY);

        movieViewModel = new ViewModelProvider(this).get(MovieViewModel.class);

        //start pobierania danych

        movieViewModel.getPopularMovies();
        activityMainBinding.setMovieViewModel(movieViewModel);

        //adapter

        adapter = new MovieAdapter(movieViewModel);

        //observe

        movieViewModel.getObservedMovies().observe(this, l -> {
            activityMainBinding.gridView.setAdapter(adapter);
        });
    }

    public String readFirstLineFromAssets(Context context, String fileName) {
        AssetManager assetManager = context.getAssets();

        try {
            InputStream inputStream = assetManager.open(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String firstLine = bufferedReader.readLine();
            bufferedReader.close();
            inputStream.close();
            return firstLine;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getApiKey() {
        String apikey = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(".env"));
            Log.d("xxx", "test");
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                if (parts.length == 2 && parts[0].equals("API_KEY")) {
                    apikey = parts[1];
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return apikey;
    }
}