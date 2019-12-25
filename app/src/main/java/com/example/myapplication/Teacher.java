package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

public class Teacher extends Activity implements Parcelable{
    int id;
    String name;
    String surname;
    String email;
    String phone;
    String qualification;
    Boolean is_admin;
    Integer permit;

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
        this.name= this.name;
        this.surname= this.surname;
        this.email= this.email;
        this.phone= this.phone;
        this.qualification= this.qualification;
        this.is_admin= this.is_admin;
        this.permit=permit;
    }

    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        Teacher teacher = (Teacher) getIntent().getParcelableExtra("teacher");
        this.id=teacher.getId();
        this.name= teacher.getName();
        this.surname= teacher.getSurname();
        this.email= teacher.getEmail();
        this.phone= teacher.getPhone();
        this.qualification= teacher.getQualification();
        this.is_admin= teacher.getIs_admin();
        this.permit= teacher.getPermit();
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
