package com.example.myapplication;

import java.util.ArrayList;
import java.util.HashMap;

public class Elective {
    final String group = "Elective";
    String name;
    /*Расписание формата <дата<номера уроков>>*/
    HashMap<String,ArrayList<Integer>> raspicanie;
    ArrayList<Learner> learners;//Участники
    Teacher mainTeacher;//Преподаватель курсов
    int maxQuanity;//максимальное колличество участников
    int nowQuanity;//текущее колличество участников


    public boolean putLearner(Learner pupil){
        if(nowQuanity >= maxQuanity) return false;
        else return true;
    }//добавить участника
    public void deleteLearner(int id){

    }//Удалить участника

    //Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, ArrayList<Integer>> getRaspicanie() {
        return raspicanie;
    }

    public void setRaspicanie(HashMap<String, ArrayList<Integer>> raspicanie) {
        this.raspicanie = raspicanie;
    }

    public ArrayList<Learner> getLearners() {
        return learners;
    }

    public void setLearners(ArrayList<Learner> learners) {
        this.learners = learners;
    }

    public Teacher getMainTeacher() {
        return mainTeacher;
    }

    public void setMainTeacher(Teacher mainTeacher) {
        this.mainTeacher = mainTeacher;
    }

    public int getMaxQuanity() {
        return maxQuanity;
    }

    public void setMaxQuanity(int maxQuanity) {
        this.maxQuanity = maxQuanity;
    }

    public int getNowQuanity() {
        return nowQuanity;
    }

}
