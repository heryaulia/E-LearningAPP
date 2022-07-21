package com.example.e_learning;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;

import com.ramotion.paperonboarding.PaperOnboardingFragment;
import com.ramotion.paperonboarding.PaperOnboardingPage;

import java.util.ArrayList;

public class OnBoarding extends AppCompatActivity {

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        fragmentManager = getSupportFragmentManager();

        final PaperOnboardingFragment paperOnboardingFragment = PaperOnboardingFragment.newInstance(getDataForOnBoarding());

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_onboarding, paperOnboardingFragment);
        fragmentTransaction.commit();
    }

    private ArrayList<PaperOnboardingPage> getDataForOnBoarding() {

        PaperOnboardingPage src1 = new PaperOnboardingPage("E-Kampus-Ku merupakan sistem layanan pembelajaran offline dan online. E-Learning melayani pembelajaran dan pelatihan mahasiswa, dosen, dan tenaga kependidikan.", "", Color.parseColor("#ffffff"), R.drawable.imgonboarding2, R.drawable.indicator_selected);
        PaperOnboardingPage src2 = new PaperOnboardingPage("App E-KampusKu ini merupakan pengembangan dari web E-learning Universitas  yang dilakukan  oleh Mahasiswa Studi Independen nongsa digital park .    ", "", Color.parseColor("#ffffff"), R.drawable.imgonboarding3, R.drawable.indicator_selected);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(src1);
        elements.add(src2);
        return elements;

    }
}