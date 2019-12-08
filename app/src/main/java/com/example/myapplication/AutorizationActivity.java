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

public class AutorizationActivity extends AppCompatActivity implements View.OnClickListener {

    Button reg;
    Button aut;
    EditText name;
    EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autorization);





        reg=findViewById(R.id.ActAutorizationRegisterBtn);
        aut=findViewById(R.id.ActAutorizationAutorizationBtn);
        reg.setOnClickListener(this);
        aut.setOnClickListener(this);
        name=findViewById(R.id.ActAutorizationNameTxt);
        password=findViewById(R.id.ActAutorizationPasswordTxt);
    }
    public void finishThis(){
        finish();
    }

    private void OpenAccount(String a){
        Intent i = new Intent();
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ActAutorizationAutorizationBtn:
                String a = Baza.checkAccount(name.getText().toString(),password.getText().toString());
                if( a!= ""){
                    OpenAccount(a);
                }
            else {
                TextView text = findViewById(R.id.ActAutorizationChekTxt);
                text.setTextColor(ContextCompat.getColor(this,R.color.Red));
                text.setText("Такого аккаунта не существует");
                }
            break;
            case R.id.ActAutorizationRegisterBtn:
                Intent i = new Intent(this,RegisterActivity.class);
                startActivity(i);
        }
    }
}
