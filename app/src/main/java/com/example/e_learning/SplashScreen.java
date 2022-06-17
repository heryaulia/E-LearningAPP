package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class SplashScreen extends AppCompatActivity {

//    String USERNAME_KEY = "username_key";
//    String username_key = "";
//    String username_key_new = "";
//    DatabaseReference reference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

//        getUsername();

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
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreen.this, MainActivity.class));
                    finish();
                }
            },1000);


        }
        else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(SplashScreen.this, Login.class));
                    finish();
                }
            },1000);

        }
    }

//    private void getUsername() {
//
//        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
//        username_key_new = sharedPreferences.getString(username_key, "");
//
//        if(username_key_new.isEmpty()){
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent goToStart = new Intent(SplashScreen.this, Login.class);
//                    startActivity(goToStart);
//                    finish();
//                }
//            },1000);
//        }else {
//            Handler handler = new Handler();
//            handler.postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                    Intent goToStart = new Intent(SplashScreen.this, MainActivity.class);
//                    startActivity(goToStart);
//                    finish();
//                }
//            },1000);
//        }
//    }


}