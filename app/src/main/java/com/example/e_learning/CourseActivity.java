package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CourseActivity extends AppCompatActivity {

    TextView textTitle;
    ImageView imageView;
    CheckedTextView checkedTextView1, checkedTextView2, checkedTextView3, checkedTextView4;

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


        //intent cardview pertama ke course activity 2
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
        checkedTextView1 = findViewById(R.id.checkedTextView);
        checkedTextView2 = findViewById(R.id.checkedTextView2);
        checkedTextView3 = findViewById(R.id.checkedTextView3);
        checkedTextView4 = findViewById(R.id.checkedTextView4);

        checkedTextView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedTextView1.toggle();
                if(checkedTextView1.isChecked()){
                }else {
                }
            }
        });

        checkedTextView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkedTextView2.isChecked()){
                    checkedTextView2.setChecked(false);
                }
                else checkedTextView2.setChecked(true);
            }
        });

        checkedTextView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkedTextView3.isChecked()){
                    checkedTextView3.setChecked(false);
                }
                else checkedTextView3.setChecked(true);
            }
        });

        checkedTextView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkedTextView4.isChecked()){
                    checkedTextView4.setChecked(false);
                }
                else checkedTextView4.setChecked(true);
            }
        });
    }
}