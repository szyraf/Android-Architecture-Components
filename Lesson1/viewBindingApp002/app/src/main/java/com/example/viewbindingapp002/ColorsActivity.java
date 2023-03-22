package com.example.viewbindingapp002;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class ColorsActivity extends AppCompatActivity {

    private ListView listView;

    private ArrayList<String> list = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colors);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayHomeAsUpEnabled(true);

        listView = findViewById(R.id.ColorsRows);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d("xxx","numer klikanego wiersza w ListView = " + i);
                Intent intent = new Intent(ColorsActivity.this, Color.class);
                intent.putExtra("color", list.get(i));
                startActivity(intent);
            }
        });

        Random random = new Random();

        int numberOfColors = Integer.parseInt(getIntent().getStringExtra("numberOfColors"));
        for (int i = 0; i < numberOfColors; i++) {
            int nextInt = random.nextInt(0xffffff + 1);
            String colorCode = String.format("#%06x", nextInt);
            list.add(colorCode);
        }

        ColorAdapter adapter = new ColorAdapter (
            ColorsActivity.this,
            R.layout.color_row_layout,
            list
        );
        listView.setAdapter(adapter);
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