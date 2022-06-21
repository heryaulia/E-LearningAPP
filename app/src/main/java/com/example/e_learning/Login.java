package com.example.e_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {


    TextView logoName, sloganName;
    ImageView loginLogo;
    Button btnlogin,btnlupakatasandi, btnregisternow;
    EditText usernameTextView, passwordTextView;
    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    private FirebaseAuth mAuth;
// ...
// Initialize Firebase Auth



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        btnlogin = findViewById(R.id.btn_login);
        btnlupakatasandi = findViewById(R.id.btn_lupa_kata_sandi);
        btnregisternow = findViewById(R.id.btn_register_now);
        logoName = findViewById(R.id.logo_name);
        sloganName = findViewById(R.id.slogan_name);
        loginLogo = findViewById(R.id.loginlogo);
        usernameTextView = findViewById(R.id.input_username);
        passwordTextView = findViewById(R.id.input_password);

        btnregisternow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, RegisterUser.class);
                startActivity(intent);
                finish();
            }
        });

        btnlupakatasandi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, ForgotPassword.class);
                startActivity(intent);
                finish();
            }
        });

        // Set on Click Listener on Sign-in button
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                loginUserAccount();
            }
        });




//        btnlogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//

//
//
////                System.out.println("Click");
////                Intent intent = new Intent(Login.this, MainActivity.class);
////                startActivity(intent);
////                finish();
//            }
//        });

//
//        btnlogin.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//                String xusername = username.getText().toString();
//                String xpassword = password.getText().toString();
//
////                Log.e("username",xusername);
////                Log.e("password", xpassword);
//
//                if (xusername.length() == 0){
//                    Toast.makeText(getApplicationContext(), "Username Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
//
//                }
//                else{
//                    if(xpassword.isEmpty()){
//                        Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong!", Toast.LENGTH_SHORT).show();
//                    }
//                    else {
//                        reference = FirebaseDatabase.getInstance("https://e-learning-6f17a-default-rtdb.asia-southeast1.firebasedatabase.app/").getReference().child("Users").child(xusername);
//                        reference.addListenerForSingleValueEvent(new ValueEventListener() {
//                            @Override
//                            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                                Log.e("Snapshot", String.valueOf(snapshot));
//
//                                if( snapshot.exists()){
//                                    String passwordAkunDariDatabase = snapshot.child("password").getValue().toString();
//
////                                    Log.e("PasswordDb",passwordAkunDariDatabase);
//                                    if(xpassword.equals(passwordAkunDariDatabase)){
//
//                                            SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
//                                        SharedPreferences.Editor editor = sharedPreferences.edit();
//                                        editor.putString(username_key, username.getText().toString());
//                                        editor.apply();
//
//                                        Intent intent = new Intent(Login.this, MainActivity.class);
//                                        startActivity(intent);
//                                        finish();
//                                    }
//                                    else {
//                                        Toast.makeText(getApplicationContext(), "Password Salah!", Toast.LENGTH_SHORT).show();
//                                    }
//                                }
//                                else {
//                                    Toast.makeText(getApplicationContext(), "Username Belum Terdaftar!", Toast.LENGTH_SHORT).show();
//                                }
//                            }
//
//                            @Override
//                            public void onCancelled(@NonNull DatabaseError error) {
//
//                            }
//                        });
//                    }
//                }
//
//            }
//        });

    }



    private void loginUserAccount() {

        // Take the value of two edit texts in Strings
        String email, password;
        email = usernameTextView.getText().toString();
        password = passwordTextView.getText().toString();

        // validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter email!!",
                            Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(),
                            "Please enter password!!",
                            Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        // signin existing user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(
                                    @NonNull Task<AuthResult> task)
                            {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(),
                                                    "Login successful!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();

//                                    // hide the progress bar
//                                    progressBar.setVisibility(View.GONE);

                                    // if sign-in is successful
                                    // intent to home activity
                                    Intent intent
                                            = new Intent(Login.this,
                                            MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }

                                else {

                                    // sign-in failed
                                    Toast.makeText(getApplicationContext(),
                                                    "Login failed!!",
                                                    Toast.LENGTH_SHORT)
                                            .show();

//                                    // hide the progress bar
//                                    progressbar.setVisibility(View.GONE);
                                }
                            }
                        });

    }

//    void login (String email, String password){
//        mAuth.signInWithEmailAndPassword(email, password)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            // Sign in success, update UI with the signed-in user's information
//                            FirebaseUser user = mAuth.getCurrentUser();
//
//                        } else {
//                            // If sign in fails, display a message to the user.
//                            Toast.makeText(Login.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//
//                        }
//                    }
//                });
//    }


}