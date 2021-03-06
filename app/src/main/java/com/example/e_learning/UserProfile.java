package com.example.e_learning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.protobuf.Value;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserProfile extends AppCompatActivity {


    private TextView tvName, tvNim, tvEmail,tvJurusan,tvSemester, tvIpk,tvSks;
    Button btnSignout, btnEditUser;
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;
    Uri avatarUrl;
    FirebaseStorage firebaseStorage;
    StorageReference storageReference;
    private String userID;
    private CircleImageView profileimageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        profileimageView = findViewById(R.id.profile_image);
        tvName = findViewById(R.id.tv2_name_user);
        tvNim = findViewById(R.id.tv2_nim_user);
        tvEmail = findViewById(R.id.tv2_email_user);
//        tvJurusan =findViewById(R.id.tv2_jurusan_user);
//        tvSemester = findViewById(R.id.tv2_semester_user);
//        tvIpk = findViewById(R.id.tv2_ipk_user);
//        tvSks = findViewById(R.id.tv2_sks_user);
        btnSignout = findViewById(R.id.btn_logout);
        btnEditUser = findViewById(R.id.btn_profile);

        firebaseStorage = FirebaseStorage.getInstance();
        storageReference = firebaseStorage.getReference();
        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();

        DocumentReference documentReference = mStore.collection("Users").document(userID);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                tvName.setText(value.getString("fullName"));
                tvNim.setText(value.getString("nim"));
                tvEmail.setText(value.getString("email"));

                Glide.with(UserProfile.this).load(value.getString("avatar")).placeholder(R.drawable.avatar).into(profileimageView);
            }
        });

        btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, EditUserProfileActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOutUser();
            }
        });
    }

    private void signOutUser() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(UserProfile.this, Login.class);
        startActivity(intent);
        finish();
    }
}