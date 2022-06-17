package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterUser extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText Name, Nim, Email, Password;
    Button btnregister, btnloginnow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        btnregister = findViewById(R.id.btn_register);
        btnloginnow = findViewById(R.id.btn_login_now);

        btnloginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterUser.this, Login.class);
                startActivity(intent);
            }
        });
    }
}