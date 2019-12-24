package com.example.myapplication;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Server {

    //Авторизация учителя
    @GET("getTeacher")//Возвращает объект класса Teacher
    Call<JSONObject> checkTeacherFromServer(String login,String password);

    //Регистрация учителя
    @POST("setTeacher")//Возвращает объект класса Teacher
    Call<JSONObject> registerTeacherToServer(String login,String password,String name,String surname,String position,String email,String phone,String qualification);




    //Авторизация ученика
    @GET("getLearner")
    Call<JSONObject> checkLearnerFromServer(String login,String password);

    //Регистрация ученика
    @POST("setTeacher")//Возвращает объект класса Teacher
    Call<JSONObject> registerPupilToServer(String login,String password,String name,String surname,String position,String clas);


    //Авторизация родителя
    @GET("getLearner")
    Call<JSONObject> checkParentFromServer(String login,String password);

    //Регистрация родителя
    @POST("setTeacher")//Возвращает объект класса Teacher
    Call<JSONObject> registerParentToServer(String login,String password,String name,String surname,String position,int child_id);







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
