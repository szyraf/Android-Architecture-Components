package com.example.viewbindingapp5_001;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.viewbindingapp5_001.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostsAPI postsAPI = retrofit.create(PostsAPI.class);

        activityMainBinding.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post post = new Post(100, "new post title", "new post text");
                Call<Post> call = postsAPI.createPost(post);

                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (!response.isSuccessful()) {
                            Log.d("xxx", String.valueOf(response.code()));
                            return;
                        } else {
                            Post postResponse = response.body();

                            String content = "";
                            content += "Code: " + response.code() + "\n";
                            content += "UserID: " + postResponse.getUserId() + "\n";
                            content += "PostID: " + postResponse.getId() + "\n";
                            content += "Title: " + postResponse.getTitle() + "\n";
                            content += "Text: " + postResponse.getText() + "\n\n";
                            
                            activityMainBinding.tv1.setText(content);
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        t.getMessage();
                    }
                });
            }
        });

        activityMainBinding.bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Post> call = postsAPI.createPost(200, "new title", "new text");

                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (!response.isSuccessful()) {
                            Log.d("xxx", String.valueOf(response.code()));
                            return;
                        } else {
                            Post postResponse = response.body();

                            String content = "";
                            content += "Code: " + response.code() + "\n";
                            content += "UserID: " + postResponse.getUserId() + "\n";
                            content += "PostID: " + postResponse.getId() + "\n";
                            content += "Title: " + postResponse.getTitle() + "\n";
                            content += "Text: " + postResponse.getText() + "\n\n";

                            activityMainBinding.tv1.setText(content);
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        t.getMessage();
                    }
                });
            }
        });

        activityMainBinding.bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post post = new Post(100, null, "new post text");
                Call<Post> call = postsAPI.putPost(100, post);

                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (!response.isSuccessful()) {
                            Log.d("xxx", String.valueOf(response.code()));
                            return;
                        } else {
                            Post postResponse = response.body();

                            String content = "";
                            content += "Code: " + response.code() + "\n";
                            content += "UserID: " + postResponse.getUserId() + "\n";
                            content += "PostID: " + postResponse.getId() + "\n";
                            content += "Title: " + postResponse.getTitle() + "\n";
                            content += "Text: " + postResponse.getText() + "\n\n";

                            activityMainBinding.tv1.setText(content);
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        t.getMessage();
                    }
                });
            }
        });

        activityMainBinding.bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post post = new Post(100, null, "new post text");
                Call<Post> call = postsAPI.patchPost(100, post);

                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if (!response.isSuccessful()) {
                            Log.d("xxx", String.valueOf(response.code()));
                            return;
                        } else {
                            Post postResponse = response.body();

                            String content = "";
                            content += "Code: " + response.code() + "\n";
                            content += "UserID: " + postResponse.getUserId() + "\n";
                            content += "PostID: " + postResponse.getId() + "\n";
                            content += "Title: " + postResponse.getTitle() + "\n";
                            content += "Text: " + postResponse.getText() + "\n\n";

                            activityMainBinding.tv1.setText(content);
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        t.getMessage();
                    }
                });
            }
        });

        activityMainBinding.bt5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<Void> call = postsAPI.deletePost(100);

                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (!response.isSuccessful()) {
                            Log.d("xxx", String.valueOf(response.code()));
                            return;
                        } else {
                            activityMainBinding.tv1.setText("Code: " + response.code());
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        t.getMessage();
                    }
                });
            }
        });
    }
}