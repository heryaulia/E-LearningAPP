package com.example.e_learning;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class PresensiFragment extends Fragment {

    //Recycle View
    ArrayList<CourseModel> courseModels = new ArrayList<>();

    int[] courseImages = {R.drawable.img_course_teorikomputasi};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_presensi, container, false);

        Button btnPresenceHere = view.findViewById(R.id.button3);

        btnPresenceHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PresenceActivity.class);
                startActivity(intent);
            }
        });

        //Recycle View
        RecyclerView recyclerView = view.findViewById(R.id.mRecyclerView);

        setUpCourseModels();

        Course_RecyclerViewAdapter adapter = new Course_RecyclerViewAdapter(view.getContext(), courseModels);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

    //Recycle View
    private void setUpCourseModels(){
        String[] courseNames = getResources().getStringArray(R.array.course_name_full_text);
        String[] lessonAmounts = getResources().getStringArray(R.array.lesson_amount_full_text);

        Log.e("Course Name", String.valueOf(courseNames.length));

        for (int i = 0; i <= (courseNames.length -1); i++){
            courseModels.add(new CourseModel(courseNames[i], lessonAmounts[i], courseImages[0]));
        }

    }
}