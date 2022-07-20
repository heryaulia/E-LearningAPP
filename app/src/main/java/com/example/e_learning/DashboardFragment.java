package com.example.e_learning;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


public class DashboardFragment extends Fragment implements RecyclerViewInterface{

    //Recycle View
    ArrayList<CourseModel> courseModels = new ArrayList<>();

    int[] courseImages = {R.drawable.img_course_teorikomputasi, R.drawable.img_recycleview_2, R.drawable.img_recycleview_3, R.drawable.img_recycleview_4,R.drawable.img_recycleview_5};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        //card view intent timeline today
        CardView cardView1 = view.findViewById(R.id.cardView3);
        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TimelineTodayActivity.class);
                startActivity(intent);
            }
        });

        //Recycle View
        RecyclerView recyclerView = view.findViewById(R.id.mRecyclerView);

        setUpCourseModels();

        Course_RecyclerViewAdapter adapter = new Course_RecyclerViewAdapter(view.getContext(), courseModels,this);

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
            courseModels.add(new CourseModel(courseNames[i], lessonAmounts[i], courseImages[i]));
        }

    }

    @Override
    public void onItemClick(int position) {

        // intent title and image to course activity
        Intent intent = new Intent(getActivity(), CourseActivity.class);

        intent.putExtra("NAME", courseModels.get(position).getCourseName());
        intent.putExtra("IMAGE", courseModels.get(position).getImage());

        startActivity(intent);

    }
}