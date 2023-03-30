package com.example.viewbindingapp2_002;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;

import com.example.viewbindingapp2_002.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = activityMainBinding.getRoot();
        setContentView(view);

        activityMainBinding.toolbar.setNavigationOnClickListener(v->{
            activityMainBinding.drawerLayout.openDrawer(GravityCompat.START);
        });

        activityMainBinding.navigationView.setNavigationItemSelectedListener(item->{
            activityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);

            int id = item.getItemId();

            switch(id){
                case R.id.home:
                    replaceFragment(new home());
                    break;
                case R.id.settings:
                    replaceFragment(new settings());
                    break;
                case R.id.share:
                    replaceFragment(new share());
                    break;
            }

            return true;
        });


    }

    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit();
    }

}