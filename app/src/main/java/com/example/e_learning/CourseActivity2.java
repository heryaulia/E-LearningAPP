package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

public class CourseActivity2 extends AppCompatActivity {

   private Button btn;
   private CheckBox checkBoxCourse, checkbox_excel, checkbox_word, checkbox_ppt, checkbox_upload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course2);

        btn = findViewById(R.id.button4);
        checkBoxCourse = findViewById(R.id.checkbox_course);
        checkbox_excel = findViewById(R.id.checkbox_excel);
        checkbox_word = findViewById(R.id.checkbox_word);
        checkbox_ppt = findViewById(R.id.checkbox_ppt);
        checkbox_upload = findViewById(R.id.checkbox_uploadtugas);

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(CourseActivity2.this, CourseActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        });

    }
    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox_course:
                if (checked){

                }
            else {

                }
                break;
            case R.id.checkbox_uploadtugas:
                if (checked){
                    Intent intent = new Intent(CourseActivity2.this, UploadTaskActivity.class);
                    startActivity(intent);
                }
        }
    }
}