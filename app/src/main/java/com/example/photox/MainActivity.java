package com.example.photox;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.google.firebase.auth.FirebaseAuth;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    TextView clickhere;
    Button signInBtn;
    FirebaseAuth mAuth;
    String prevStarted = "yes";

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences sharedpreferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        if (!sharedpreferences.getBoolean(prevStarted, false)) {
            SharedPreferences.Editor editor = sharedpreferences.edit();
            editor.putBoolean(prevStarted, Boolean.TRUE);
            editor.apply();
        } else {
            moveToSecondary();
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clickhere = findViewById(R.id.clickHereSignUp);
        signInBtn = findViewById(R.id.signInBtn);
        mAuth = FirebaseAuth.getInstance();

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

        // Getting clicked on the text click here, if don't have a ccount
        /*clickhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              *//*  File directoryToStore;
                directoryToStore = getBaseContext().getExternalFilesDir("TestFolder");
                if (!directoryToStore.exists()) {
                    if (directoryToStore.mkdir()) ; //directory is created;
                }*//*

                // creating bottom sheet Fragment
                bottom_sheet_signup_frag bottom_sheet_signup_frag = new bottom_sheet_signup_frag();
                bottom_sheet_signup_frag.show(getSupportFragmentManager(), bottom_sheet_signup_frag.getTag());


            }
        });*/
    }


    public void moveToSecondary(){
        // use an intent to travel from one activity to another.
        Intent intent = new Intent(this,HomeActivity.class);
        startActivity(intent);
    }
}