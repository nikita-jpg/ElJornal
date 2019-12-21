package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;

public class Rasp extends AppCompatActivity implements View.OnClickListener {

    public TextView[] textView;
    public static String Dz, DzMath, DzRus, DzGeo, DzBio, DzFiz, DzHim, DzInf;
    private int Code;

    String[] array;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rasp);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10,10,10,10);

        HashMap<Integer, Integer> hashMap=new HashMap<>();
        hashMap.put(1,R.id.mon);
        hashMap.put(2,R.id.tue);
        hashMap.put(3,R.id.wen);
        hashMap.put(4,R.id.thu);
        hashMap.put(5,R.id.frid);

        textView=new TextView[40];

        int tekLay=1;
        int tekText=0;
        //предмет:дз;предмет:дз;
        array = new String[2];
        array[0]="математика:дз1;русский:дзРус1";
        array[1]="математика:дз2;русский:дзРус2";

        //Для ученика
        for(int i=0;i<array.length;i++){
            String[] a= array[i].split(";");
            for(int j=0;j<a.length;j++){
                String[] b = a[j].split(":");
                textView[tekText] = new TextView(this);
                textView[tekText].setText(b[0]+"\n"+b[1]);
                textView[tekText].setTextColor(Color.parseColor("#000000"));
                textView[tekText].setPadding(0,20,0,20);
                textView[tekText].setGravity(Gravity.CENTER);
                textView[tekText].setBackgroundResource(R.drawable.fortextview);
                LinearLayout layout = findViewById(hashMap.get(tekLay));
                layout.addView(textView[tekText],layoutParams);
                tekText++;
            }
            tekLay++;
        }

        //Для учителя
        for(int i=0;i<array.length;i++){
            String[] a= array[i].split(";");
            for(int j=0;j<a.length;j++){
                textView[tekText] = new TextView(this);
                textView[tekText].setText(a[j]);
                textView[tekText].setTextColor(Color.parseColor("#000000"));
                textView[tekText].setPadding(0,20,0,20);
                textView[tekText].setGravity(Gravity.CENTER);
                textView[tekText].setBackgroundResource(R.drawable.fortextview);
                LinearLayout layout = findViewById(hashMap.get(tekLay));
                layout.addView(textView[tekText],layoutParams);
                tekText++;
            }
            tekLay++;
        }


    }

    @Override
    public void onClick(View v) {

    }
}
