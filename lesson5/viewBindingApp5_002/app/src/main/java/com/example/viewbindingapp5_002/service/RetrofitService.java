package com.example.viewbindingapp5_002.service;

import com.example.viewbindingapp5_002.api.MovieInterface;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static final String BASE_URL = "https://api.themoviedb.org/3/movie/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    // zwracamy interfejs na zewnątrz

    public static MovieInterface getMovieInterface() {
        return retrofit.create(MovieInterface.class);
    }
}
