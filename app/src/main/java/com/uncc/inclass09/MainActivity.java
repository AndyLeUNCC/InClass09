package com.uncc.inclass09;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements GradeListFragment.GradeListFragmentListener {

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
                .commit();
    }

}