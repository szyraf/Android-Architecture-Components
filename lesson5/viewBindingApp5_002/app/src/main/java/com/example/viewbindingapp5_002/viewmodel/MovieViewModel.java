package com.example.viewbindingapp5_002.viewmodel;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.viewbindingapp5_002.model.Movies;
import com.example.viewbindingapp5_002.service.RetrofitService;
import com.example.viewbindingapp5_002.view.MainActivity;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.Scanner;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<Movies> mutableMoviesList;

    public MovieViewModel() {
        this.mutableMoviesList = new MutableLiveData<>();
    }

    public void getPopularMovies() {

        Call<Movies> call = RetrofitService
                .getMovieInterface()
                .getPopularMovies(MainActivity.API_KEY);

        call.enqueue(new Callback<Movies>() {
            @Override
            public void onResponse(Call<Movies> call, Response<Movies> response) {
                mutableMoviesList.setValue(response.body());
            }

            @Override
            public void onFailure(Call<Movies> call, Throwable t) {
                Log.d("xxx", "onFailure:" + t);
            }
        });

    }

    public MutableLiveData<Movies> getObservedMovies() {
        return mutableMoviesList;
    }
}
