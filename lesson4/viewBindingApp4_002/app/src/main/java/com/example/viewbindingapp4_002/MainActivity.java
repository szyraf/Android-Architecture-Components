package com.example.viewbindingapp4_002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.viewbindingapp4_002.databinding.ActivityMainBinding;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        // RETROFIT

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PostsAPI postsAPI = retrofit.create(PostsAPI.class);

        activityMainBinding.bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<Post>> call = postsAPI.getAllPosts();

                call.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if (!response.isSuccessful()) {
                            Log.d("xxx", String.valueOf(response.code()));
                            return;
                        } else {
                            List<Post> posts = response.body();

                            String content = "";
                            for (Post post : posts) {
                                content += "UserID: " + post.getUserId() + "\n";
                                content += "PostID: " + post.getId() + "\n";
                                content += post.getText() + "\n\n";
                            }
                            activityMainBinding.tv1.setText(content);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        t.getMessage();
                    }
                });
            }
        });

        activityMainBinding.bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<Comment>> call = postsAPI.getComents(3);

                call.enqueue(new Callback<List<Comment>>() {
                    @Override
                    public void onResponse(Call<List<Comment>> call, Response<List<Comment>> response) {
                        if (!response.isSuccessful()) {
                            Log.d("xxx", String.valueOf(response.code()));
                            return;
                        } else {
                            List<Comment> comments = response.body();

                            String content = "";
                            for (Comment comment : comments) {
                                content += "PostID: " + comment.getPostId() + "\n";
                                content += "CommentID: " + comment.getId() + "\n";
                                content += "email: " + comment.getEmail() + "\n\n";
                            }
                            activityMainBinding.tv1.setText(content);
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Comment>> call, Throwable t) {
                        t.getMessage();
                    }
                });
            }
        });

        activityMainBinding.bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<List<Post>> call = postsAPI.getUserPosts(2);

                call.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if (!response.isSuccessful()) {
                            Log.d("xxx", String.valueOf(response.code()));
                            return;
                        } else {
                            List<Post> posts = response.body();

                            String content = "";
                            for (Post post : posts) {
                                content += "UserID: " + post.getUserId() + "\n";
                                content += "PostID: " + post.getId() + "\n";
                                content += post.getText() + "\n\n";
                            }
                            activityMainBinding.tv1.setText(content);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        t.getMessage();
                    }
                });
            }
        });

        activityMainBinding.bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, String> params = new HashMap<>();
                params.put("userId", "1");
                params.put("_sort", "id");
                params.put("_order", "desc");

                Call<List<Post>> call = postsAPI.getUserPostsMap(params);

                call.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if (!response.isSuccessful()) {
                            Log.d("xxx", String.valueOf(response.code()));
                            return;
                        } else {
                            List<Post> posts = response.body();

                            String content = "";
                            for (Post post : posts) {
                                content += "UserID: " + post.getUserId() + "\n";
                                content += "PostID: " + post.getId() + "\n";
                                content += post.getText() + "\n\n";
                            }
                            activityMainBinding.tv1.setText(content);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        t.getMessage();
                    }
                });
            }
        });
    }
}