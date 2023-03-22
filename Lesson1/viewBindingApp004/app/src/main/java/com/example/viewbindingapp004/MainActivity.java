package com.example.viewbindingapp004;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.viewbindingapp004.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        ArrayList<Item> list = new ArrayList<>();
        list.add(new Item("-65535", "czerwony"));
        list.add(new Item("-16581630", "magenta"));

        TestAdapter itemsAdapter = new TestAdapter(list);
        activityMainBinding.listView.setAdapter(itemsAdapter);

        activityMainBinding.addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.add(new Item("-65535", "czerwony"));
                itemsAdapter.notifyDataSetChanged();
            }
        });

        activityMainBinding.deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                itemsAdapter.notifyDataSetChanged();
            }
        });
    }
}