package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.intellij.lang.annotations.Language;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AutorizationActivity extends Activity implements View.OnClickListener {

    Button aut;
    EditText name;
    EditText password;
    Button btnTaped;
    Button btnTeacher,btnLearner;
    String tekStatus;

    Retrofit retrofit;
    Server server;
    Call<JSONObject> jsonObjectCall;
    JSONObject jsonObject;
    Toast backToast;
    Intent intent;

    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorization);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        a ="{\"id\":12,\"position\":\"Teacher\",\"child_id\":12,\"password\":\"12\",\"name\":\"12\",\"surname\":\"12\",\"email\":\"12\",\"phone\":\"12\",\"qualification\":\"12\",\"is_admin\":true,\"permit\":12,\"clas\":\"12\"}";


        //Видео на фоне
        VideoView videoPlayer;
        videoPlayer =  findViewById(R.id.video_autorization_view);
        Uri myVideoUri= Uri.parse( "android.resource://" + getPackageName() + "/" + R.raw.video_autorization);
        videoPlayer.setVideoURI(myVideoUri);
        videoPlayer.start( );
        videoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        tekStatus="";

        aut=findViewById(R.id.btn_aut);
        aut.setOnClickListener(this);
        name=findViewById(R.id.ActAutorizationNameTxt);
        password=findViewById(R.id.ActAutorizationPasswordTxt);
        btnTaped=findViewById(R.id.btn_aut_learner);

        btnLearner=findViewById(R.id.btn_aut_learner);
        btnTeacher=findViewById(R.id.btn_aut_teacher);
        btnLearner.setOnClickListener(this);
        btnTeacher.setOnClickListener(this);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        server = retrofit.create(Server.class);
    }

    public int checkJson() throws JSONException {
        //return jsonObject.getInt("resultCode");
        return 0;
    }

    public void getJsonObject() throws IOException, JSONException {
        jsonObject=new JSONObject(a);
        //Response<JSONObject> jsonObject = jsonObjectCall.execute();
    }

    public Teacher startTeacher() throws JSONException {
        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String surname= jsonObject.getString("surname");
        String email= jsonObject.getString("email");
        String phone= jsonObject.getString("phone");
        String qualification= jsonObject.getString("qualification");
        Boolean is_admin= jsonObject.getBoolean("is_admin");
        Integer permit= jsonObject.getInt("permit");

        Teacher teacher = new Teacher(id,name,surname,email,phone,qualification,is_admin,permit);

        return teacher;
    }
    public Learner startLearner() throws JSONException {
        int id = jsonObject.getInt("id");
        String name = jsonObject.getString("name");
        String surname= jsonObject.getString("surname");
        String clas = jsonObject.getString("clas");
        Integer permit= jsonObject.getInt("permit");

        Learner learner = new Learner(id,name,surname,clas,permit);

        return learner;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_aut_learner:
                btnTaped.setBackgroundResource(R.drawable.botton);
                btnTaped=(Button)findViewById(R.id.btn_aut_learner);
                btnTaped.setBackgroundResource(R.drawable.botton_taped);
                tekStatus="learner";
                break;
            case R.id.btn_aut_teacher:
                btnTaped.setBackgroundResource(R.drawable.botton);
                btnTaped=(Button)findViewById(R.id.btn_aut_teacher);
                btnTaped.setBackgroundResource(R.drawable.botton_taped);
                tekStatus="teacher";
                break;
            case R.id.btn_aut:
                if (name.length()>=1 && password.length()>=1 && tekStatus!="") {

                            //Работаем с учителем
                            jsonObjectCall = server.checkTeacherFromServer();
                            try {
                                getJsonObject();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                if(checkJson() == 0){
                                    Gson gson = new Gson();
                                    if(jsonObject.getString("position").equals("teacher")){
                                        Teacher teacher = startTeacher();
                                        intent = new Intent(this,Teacher.class);
                                        intent.putExtra("teacher", (Parcelable) teacher);
                                    }
                                    else {
                                        Teacher teacher = startTeacher();
                                        intent = new Intent(this,Teacher.class);
                                        intent.putExtra("teacher", (Parcelable) teacher);
                                    }
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            //Закончили работать с учителем
                            break;

                }
                else {
                    backToast = Toast.makeText(getBaseContext(), "Вы ошиблись", Toast.LENGTH_SHORT);
                    backToast.show();
                }
                break;
        }
    }


}
