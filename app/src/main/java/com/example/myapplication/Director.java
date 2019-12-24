package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

public class Director extends Activity implements View.OnClickListener {

    int id;
    String position;
    String login;
    String password;
    String name;
    String surname;
    String firstName;
    String clas;


    Button btnTeacher;
    Button btnPupil;
    Button btnParent;
    Button btnTaped;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_director);
        btnTeacher = findViewById(R.id.btn_make_teacher);
        btnPupil = findViewById(R.id.btn_make_pupil);
        btnParent = findViewById(R.id.btn_make_parent);
        btnTaped = findViewById(R.id.btn_make_teacher);

        btnTeacher.setOnClickListener(this);
        btnPupil.setOnClickListener(this);
        btnParent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, Registration.class);
        switch (v.getId()){
            case R.id.btn_make_pupil:
                btnTaped.setBackgroundResource(R.drawable.botton);
                btnTaped=findViewById(R.id.btn_make_pupil);
                btnTaped.setBackgroundResource(R.drawable.botton_taped);
                intent.putExtra("status","Pupil");
                break;
            case R.id.btn_make_teacher:
                btnTaped.setBackgroundResource(R.drawable.botton);
                btnTaped=findViewById(R.id.btn_make_teacher);
                btnTaped.setBackgroundResource(R.drawable.botton_taped);
                intent.putExtra("status","Teacher");
                break;
            case R.id.btn_make_parent:
                btnTaped.setBackgroundResource(R.drawable.botton);
                btnTaped=findViewById(R.id.btn_make_parent);
                btnTaped.setBackgroundResource(R.drawable.botton_taped);
                intent.putExtra("status","Parent");
                break;
        }
        startActivity(intent);
    }
}
