package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Teacher extends Activity {
    final String group = "Teacher";
    String name;
    String firstName;
    String patronymic;
    String subject;
    String teacherClass;
    int id;//Индивидуальный id
    /*Расписание формата <дата<номер урока,класс>>*/
    HashMap<String, HashMap<Integer,String>> raspicanie;
    HashMap<String,Elective> electives;//элективы


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
    }

    public void setDzClass(String klass, String data, String dz){

    }//Задать дз всем ученикам в классе
    public void setDzIndividual(Learner learner,String data,String dz){
    } //Задаьб дз конкретному ученику

    //Геттеры и сеттеры
    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTeacherClass() {
        return teacherClass;
    }

    public void setTeacherClass(String teacherClass) {
        this.teacherClass = teacherClass;
    }

    public HashMap<String, HashMap<Integer, String>> getRaspicanie() {
        return raspicanie;
    }

    public HashMap<String,Elective> getElectives() {
        return electives;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRaspicanie(HashMap<String, HashMap<Integer, String>> raspicanie) {
        this.raspicanie = raspicanie;
    }

    public void setElectives(HashMap<String,Elective> electives) {
        this.electives = electives;
    }

}
