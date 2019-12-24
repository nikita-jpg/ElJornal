package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;

import org.intellij.lang.annotations.Language;
import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AutorizationActivity extends AppCompatActivity implements View.OnClickListener {

    Button aut;
    EditText name;
    EditText password;
    Button btnTaped;
    Button btnTeacher,btnParent,btnLearner;
    String tekStatus;

    Retrofit retrofit;
    Server server;
    Call<JSONObject> autGet;
    JSONObject jsonObject;
    Toast backToast;
    @Language("JSON")
    String a;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorization);

        //Тест

        a ="{\"id\":12,\"position\":\"Teacher\",\"login\":\"12\",\"password\":\"12\",\"name\":\"12\",\"surname\":\"12\",\"email\":\"12\",\"phone\":\"12\",\"qualification\":\"12\",\"is_admin\":true,\"permit\":12}";
        //Тест



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
        btnParent=findViewById(R.id.btn_aut_parent);
        btnTeacher=findViewById(R.id.btn_aut_teacher);
        btnLearner.setOnClickListener(this);
        btnParent.setOnClickListener(this);
        btnTeacher.setOnClickListener(this);


        retrofit = new Retrofit.Builder()
                .baseUrl("https://trinixy.ru/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        server = retrofit.create(Server.class);
    }

    public void getJsonObject(){
        autGet.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                jsonObject = response.body();
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {

            }
        });
    }

    public void startTeacher(){
        Gson gson = new Gson();
        Teacher teacher = gson.fromJson(a,Teacher.class);
        intent = new Intent(this,Teacher.class);
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
            case R.id.btn_aut_parent:
                btnTaped.setBackgroundResource(R.drawable.botton);
                btnTaped=(Button)findViewById(R.id.btn_aut_parent);
                btnTaped.setBackgroundResource(R.drawable.botton_taped);
                tekStatus="parent";
                break;
            case R.id.btn_aut:
                if (name.length()>=1 && password.length()>=1 && tekStatus!="") {
                    switch (tekStatus) {
                        case "teacher":
                            /*autGet = server.checkTeacherFromServer(name.getText().toString(),password.getText().toString());
                            getJsonObject();*/
                            /*if (jsonObject.getInt("resultCode") == 1) {
                                backToast = Toast.makeText(getBaseContext(), "Вы ошиблись", Toast.LENGTH_SHORT);
                                backToast.show();
                            }
                            else {*/
                            startTeacher();
                            startActivity(intent);
                            //Запускаем учителя
                            break;
                        case "learner":
                            autGet = server.checkLearnerFromServer(name.getText().toString(),password.getText().toString());
                            getJsonObject();
                            try {
                                if (jsonObject.getInt("resultCode") == 1) {
                                    backToast = Toast.makeText(getBaseContext(), "Вы ошиблись", Toast.LENGTH_SHORT);
                                    backToast.show();
                                }
                                else {
                                    //Запускаем ученика
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
                else {
                    backToast = Toast.makeText(getBaseContext(), "Вы ошиблись", Toast.LENGTH_SHORT);
                    backToast.show();
                }
                break;
        }
    }


}
