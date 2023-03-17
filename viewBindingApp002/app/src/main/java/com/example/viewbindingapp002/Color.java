package com.example.viewbindingapp002;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewbindingapp002.databinding.ActivityColorBinding;
import com.example.viewbindingapp002.databinding.ActivityMainBinding;

public class Color extends AppCompatActivity {
    private ActivityColorBinding activityColorBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_color);

        activityColorBinding = ActivityColorBinding.inflate(getLayoutInflater());
        View view = activityColorBinding.getRoot();
        setContentView(view);
        
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        String color = getIntent().getStringExtra("color");

        activityColorBinding.idText.setText(color);

        activityColorBinding.idColor.setBackgroundColor(android.graphics.Color.parseColor(color));
        activityColorBinding.idColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            this.finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}