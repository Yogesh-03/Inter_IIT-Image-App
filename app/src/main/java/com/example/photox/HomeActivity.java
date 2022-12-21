package com.example.photox;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        //ActivityCompat.requestPermissions((HomeActivity.this), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        //ActivityCompat.requestPermissions((HomeActivity.this), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        // Setting default fragment as Home Fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, new HomeFragment()).commit();
        bottomNavigationView.setSelectedItemId(R.id.bottomNavHome);

        // Getting clicked on items of bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment fragment = null;
                switch (item.getItemId()){

                    case R.id.bottomNavHome:
                        fragment = new HomeFragment();
                        break;

                    case R.id.bottomNavPofile:
                        fragment = new ProfileFragment();
                        break;
                }
                assert fragment != null;
                getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragment).commit();
                return true;
            }
        });
    }
}