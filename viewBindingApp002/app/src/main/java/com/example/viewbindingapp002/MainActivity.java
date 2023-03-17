package com.example.viewbindingapp002;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.viewbindingapp002.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        activityMainBinding.button.setOnClickListener(v->{
            if (activityMainBinding.input.getText().toString().isEmpty() || !isInteger(activityMainBinding.input.getText().toString())) {
                Snackbar s = Snackbar.make(view, "Trzeba podać liczbę!!!!!!!!!!!!!!!!!!!!", Snackbar.LENGTH_LONG);
                s.setAction("OK", a -> Log.d("xxx", "snack"));
                s.show();
            } else {
                activityMainBinding.text.setText(activityMainBinding.input.getText());

                Intent intent = new Intent(MainActivity.this, ColorsActivity.class);
                intent.putExtra("numberOfColors", activityMainBinding.input.getText().toString());
                startActivity(intent);
            }
        });
    }

    public static boolean isInteger(String s) {
        return isInteger(s,10);
    }

    public static boolean isInteger(String s, int radix) {
        if(s.isEmpty()) return false;
        for(int i = 0; i < s.length(); i++) {
            if(i == 0 && s.charAt(i) == '-') {
                if(s.length() == 1) return false;
                else continue;
            }
            if(Character.digit(s.charAt(i),radix) < 0) return false;
        }
        return true;
    }
}