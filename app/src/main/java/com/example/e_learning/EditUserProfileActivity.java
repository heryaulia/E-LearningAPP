package com.example.e_learning;

import static androidx.fragment.app.FragmentManager.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditUserProfileActivity extends AppCompatActivity {

    private EditText etNama, etNim, etJurusan, etSemester, etEmail, etIpk, etSks;
    private Button btnSaveData;
    private FirebaseAuth mAuth;
    private FirebaseDatabase database;
    private DatabaseReference mDatabase;
    private static final String USER = "user";
    private FirebaseUser user;
    private UserProfileChangeRequest profileUpdates;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user_profile);


        database = FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();

        etNama = findViewById(R.id.et_name_edit_user);
        etNim = findViewById(R.id.et_nim_edit_user);
        etJurusan = findViewById(R.id.et_jurusan_edit_user);
        etSemester = findViewById(R.id.et_semester_edit_user);
        etEmail = findViewById(R.id.et_email_edit_user);
        etIpk = findViewById(R.id.et_ipk_edit_user);
        etSks = findViewById(R.id.et_sks_edit_user);

    }



}
