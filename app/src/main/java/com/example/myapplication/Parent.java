package com.example.myapplication;

import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.VideoView;

import androidx.annotation.Nullable;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Parent extends Activity implements View.OnClickListener, Parcelable {

    int id;
    String name;
    String surname;
    int child_id;
    VideoView videoPlayer;

    public Parent(){
    };

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learner);
        Parent parent=getIntent().getParcelableExtra("parent");
        this.id=parent.getId();
        this.name=parent.getName();
        this.surname=parent.getSurname();
        this.child_id=getChild_id();


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

    Parent(int id,String name,String surname,int child_id){
        this.id=id;
        this.name=name;
        this.surname=surname;
        this.child_id=child_id;
    }

    protected Parent(Parcel in) {
        this.id=in.readInt();
        this.name=in.readString();
        this.surname=in.readString();
        this.child_id=in.readInt();
    }

    public static final Creator<Parent> CREATOR = new Creator<Parent>() {
        @Override
        public Parent createFromParcel(Parcel in) {
            return new Parent(in);
        }

        @Override
        public Parent[] newArray(int size) {
            return new Parent[size];
        }
    };

    @Override
    public void onClick(View v) {

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
        dest.writeInt(child_id);
    }

    //Setters and Getters
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

    public int getChild_id() {
        return child_id;
    }

    public void setChild_id(int child_id) {
        this.child_id = child_id;
    }

    public static Creator<Parent> getCREATOR() {
        return CREATOR;
    }
    @Override
    public void onResume() {
        super.onResume();
        videoPlayer.start( );
    }

   /* public void onBackPressed() {
       Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
                .build();
       Server logout = retrofit.create(Server.class);
       Void messege = logout.logout();
    }
    */

}
