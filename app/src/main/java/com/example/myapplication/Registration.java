package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Registration extends Activity implements View.OnClickListener {

    LinearLayout layout;
    LinearLayout.LayoutParams layoutParams;
    EditText editText1;
    EditText editText2;
    EditText editText3;

    EditText edit_registr_login;
    EditText edit_registr_password;
    EditText edit_registr_name;
    EditText edit_registr_surname;

    Toast backToast;

    String position;
    Button btnReg;

    Retrofit retrofit;
    Server server;
    JSONObject jsonObject;
    Call<JSONObject> gsonReg;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        layout=findViewById(R.id.lay_registr);
        layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        edit_registr_login=findViewById(R.id.edit_registr_login);
        edit_registr_password=findViewById(R.id.edit_registr_password);
        edit_registr_name=findViewById(R.id.edit_registr_name);
        edit_registr_surname=findViewById(R.id.edit_registr_surname);


        btnReg=findViewById(R.id.btn_reg);
        btnReg.setOnClickListener(this);



       retrofit = new Retrofit.Builder()
               .baseUrl("https://docs.google.com/document/")
               .addConverterFactory(GsonConverterFactory.create())
               .build();
       server = retrofit.create(Server.class);

        Intent intent=getIntent();
        position = intent.getStringExtra("status");

        switch (position){
            case "Teacher":
                editText1=new EditText(this);
                editText2=new EditText(this);
                editText3=new EditText(this);
                editText1.setHint("e-mail");
                editText2.setHint("телефон");
                editText3.setHint("предмет");
                layout.addView(editText1,layoutParams);
                layout.addView(editText2,layoutParams);
                layout.addView(editText3,layoutParams);

                editText1.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                editText2.setInputType(InputType.TYPE_CLASS_PHONE);

                break;
            case "Pupil":
                editText1=new EditText(this);
                editText1.setHint("класс");
                layout.addView(editText1,layoutParams);
                break;
            case "Parent":
                editText1=new EditText(this);
                editText1.setHint("id ребёнка");
                layout.addView(editText1,layoutParams);
                break;
        }
    }
    public void getJsonObject(){
        /*gsonReg.enqueue(new Callback<JSONObject>() {
            @Override
            public void onResponse(Call<JSONObject> call, Response<JSONObject> response) {
                jsonObject = response.body();
            }

            @Override
            public void onFailure(Call<JSONObject> call, Throwable t) {

            }
        });
         */
    }

    @Override
    public void onClick(View v) {

        if(edit_registr_login.length()<=7){
            backToast = Toast.makeText(getBaseContext(), "Логин не может быть короче 8-ми символов", Toast.LENGTH_SHORT);
            backToast.show();
        }
        else if(edit_registr_password.length()<=7){
            backToast = Toast.makeText(getBaseContext(), "Пароль не может быть короче 8-ми символов", Toast.LENGTH_SHORT);
            backToast.show();
        }
        else if(edit_registr_name.length()<=1){
            backToast = Toast.makeText(getBaseContext(), "Имя не может быть короче 2-х букв", Toast.LENGTH_SHORT);
            backToast.show();
        }
        else if( edit_registr_surname.length()<=1){
            backToast = Toast.makeText(getBaseContext(), "Фамилия не может быть короче 2-х букв", Toast.LENGTH_SHORT);
            backToast.show();
        }
        else {
            switch (position){
                case "Teacher":
                    if(editText1.length()<=4){
                        backToast = Toast.makeText(getBaseContext(), "E-mail не может быть короче 4-х символов", Toast.LENGTH_SHORT);
                        backToast.show();
                    }
                    else if(editText2.length()!=11){
                        backToast = Toast.makeText(getBaseContext(), "Длинна номера телефона должна быть равна 11-ти цифрам", Toast.LENGTH_SHORT);
                        backToast.show();
                    }
                    else if(editText3.length()<=6){
                        backToast = Toast.makeText(getBaseContext(), "Название учебной дисциплины не может быть короче 6-ти букв", Toast.LENGTH_SHORT);
                        backToast.show();
                    }
                    else {
                        gsonReg = server.registerTeacherToServer(edit_registr_login.getText().toString(),edit_registr_password.getText().toString(),edit_registr_name.getText().toString(),edit_registr_surname.getText().toString(),position,editText1.getText().toString(),editText2.getText().toString(),editText3.getText().toString());
                    }
                    break;
                case "Pupil":
                    if(editText1.length()<=1){
                        backToast = Toast.makeText(getBaseContext(), "Название класса не может быть короче 2-х символов", Toast.LENGTH_SHORT);
                        backToast.show();
                    }
                    else {
                        gsonReg = server.registerPupilToServer(edit_registr_login.getText().toString(),edit_registr_password.getText().toString(),edit_registr_name.getText().toString(),edit_registr_surname.getText().toString(),position,editText1.getText().toString());
                    }
                    break;
                case "Parent":
                    if(editText1.length()!=15){
                        backToast = Toast.makeText(getBaseContext(), "id человека - 15 цифр", Toast.LENGTH_SHORT);
                        backToast.show();
                    }
                    else {
                        gsonReg = server.registerParentToServer(edit_registr_login.getText().toString(),edit_registr_password.getText().toString(),edit_registr_name.getText().toString(),edit_registr_surname.getText().toString(),position,Integer.parseInt(editText1.getText().toString()));
                    }
                    break;
            }
            getJsonObject();
            try {
                if (jsonObject.getInt("resultCode") == 1) {
                    backToast = Toast.makeText(getBaseContext(), "Вы ошиблись", Toast.LENGTH_SHORT);
                    backToast.show();
                }
                else {
                    backToast = Toast.makeText(getBaseContext(), "Успешная регистрация", Toast.LENGTH_SHORT);
                    backToast.show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
