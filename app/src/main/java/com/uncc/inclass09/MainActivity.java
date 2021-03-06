package com.uncc.inclass09;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
/**
 * Inclass09
 * File Name:MainActivity.java
 * Full Name of the student:
 * 1. Sai Kandimalla
 * 2. Andy Le
 */

public class MainActivity extends AppCompatActivity implements GradeListFragment.GradeListFragmentListener, AddCourseFragment.AddCourseFragmentListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerView, new GradeListFragment())
                .commit();
    }

    @Override
    public void AddCourseFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerView, AddCourseFragment.newInstance())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void BackFragment() {
        getSupportFragmentManager().popBackStack();
    }


}