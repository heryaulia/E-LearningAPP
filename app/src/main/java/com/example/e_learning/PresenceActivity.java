package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class PresenceActivity extends AppCompatActivity {

    Button btnPresence;
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;
    private String userID;
    TextView tvName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presence);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        tvName = findViewById(R.id.textView16);

        //fetch name from firestore
        final DocumentReference docRef = mStore.collection("Users").document(userID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                tvName.setText(documentSnapshot.getString("fullName"));
            }
        });

        btnPresence = findViewById(R.id.button7);

        btnPresence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(PresenceActivity.this, "Button udah Ke Klik", Toast.LENGTH_SHORT).show();
            }
        });
    }
}