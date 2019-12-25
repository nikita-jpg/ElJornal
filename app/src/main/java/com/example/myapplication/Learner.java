package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.VideoView;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

public class Learner extends Activity implements View.OnClickListener, Parcelable {
    int id;
    String position;
    String login;
    String password;
    String name;
    String surname;
    String clas;
    int permit;


    VideoView videoPlayer;

    public Learner(){

    }



    public Learner(int id,String name,String surname,String clas,int permit){
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.clas=clas;
        this.permit=permit;
    }


    protected Learner(Parcel in) {
        id = in.readInt();
        position = in.readString();
        login = in.readString();
        password = in.readString();
        name = in.readString();
        surname = in.readString();
        clas = in.readString();
        permit = in.readInt();
    }

    public static final Creator<Learner> CREATOR = new Creator<Learner>() {
        @Override
        public Learner createFromParcel(Parcel in) {
            return new Learner(in);
        }

        @Override
        public Learner[] newArray(int size) {
            return new Learner[size];
        }
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learner);
        //Intent intent =getIntent();

        VideoView videoPlayer;
        videoPlayer =  findViewById(R.id.video_learner_meny);
        Uri myVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.video_learner_meny);
        videoPlayer.setVideoURI(myVideoUri);
        videoPlayer.start( );
        videoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_academic_performance:
                Intent intent = new Intent(this, AcademicPerformanceTable.class);
                 //intent.putExtra("rasp",new Learner(id,position,login,password,name,surname,clas,permit));
                startActivity(intent);
            case R.id.btn_rasp_learner:

        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(position);
        dest.writeString(login);
        dest.writeString(password);
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeString(clas);
        dest.writeInt(permit);
    }
    public String getPosition(){
        return position;
    }
    @Override
    public void onResume() {
        super.onResume();
        videoPlayer.start( );
    }

}
