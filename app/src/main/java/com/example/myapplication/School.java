package com.example.myapplication;

import java.util.HashMap;

public class School {
    HashMap<Integer,Learner> learners;
    HashMap<String,Elective> electives;
    HashMap<Integer,Teacher> teachers;
    HashMap<String,Klass> klassies;
    HashMap<Integer,Service> services;
    String name;
    String adress;

    public HashMap<Integer, Learner> getLearners() {
        return learners;
    }

    public void putLearner(Learner learner) {
    }

    public HashMap<String, Elective> getElectives() {
        return electives;
    }

    public void putElective(String name, Elective elective) {
        electives.put(name,elective);
    }

    public void deleteElective(String name) {
        electives.remove(name);
    }

    public HashMap<Integer, Teacher> getTeachers() {
        return teachers;
    }

    public void putTeachers(int id, Teacher teacher) {
        teachers.put(id,teacher);
    }

    public void deleteTeachers(int id, Teacher teacher) {
        teachers.remove(id);
    }

    public HashMap<String, Klass> getClassies() {
        return klassies;
    }

    public void putClassies(String name,Klass klass) {
        klassies.put(name,klass);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
