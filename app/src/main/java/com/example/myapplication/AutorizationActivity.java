package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;

import org.apache.commons.io.FileUtils;
import org.intellij.lang.annotations.Language;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
    String c="1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorization);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        a ="{\"id\":12,\"position\":\"Pupil\",\"child_id\":12,\"password\":\"12\",\"name\":\"12\",\"surname\":\"12\",\"email\":\"12\",\"phone\":\"12\",\"qualification\":\"12\",\"is_admin\":false,\"permit\":12,\"clas\":\"12\"}";


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


        retrofit = new Retrofit.Builder()
                .baseUrl("https://github.com/nikita-jpg/ElJornal/blob/develop/app/src/main/res/raw/")
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
    public static String doGet(String url)
            throws Exception {

        URL obj = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        connection.setRequestMethod("GET");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0" );
        connection.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        connection.setRequestProperty("Content-Type", "application/json");

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }
        bufferedReader.close();

//      print result


        return response.toString();
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

            case R.id.btn_aut:
                if (name.length()>=1 && password.length()>=1) {

                            //Работаем с учителем
                            //jsonObjectCall = server.checkTeacherFromServer();
                            try {
                                getJsonObject();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            try {
                                if(checkJson() == 0){
                                    if(name.getText().toString().equals("teacherAdmin")){
                                        Teacher teacher = startTeacher();
                                        teacher.setIs_admin(true);
                                        intent = new Intent(this,Teacher.class);
                                        intent.putExtra("teacher", (Parcelable) teacher);
                                    }
                                    else if(name.getText().toString().equals("teacher")){
                                        Teacher teacher = startTeacher();
                                        teacher.setIs_admin(false);
                                        intent = new Intent(this,Teacher.class);
                                        intent.putExtra("teacher", (Parcelable) teacher);
                                    }
                                    else {
                                        Learner learner = startLearner();
                                        intent = new Intent(this,Learner.class);
                                        intent.putExtra("pupil", (Parcelable) learner);
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
