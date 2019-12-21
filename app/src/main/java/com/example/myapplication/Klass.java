package com.example.myapplication;

import java.util.HashMap;

public class Klass {
    String name;
    HashMap<String, HashMap<Integer,String>> raspicanie;//Расписание класса
    int quanities =0;//Колличество учеников в классе
    HashMap<Integer,Learner> pupils;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, HashMap<Integer, String>> getRaspicanie() {
        return raspicanie;
    }

    public void getRaspicanie(HashMap<String, HashMap<Integer, String>> raspicanie) {
        this.raspicanie = raspicanie;
    }

    public int getQuanities() {
        return quanities;
    }

    public HashMap<Integer, Learner> getPupils() {
        return pupils;
    }

    public void putPupils(HashMap<Integer, Learner> pupils) {
        this.pupils = pupils;
        quanities++;
    }

    public void deletePupils(HashMap<Integer, Learner> pupils) {
        this.pupils = pupils;
        quanities--;
    }
}
