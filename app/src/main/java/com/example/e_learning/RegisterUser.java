package com.example.e_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegisterUser extends AppCompatActivity {

    public static final String TAG = "TAG";
    private FirebaseAuth mAuth;
    private EditText nameEditText, nimEditText, emailEditText, passwordEditText;
    private Button btnregister, btnloginnow;
    private FirebaseFirestore mStore;
    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        // taking FirebaseAuth instance
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();

        nameEditText = findViewById(R.id.input_full_name_register);
        nimEditText = findViewById(R.id.input_nim_register);
        emailEditText = findViewById(R.id.input_email_register);
        passwordEditText = findViewById(R.id.input_password_register);
        btnregister = findViewById(R.id.btn_register);
        btnloginnow = findViewById(R.id.btn_login_now);

        // Set on Click Listener on Registration button
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                registerNewUser();
            }
        });

        btnloginnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterUser.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    private void registerNewUser() {

//        // show the visibility of progress bar to show loading
//        progressbar.setVisibility(View.VISIBLE);

        // Take the value of two edit texts in Strings
        String email, password, fullName, nim;
        email = emailEditText.getText().toString();
        password = passwordEditText.getText().toString();
        fullName = nameEditText.getText().toString();
        nim = nimEditText.getText().toString();

        // Validations for input email and password
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(getApplicationContext(), "Please enter email!!", Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Please enter password!!", Toast.LENGTH_LONG).show();
            return;
        }

        // create new user or register new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Registration successful!", Toast.LENGTH_LONG).show();
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = mStore.collection("Users").document(userID);
                            Map<String, Object> user = new HashMap<>();
                            user.put("fullName", fullName);
                            user.put("nim", nim);
                            user.put("email",email);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.d(TAG, "onSuccess: User Profile is created" + userID);
                                }
                            });
                            documentReference.set(user).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });

//                            // hide the progress bar
//                            progressBar.setVisibility(View.GONE);

                            // if the user created intent to login activity
                            Intent intent = new Intent(RegisterUser.this, Login.class);
                            startActivity(intent);
                        }
                        else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(getApplicationContext(),"Email Sudah Terdaftar",Toast.LENGTH_LONG).show();
                                // thrown if there already exists an account with the given email address
                            } else if (task.getException() instanceof FirebaseAuthWeakPasswordException) {
                                Toast.makeText(getApplicationContext(),"Password harus Lebih dari 6 karakter",Toast.LENGTH_LONG).show();
                                // thrown if the password is not strong enough
                            }else {
                                // Registration failed
                                Toast.makeText(
                                        getApplicationContext(), "Registration failed!!" + " Please try again later", Toast.LENGTH_LONG).show();

                            }
//                            // hide the progress bar
//                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}