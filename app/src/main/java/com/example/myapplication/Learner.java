package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Learner extends Activity implements View.OnClickListener {
    final String group = "Learner";
    String Name;
    String firstName;
    String patronymic;
    int age;
    String klassName;
    int id;//Индивидуальный id
    String[] a;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learner);
    }

    public void getRaspicanie(){
    }//получить расписание

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_academic_performance:
                Intent intent=new Intent(this,AcademicPerformanceTable.class);
                intent.putExtra("",a);
                startActivity(intent);
        }
    }

}
