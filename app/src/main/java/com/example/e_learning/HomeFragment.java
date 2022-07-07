package com.example.e_learning;

import static com.example.e_learning.RegisterUser.TAG;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.concurrent.Executor;


public class HomeFragment extends Fragment {

    private TabLayout tabLayout;
    ImageButton imageButton;
    private ViewPager2 viewPager2;
    private VPAdapter adapter;
    Activity context;
    FirebaseAuth mAuth;
    FirebaseFirestore mStore;
    private String userID;
    TextView tvName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = getActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mAuth = FirebaseAuth.getInstance();
        mStore = FirebaseFirestore.getInstance();
        userID = mAuth.getCurrentUser().getUid();
        tvName = view.findViewById(R.id.textView24);
        imageButton = view.findViewById(R.id.btn_user_profile);

        //fetch name from firestore
        final DocumentReference docRef = mStore.collection("Users").document(userID);
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                tvName.setText(documentSnapshot.getString("fullName"));

                Glide.with(HomeFragment.this).load("https://firebasestorage.googleapis.com/v0/b/e-learning-6f17a.appspot.com/o/images%2F08af4cab-4f79-47b6-84fe-211e7ee779ff.jpg?alt=media&token=ba1deeaf-f6a8-4bd4-b75f-e5d6991a7b80").into(imageButton);

            }
        });

        tabLayout = view.findViewById(R.id.tabLayout);
        viewPager2 = view.findViewById(R.id.viewPagerTab);

        tabLayout.addTab(tabLayout.newTab().setText("Dashboard"));
        tabLayout.addTab(tabLayout.newTab().setText("Upload Task"));


        FragmentManager fragmentManager = getChildFragmentManager();
        adapter = new VPAdapter(fragmentManager, getLifecycle());
        viewPager2.setAdapter(adapter);

        //tabLayout.setTabsFromPagerAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserProfile.class);
                startActivity(intent);
            }
        });
    }
}