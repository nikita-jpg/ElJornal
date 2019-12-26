package com.example.myapplication;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Server {

    //Авторизация учителя
    @GET("File.json")//Возвращает объект класса Teacher
    Call<JSONObject> checkTeacherFromServer();

    //Регистрация учителя
    @POST("setTeacher")//Возвращает объект класса Teacher
    Call<JSONObject> registerTeacherToServer(String login,String password,String name,String surname,String position,String email,String phone,String qualification);


    //Авторизация ученика
    @GET("aut/login")
    Call<JSONObject> checkPupilFromServer();

    //Регистрация ученика
    @POST("auth/register")//Возвращает объект класса Teacher
    Call<JSONObject> registerPupilToServer(String login,String password,String name,String surname,String position,String clas);


    //Регистрация родителя
    @POST("auth/register")//Возвращает объект класса Teacher
    Call<JSONObject> registerParentToServer(String login,String password,String name,String surname,String position,int child_id);

    //Выход
    /*@POST("auth/logout")
    Void logout();*/



    @GET("setLrearnerElective")//Если есть места то добавляет школьника на электив и возвращает true, иначе false
    Call<Boolean> setLearnerElectiveToServer(int id,String electiveName);

    @POST("deleteLearnerElective")//Возвращает объект класса School,но только поля доступные школьнику
    Call<Boolean> deleteLearnerElectiveFromServer(int id,String electiveName);

    //Для учителя
    @GET("getTeacher")//Возвращает объект класса Teacher
    Call<Teacher> getTeacherFromServer(String login,String password);


    @POST("setDz")//Ставим дз ученику
    void setDzToServer(int id,String predmet,String dz);

    @POST("setstimate")//Ставим оценку ученику
    void setEstimateToServer(int id,String predmet,int estimate);
}
