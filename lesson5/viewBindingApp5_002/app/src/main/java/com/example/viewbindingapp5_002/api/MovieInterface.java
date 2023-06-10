package com.example.viewbindingapp5_002.api;

import com.example.viewbindingapp5_002.model.Movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieInterface {
    @GET("popular")
    Call<Movies> getPopularMovies(@Query("api_key") String api_key);
}
