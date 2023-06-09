package com.example.viewbindingapp4_002;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface PostsAPI {
    @GET("/posts")
    Call<List<Post>> getAllPosts();

    @GET ("/post/{id}/comments")
    Call<List<Comment>> getComents(@Path("id") int postId);

    @GET("/posts")
    Call<List<Post>> getUserPosts(@Query("userId") int userId);

    @GET("/posts")
    Call<List<Post>> getUserPosts(
            @Query("userId") int userId,
            @Query("_sort") String sort,
            @Query("_order") String order
    );

    @GET("/posts")
    Call<List<Post>> getUserPostsMap(@QueryMap Map<String, String> params);
}
