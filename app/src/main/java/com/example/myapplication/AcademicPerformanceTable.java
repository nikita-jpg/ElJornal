package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class AcademicPerformanceTable extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academic_performance_activity);

        Intent intent = getIntent();
        String array[] = intent.getStringArrayExtra("AcademicTable");
    }
}
