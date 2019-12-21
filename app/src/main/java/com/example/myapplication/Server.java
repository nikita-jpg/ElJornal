package com.example.myapplication;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Server {

    //Общие методы
    @GET("checkPerson")//сли человек существует,то возвращает название группы,иначе ""
    Call<String> checkPersonFromServer(String login,String password);

    @GET("getRasp")//Возвращает расписание
    Call<String[]> getRaspFromServer(int id,int date);
    //"rasp":["прдмет","Предмет","","","","","","","","",""]

    //Для ученика
    @GET("getLearner")//Возвращает объект класса Learner
    Call<Learner> getLearnerFromServer(String login,String password);

    @GET("setLrearnerElective")//Если есть места то добавляет школьника на электив и возвращает true, иначе false
    Call<Boolean> setLearnerElectiveToServer(int id,String electiveName);

    @POST("deleteLearnerElective")//Возвращает объект класса School,но только поля доступные школьнику
    Call<Boolean> deleteLearnerElectiveFromServer(int id,String electiveName);


    //Для учителя
    @GET("getTeacher")//Возвращает объект класса Teacher
    Call<Teacher> getTeacherFromServer(String login,String password);

    @GET("getTEacherSchool")//Возвращает объект класса School,но только пол доступные школьнику
    Call<School> getTeacherSchoolFromServer();

    @POST("setDz")//Ставим дз ученику
    void setDzToServer(int id,String predmet,String dz);

    @POST("setstimate")//Ставим оценку ученику
    void setEstimateToServer(int id,String predmet,int estimate);
}
