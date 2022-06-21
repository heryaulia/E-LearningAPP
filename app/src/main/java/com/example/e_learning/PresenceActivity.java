package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PresenceActivity extends AppCompatActivity {

    Button btnPresence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presence);

        btnPresence = findViewById(R.id.button7);

        btnPresence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PresenceActivity.this, PresensiFragment.class);
                startActivity(intent);
            }
        });
    }
}