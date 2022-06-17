package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.database.DatabaseReference;

public class SplashScreen extends AppCompatActivity {

    String USERNAME_KEY = "username_key";
    String username_key = "";
    String username_key_new = "";
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        getUsername();

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashScreen.this,HomeFragment.class);
//                startActivity(intent);
//                finish();
//
//            }
//        },SPLASH_SCREEN);
    }

    public void getUsername(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");

        if(username_key_new.isEmpty()){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent goToStart = new Intent(SplashScreen.this, Login.class);
                    startActivity(goToStart);
                    finish();
                }
            },1000);
        }else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent goToStart = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(goToStart);
                    finish();
                }
            },1000);
        }

    }
}