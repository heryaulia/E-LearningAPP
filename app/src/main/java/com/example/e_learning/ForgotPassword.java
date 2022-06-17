package com.example.e_learning;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class ForgotPassword extends AppCompatActivity {

    private Button btnresetpassword;
    private EditText emailInput;
    private FirebaseAuth authProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);



        emailInput = findViewById(R.id.et_email_input_reset_password);
        btnresetpassword = findViewById(R.id.btn_reset_password);

        btnresetpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // get email from login to reset password
                String email = emailInput.getText().toString();

                System.out.println(email);

                if (email.isEmpty()){

                    Toast.makeText(ForgotPassword.this, "Please Enter your Resgistered Email", Toast.LENGTH_SHORT).show();
                    emailInput.setError("Email Is Required to Reset Password");
                    emailInput.requestFocus();
//                }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
//                        Toast.makeText(ForgotPassword.this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
//                    emailInput.setError("Valid Email Is Required to Reset Password");
//                    emailInput.requestFocus();

                }else {
                    resetpassword_method(email);
                }

            }
        });
    }

    private void resetpassword_method(String email) {
        authProfile = FirebaseAuth.getInstance();
        authProfile.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Check your Email to Reset Password", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(ForgotPassword.this, Login.class);

                    // clear stack to prevent user coming back to forgotpassword activity
                    intent.setFlags(intent.FLAG_ACTIVITY_CLEAR_TOP | intent.FLAG_ACTIVITY_CLEAR_TASK | intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    finish();


                }

            }
        });
    }
}