package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Learner extends Activity {
    final String group = "Learner";
    String Name;
    String firstName;
    String patronymic;
    int age;
    String klass;
    int id;//Индивидуальный id


    /*Расписание формата <дата<предмет-дз,предмет-дз,>,дата>*/
    HashMap<String,HashMap<String,String>> raspicanie;
    HashMap<String,Elective> electives;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learner);
    }



    public boolean setElectives(String electiveName){
        if(electives.get(electiveName).putLearner(this) == true) return true;
        else return false;
    }//вступить в эллектив
    public void deleteElectives(){

    }//удалиться из эллектив
    public HashMap<String,HashMap<String,String>> getRaspicanie(){
        return raspicanie;
    }//получить расписание


    //Геттеры и сеттеры
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGroup() {
        return group;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getKlass() {
        return klass;
    }

    public void setKlass(String klass) {
        this.klass = klass;
    }

    public void setRaspicanie(HashMap<String,HashMap<String,String>> raspicanie) {
        this.raspicanie = raspicanie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setElectives(HashMap<String,Elective> electives) {
        this.electives = electives;
    }

    public HashMap<String,Elective> getElectives(){
        return electives;
    }//Список эллективов

}
