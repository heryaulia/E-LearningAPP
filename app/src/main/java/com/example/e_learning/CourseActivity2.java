package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;

public class CourseActivity2 extends AppCompatActivity {

   private Button btn;
   private CheckedTextView checkedTextView, checkedTextView2, checkedTextView3, checkedTextView4, checkedTextView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course2);

        btn = findViewById(R.id.button4);
        checkedTextView = findViewById(R.id.checkedTextView);
        checkedTextView2 = findViewById(R.id.textView25);
        checkedTextView3 = findViewById(R.id.textView26);
        checkedTextView4 = findViewById(R.id.textView27);
        checkedTextView5 = findViewById(R.id.textView28);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CourseActivity2.this, CourseActivity.class);
                startActivity(intent);
                finish();
            }
        });

        checkedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkedTextView.isChecked()){
                    checkedTextView.setChecked(false);
                }
                else checkedTextView.setChecked(true);
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

        checkedTextView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkedTextView5.toggle();
                if(checkedTextView5.isChecked()){
                }else {
                    Intent intent = new Intent(CourseActivity2.this, UploadTaskActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}