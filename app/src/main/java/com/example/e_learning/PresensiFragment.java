package com.example.e_learning;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class PresensiFragment extends Fragment {

//    //Recycle View
//    ArrayList<CourseModel> courseModels = new ArrayList<>();
//
//    int[] courseImages = {R.drawable.img_course_teorikomputasi, R.drawable.img_recycleview_2, R.drawable.img_recycleview_3, R.drawable.img_recycleview_4,R.drawable.img_recycleview_5};
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;
    private String userID;
    TextView tvName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_presensi, container, false);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        tvName = view.findViewById(R.id.textView6);

        //fetch name from firestore
        final DocumentReference docRef = mStore.collection("Users").document(userID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                tvName.setText(documentSnapshot.getString("fullName"));
            }
        });

        Button btnPresenceHere = view.findViewById(R.id.button3);

        CardView cardView1 = view.findViewById(R.id.cardView2);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ViewPresenceActivity.class);
                startActivity(intent);
            }
        });

        btnPresenceHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PresenceActivity.class);
                startActivity(intent);
            }
        });

//        //Recycle View
//        RecyclerView recyclerView = view.findViewById(R.id.mRecyclerView);
//
//        setUpCourseModels();
//
//        Course_RecyclerViewAdapter adapter = new Course_RecyclerViewAdapter(view.getContext(), courseModels);
//
//        recyclerView.setAdapter(adapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        return view;
    }

//    //Recycle View
//    private void setUpCourseModels(){
//        String[] courseNames = getResources().getStringArray(R.array.course_name_full_text);
//        String[] lessonAmounts = getResources().getStringArray(R.array.lesson_amount_full_text);
//
//        Log.e("Course Name", String.valueOf(courseNames.length));
//
//        for (int i = 0; i <= (courseNames.length -1); i++){
//            courseModels.add(new CourseModel(courseNames[i], lessonAmounts[i], courseImages[i]));
//        }
//
//    }
}