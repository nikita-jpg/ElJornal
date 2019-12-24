package com.example.myapplication;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class ForTeacherMakeDz extends AppCompatActivity implements View.OnClickListener {

    EditText Dz;
    Button Upg;
    TextView textViewSet,textViewGet;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_teacher_make_dz);

        Intent intent = getIntent();
        //array[0]-предмет,array[1]-дз
        String[] a = intent.getStringArrayExtra("teacher");
        textViewSet = findViewById(R.id.textView2);
        textViewSet.setText(a[0]);
        textViewGet=findViewById(R.id.textView);
        textViewGet.setText(a[1]);
        Dz =  findViewById(R.id.Dz);
        Upg = findViewById(R.id.Btn);
    }

    @Override
    public void onClick(View v) {
        //Запрос к серверу
    }
}
