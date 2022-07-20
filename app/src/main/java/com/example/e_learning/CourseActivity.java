package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CourseActivity extends AppCompatActivity {

    TextView textTitle;
    ImageView imageView;
    CheckBox checkBox, checkBox2, checkBox3, checkBox4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        //untuk ambil data dari intent course fragment
        String name = getIntent().getStringExtra("NAME");
        int image = getIntent().getIntExtra("IMAGE", 0);

        textTitle = findViewById(R.id.detailTitle);
        imageView = findViewById(R.id.iv_gambar);

        textTitle.setText(name);
        imageView.setImageResource(image);



        //intent cardview ke course activity 2
        CardView cardView1 = (CardView) findViewById(R.id.cv_course_pertama);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseActivity.this, CourseActivity2.class);
                startActivity(intent);
                finish();
            }
        });


        //checked text view
        checkBox = findViewById(R.id.checkbox_course);
        checkBox2 = findViewById(R.id.checkbox_course2);
        checkBox3 = findViewById(R.id.checkbox_course3);
        checkBox4 = findViewById(R.id.checkbox_course4);


    }
}