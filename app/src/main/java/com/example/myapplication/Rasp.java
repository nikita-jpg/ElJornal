package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.HashMap;

public class Rasp extends AppCompatActivity implements View.OnClickListener {

    AlertDialog.Builder bilder;
    AlertDialog al;

    public TextView[] textViews;
    public static String Dz, DzMath, DzRus, DzGeo, DzBio, DzFiz, DzHim, DzInf;
    private int Code;
    HashMap<Integer, Integer> hashMap=new HashMap<>();
    LinearLayout.LayoutParams layoutParams;
    LinearLayout.LayoutParams layoutParamsBtn;
    LinearLayout.LayoutParams layoutParamsPred;
    LinearLayout.LayoutParams layoutParamsOc;
    String[] array;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rasp_pupil);


        i=0;

        layoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,4,0,4);

        layoutParamsBtn = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        layoutParamsBtn.weight=1;

        layoutParamsBtn.setMargins(14,0,14,0);

        layoutParamsPred = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        layoutParamsPred.setMargins(10,0,0,0);

        layoutParamsOc = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        layoutParamsOc.setMargins(0,0,10,0);


        textViews=new TextView[64];

        hashMap.put(1,R.id.mon);
        hashMap.put(2,R.id.tue);
        hashMap.put(3,R.id.wen);
        hashMap.put(4,R.id.thu);
        hashMap.put(5,R.id.frid);

        Intent intent=getIntent();
        //Получаем объект.Если он равен "Learner",то первый метод,наче второй
        RaspForLearner((TableLayout) findViewById(R.id.tab_monday),"Понедельник");
        RaspForLearner((TableLayout) findViewById(R.id.tab_tuesday),"Понедельник");
        RaspForLearner((TableLayout) findViewById(R.id.tab_wensday),"Понедельник");
        RaspForLearner((TableLayout) findViewById(R.id.tab_thursday),"Понедельник");
        RaspForLearner((TableLayout) findViewById(R.id.tab_friday),"Понедельник");


        bilder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.activity_alert_dz, null,true);
        bilder.setView(view);
        al = bilder.create();
        al.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


    }
    //Для ученикаy
    public void RaspForLearner(TableLayout layoutRoot,String day){
        int j=i+8;
        TableRow tableRowDay = new TableRow(this);
        TextView textDay= new TextView(this);
        textDay.setTextColor(Color.parseColor("#000002"));
        textDay.setText(day);
        tableRowDay.addView(textDay,l);
        while(i<j){
            TableRow tableRow = new TableRow(this);
            textViews[i] = new TextView(this);
            textViews[i].setText("1.Математика");
            textViews[i].setTextColor(Color.parseColor("#000002"));
            TextView button=new TextView(this);
            button.setBackgroundResource(R.drawable.botton_for_learner_reg);
            button.setClickable(true);
            button.setOnClickListener(this);
            TextView textView=new TextView(this);
            textView.setTextColor(Color.parseColor("#000002"));
            textView.setText("Оценки");
            tableRow.addView(textViews[i],layoutParamsPred);
            tableRow.addView(button,layoutParamsBtn);
            tableRow.addView(textView,layoutParamsOc);
            layoutRoot.addView(tableRow,layoutParams);
            i++;
        }
        /*
        textView=new TextView[40];

        int tekLay=1;
        int tekText=0;
        //предмет:дз;предмет:дз;
        array = new String[2];
        array[0]="математика:дз1;русский:дзРус1";
        array[1]="математика:дз2;русский:дзРус2";
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
        */
    }

    public void RaspForTeacher(){







        //Для учителя
        /*
        Button[] btns=new Button[40];
        int tekLay=1;
        int tekBtn=0;
        //предмет;предмет;
        array = new String[2];//Парсим классы
        array[0]="11Б;8В;";
        array[1]="10А;11В;";
        for(int i=0;i<array.length;i++){
            String[] a= array[i].split(";");
            for(int j=0;j<a.length;j++){
                btns[tekBtn] = new Button(this);
                btns[tekBtn].setOnClickListener(this);
                btns[tekBtn].setText(a[j]);
                btns[tekBtn].setTextColor(Color.parseColor("#000000"));
                btns[tekBtn].setPadding(0,20,0,20);
                btns[tekBtn].setGravity(Gravity.CENTER);
                btns[tekBtn].setBackgroundResource(R.drawable.fortextview);
                LinearLayout layout = findViewById(hashMap.get(tekLay));
                layout.addView(btns[tekBtn],layoutParams);
                tekBtn++;
            }
            tekLay++;
        }*/
    }

    @Override
    public void onClick(View v) {
        al.show();
    }
}
