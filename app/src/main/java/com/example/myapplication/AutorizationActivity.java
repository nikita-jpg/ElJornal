package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AutorizationActivity extends AppCompatActivity implements View.OnClickListener {

    Button reg;
    Button aut;
    EditText name;
    EditText password;

    String otvFromServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorization);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("EvgeneyServer")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Server server = retrofit.create(Server.class);

        Call<String> person = server.checkPersonFromServer(name.getText().toString(),password.getText().toString());

        person.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                otvFromServer = response.body();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });

        aut=findViewById(R.id.ActAutorizationAutorizationBtn);
        reg.setOnClickListener(this);
        aut.setOnClickListener(this);
        name=findViewById(R.id.ActAutorizationNameTxt);
        password=findViewById(R.id.ActAutorizationPasswordTxt);
    }

    @Override
    public void onClick(View v) {

    }
}
