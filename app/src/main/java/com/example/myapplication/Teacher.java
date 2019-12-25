package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class Teacher extends Activity implements View.OnClickListener, Parcelable{
    int id;
    String name;
    String surname;
    String email;
    String phone;
    String qualification;
    Boolean is_admin;
    Integer permit;


    String[] array;
    HashMap<Integer, Integer> hashMap=new HashMap<>();
    LinearLayout.LayoutParams layoutParams;

    public Teacher(){}

    protected Teacher(Parcel in) {
        id = in.readInt();
        name = in.readString();
        surname = in.readString();
        email = in.readString();
        phone = in.readString();
        qualification = in.readString();
        byte tmpIs_admin = in.readByte();
        is_admin = tmpIs_admin == 0 ? null : tmpIs_admin == 1;
        if (in.readByte() == 0) {
            permit = null;
        } else {
            permit = in.readInt();
        }
    }

    public static final Creator<Teacher> CREATOR = new Creator<Teacher>() {
        @Override
        public Teacher createFromParcel(Parcel in) {
            return new Teacher(in);
        }

        @Override
        public Teacher[] newArray(int size) {
            return new Teacher[size];
        }
    };

    Teacher(int id, String name, String surname, String email, String phone, String qualification, Boolean is_admin,int permit){
        this.id=id;
        this.name= name;
        this.surname= surname;
        this.email= email;
        this.phone= phone;
        this.qualification= qualification;
        this.is_admin= is_admin;
        this.permit=permit;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Teacher teacher = (Teacher) getIntent().getParcelableExtra("teacher");
        this.id=teacher.getId();
        this.name= teacher.getName();
        this.surname= teacher.getSurname();
        this.email= teacher.getEmail();
        this.phone= teacher.getPhone();
        this.qualification= teacher.getQualification();
        this.is_admin= teacher.getIs_admin();
        this.permit= teacher.getPermit();


        hashMap.put(1,R.id.mon);
        hashMap.put(2,R.id.tue);
        hashMap.put(3,R.id.wen);
        hashMap.put(4,R.id.thu);
        hashMap.put(5,R.id.frid);


        if(!is_admin) {//Ошибка
            setContentView(R.layout.activity_teacher_admin);
        }
           else {
               setContentView(R.layout.activity_teacher_rasp);
               RaspForTeacher();
        }
    }


    public void RaspForTeacher(){

        layoutParams= new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT);
        //Для учителя
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
        }
    }



    @Override
    public void onClick(View v) {
        if(v.getId() != R.id.btn_make_dz) {
            setContentView(R.layout.activity_for_teacher_make_dz);
            Button btnMakeDz = findViewById(R.id.btn_make_dz);
            TextView textViewDz = findViewById(R.id.textViewDz);
            textViewDz.setText((TextView)f);
            btnMakeDz.setOnClickListener(this);
        }
        else {
            TextView textViewDz = findViewById(R.id.textViewDz);
            EditText editDz = findViewById(R.id.edit_dz);
            String a= editDz.getText().toString();
            editDz.setText(a);
        }
    }


    //Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }

    public Integer getPermit() {
        return permit;
    }

    public void setPermit(Integer permit) {
        this.permit = permit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(qualification);
        dest.writeByte((byte) (is_admin == null ? 0 : is_admin ? 1 : 2));
        if (permit == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(permit);
        }
    }

}
