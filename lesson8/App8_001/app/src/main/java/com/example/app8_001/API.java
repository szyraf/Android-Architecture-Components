package com.example.app8_001;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface API {
    @Multipart
    @POST("/api/photos")
    Call<UploadFile> sendImage(
            @Part("album") RequestBody album,
            @Part MultipartBody.Part file
    );

    @GET("/api/photos")
    Call<List<PhotoJSON>> getAll();
}
