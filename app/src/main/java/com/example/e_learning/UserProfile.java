package com.example.e_learning;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.protobuf.Value;

public class UserProfile extends AppCompatActivity {


    private TextView tvName, tvNim, tvEmail,tvJurusan,tvSemester, tvIpk,tvSks;
    Button btnSignout, btnEditUser;
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;
    private String userID;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        tvName = findViewById(R.id.tv2_name_user);
        tvNim = findViewById(R.id.tv2_nim_user);
        tvEmail = findViewById(R.id.tv2_email_user);
//        tvJurusan =findViewById(R.id.tv2_jurusan_user);
//        tvSemester = findViewById(R.id.tv2_semester_user);
//        tvIpk = findViewById(R.id.tv2_ipk_user);
//        tvSks = findViewById(R.id.tv2_sks_user);
        btnSignout = findViewById(R.id.btn_logout);
        btnEditUser = findViewById(R.id.btn_profile);

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

                Log.d("Value", String.valueOf(value.getString("email")));

            }
        });
//        if(firebaseUser == null){
//            Toast.makeText(UserProfile.this,"Something Wrong! user detaits are not available right now", Toast.LENGTH_LONG).show();
//        }else {
//            showUserProfile(firebaseUser);
//        }
//
//        tvEmail.setText(firebaseUser.getEmail());

        btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserProfile.this, EditUserProfileActivity.class);
                startActivity(intent);
            }
        });


        btnSignout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                signOutUser();

            }
        });
    }

//    private void showUserProfile(FirebaseUser firebaseUser) {
//        String userID = firebaseUser.getUid();
//
//        //Extracting user reference from database for regsitered users
//        DatabaseReference referenceProfile = FirebaseDatabase.getInstance().getReference("Registered Users");
//        referenceProfile.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//    }

    private void signOutUser() {
        Intent intent = new Intent(UserProfile.this, Login.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}