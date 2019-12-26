package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Rasp extends AppCompatActivity implements View.OnClickListener {

    AlertDialog.Builder bilder;
    AlertDialog al;
    View view;

    public TextView[] textViews;
    public static String Dz;
    private int Code;
    HashMap<Integer, Integer> hashMap=new HashMap<>();
    LinearLayout.LayoutParams layoutParams;
    LinearLayout.LayoutParams layoutParamsBtn;
    LinearLayout.LayoutParams layoutParamsPred;
    LinearLayout.LayoutParams layoutParamsOc;
    String[] array;
    int i;

    //Сервер
    Call<JSONObject> jsonObjectCall;
    JSONObject jsonObject;
    Retrofit retrofit;
    Server server;

    String a;



    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rasp_pupil);

        i=0;

        layoutParams = new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT, TableLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(0,4,0,4);

        layoutParamsBtn = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        layoutParamsBtn.weight=1;

        layoutParamsBtn.setMargins(14,0,14,0);

        layoutParamsPred = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        layoutParamsPred.setMargins(10,0,0,0);

        layoutParamsOc = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT);
        layoutParamsOc.setMargins(0,0,10,0);


        textViews=new TextView[64];

        hashMap.put(1,R.id.mon);
        hashMap.put(2,R.id.tue);
        hashMap.put(3,R.id.wen);
        hashMap.put(4,R.id.thu);
        hashMap.put(5,R.id.frid);



        a = "{\"monday\":[{\"id\":216,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":20,\"homework\":\"\",\"name\":\"\u042d\u043a\u043e\u043d\u043e\u043c\u0438\u043a\u0430\",\"classroom\":\"20\",\"school_class_name\":\"11a\"},{\"id\":215,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":19,\"homework\":\"\",\"name\":\"\u041f\u0440\u0430\u0432\u043e\",\"classroom\":\"19\",\"school_class_name\":\"11a\"},{\"id\":214,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":18,\"homework\":\"\",\"name\":\"\u041e\u0411\u0416\",\"classroom\":\"18\",\"school_class_name\":\"11a\"},{\"id\":213,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":17,\"homework\":\"\",\"name\":\"\u041c\u0443\u0437\u044b\u043a\u0430\",\"classroom\":\"17\",\"school_class_name\":\"11a\"},{\"id\":212,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":16,\"homework\":\"\",\"name\":\"\u0418\u0417\u041e\",\"classroom\":\"16\",\"school_class_name\":\"11a\"},{\"id\":211,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":15,\"homework\":\"\",\"name\":\"\u041e\u043a\u0440\u0443\u0436\u0430\u044e\u0449\u0438\u0439 \u043c\u0438\u0440\",\"classroom\":\"15\",\"school_class_name\":\"11a\"}],\"tuesday\":[{\"id\":210,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":14,\"homework\":\"\",\"name\":\"\u041e\u0431\u0449\u0435\u0441\u0442\u0432\u043e\u0437\u043d\u0430\u043d\u0438\u0435\",\"classroom\":\"14\",\"school_class_name\":\"11a\"},{\"id\":209,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":13,\"homework\":\"\",\"name\":\"\u0413\u0435\u043e\u0433\u0440\u0430\u0444\u0438\u044f\",\"classroom\":\"13\",\"school_class_name\":\"11a\"},{\"id\":208,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":12,\"homework\":\"\",\"name\":\"\u0410\u043d\u0433\u043b.\u044f\u0437.\",\"classroom\":\"12\",\"school_class_name\":\"11a\"},{\"id\":207,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":10,\"homework\":\"\",\"name\":\"\u0413\u0435\u043e\u043c\u0435\u0442\u0440\u0438\u044f\",\"classroom\":\"10\",\"school_class_name\":\"11a\"},{\"id\":206,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":9,\"homework\":\"\",\"name\":\"\u0410\u043b\u0433\u0435\u0431\u0440\u0430\",\"classroom\":\"9\",\"school_class_name\":\"11a\"},{\"id\":205,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":8,\"homework\":\"\",\"name\":\"\u0424\u0438\u0437-\u0440\u0430\",\"classroom\":\"8\",\"school_class_name\":\"11a\"},{\"id\":204,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":7,\"homework\":\"\",\"name\":\"\u0418\u043d\u0444\u043e\u0440\u043c\u0430\u0442\u0438\u043a\u0430\",\"classroom\":\"7\",\"school_class_name\":\"11a\"}],\"wednesday\":[{\"id\":203,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":6,\"homework\":\"\",\"name\":\"\u0418\u0441\u0442\u043e\u0440\u0438\u044f\",\"classroom\":\"6\",\"school_class_name\":\"11a\"},{\"id\":202,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":5,\"homework\":\"\",\"name\":\"\u041b\u0438\u0442\u0435\u0440\u0430\u0442\u0443\u0440\u0430\",\"classroom\":\"5\",\"school_class_name\":\"11a\"},{\"id\":201,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":4,\"homework\":\"\",\"name\":\"\u0420\u0443\u0441\u0441\u043a\u0438\u0439 \u044f\u0437\u044b\u043a\",\"classroom\":\"4\",\"school_class_name\":\"11a\"},{\"id\":200,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":3,\"homework\":\"\",\"name\":\"\u0424\u0438\u0437\u0438\u043a\u0430\",\"classroom\":\"3\",\"school_class_name\":\"11a\"},{\"id\":199,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":2,\"homework\":\"\",\"name\":\"\u0425\u0438\u043c\u0438\u044f\",\"classroom\":\"2\",\"school_class_name\":\"11a\"},{\"id\":198,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":1,\"homework\":\"\",\"name\":\"\u0411\u0438\u043e\u043b\u043e\u0433\u0438\u044f\",\"classroom\":\"1\",\"school_class_name\":\"11a\"}],\"thursday\":[{\"id\":197,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":11,\"homework\":\"\",\"name\":\"\u041c\u0430\u0442\u0435\u043c\u0430\u0442\u0438\u043a\u0430\",\"classroom\":\"11\",\"school_class_name\":\"11a\"},{\"id\":216,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":20,\"homework\":\"\",\"name\":\"\u042d\u043a\u043e\u043d\u043e\u043c\u0438\u043a\u0430\",\"classroom\":\"20\",\"school_class_name\":\"11a\"},{\"id\":215,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":19,\"homework\":\"\",\"name\":\"\u041f\u0440\u0430\u0432\u043e\",\"classroom\":\"19\",\"school_class_name\":\"11a\"},{\"id\":214,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":18,\"homework\":\"\",\"name\":\"\u041e\u0411\u0416\",\"classroom\":\"18\",\"school_class_name\":\"11a\"},{\"id\":213,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":17,\"homework\":\"\",\"name\":\"\u041c\u0443\u0437\u044b\u043a\u0430\",\"classroom\":\"17\",\"school_class_name\":\"11a\"},{\"id\":212,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":16,\"homework\":\"\",\"name\":\"\u0418\u0417\u041e\",\"classroom\":\"16\",\"school_class_name\":\"11a\"},{\"id\":211,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":15,\"homework\":\"\",\"name\":\"\u041e\u043a\u0440\u0443\u0436\u0430\u044e\u0449\u0438\u0439 \u043c\u0438\u0440\",\"classroom\":\"15\",\"school_class_name\":\"11a\"}],\"friday\":[{\"id\":210,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":14,\"homework\":\"\",\"name\":\"\u041e\u0431\u0449\u0435\u0441\u0442\u0432\u043e\u0437\u043d\u0430\u043d\u0438\u0435\",\"classroom\":\"14\",\"school_class_name\":\"11a\"},{\"id\":209,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":13,\"homework\":\"\",\"name\":\"\u0413\u0435\u043e\u0433\u0440\u0430\u0444\u0438\u044f\",\"classroom\":\"13\",\"school_class_name\":\"11a\"},{\"id\":208,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":12,\"homework\":\"\",\"name\":\"\u0410\u043d\u0433\u043b.\u044f\u0437.\",\"classroom\":\"12\",\"school_class_name\":\"11a\"},{\"id\":207,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":10,\"homework\":\"\",\"name\":\"\u0413\u0435\u043e\u043c\u0435\u0442\u0440\u0438\u044f\",\"classroom\":\"10\",\"school_class_name\":\"11a\"},{\"id\":206,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":9,\"homework\":\"\",\"name\":\"\u0410\u043b\u0433\u0435\u0431\u0440\u0430\",\"classroom\":\"9\",\"school_class_name\":\"11a\"},{\"id\":205,\"type\":\"subject\",\"students_list\":[\"\"],\"teacher_id\":8,\"homework\":\"\",\"name\":\"\u0424\u0438\u0437-\u0440\u0430\",\"classroom\":\"8\",\"school_class_name\":\"11a\"}]}";

        bilder = new AlertDialog.Builder(this);
        view = getLayoutInflater().inflate(R.layout.activity_alert_dz, null,true);
        bilder.setView(view);
        al = bilder.create();
        al.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));



        retrofit = new Retrofit.Builder()
                .baseUrl("https://rawgit.com/startandroid/data/master/messages/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        server = retrofit.create(Server.class);
        try {
            getJsonObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            RaspForLearner((TableLayout) findViewById(R.id.tab_monday),"Понедельник","monday");
            RaspForLearner((TableLayout) findViewById(R.id.tab_tuesday),"Вторник","tuesday");
            RaspForLearner((TableLayout) findViewById(R.id.tab_wensday),"Среда","wednesday");
            RaspForLearner((TableLayout) findViewById(R.id.tab_thursday),"Четверг","thursday");
            RaspForLearner((TableLayout) findViewById(R.id.tab_friday),"Пятница","friday");
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
    //Для ученика
    public void RaspForLearner(TableLayout layoutRoot,String day,String dayEng) throws JSONException {
        int j=i+5;
        TableRow tableRowDay = new TableRow(this);
        TextView textDay= new TextView(this);
        textDay.setTextColor(Color.parseColor("#000002"));
        textDay.setText(day);
        tableRowDay.addView(textDay,layoutParamsBtn);
        layoutRoot.addView(tableRowDay,layoutParams);
        String g;
        int k=0;
        while(i<j){
            TableRow tableRow = new TableRow(this);
            textViews[i] = new TextView(this);
            g = jsonObject.getJSONArray(dayEng).getJSONObject(k).getString("name");
            if(g.length()>8) g=g.substring(0,9)+".";
            textViews[i].setText(String.valueOf(k+1)+". "+g);
            textViews[i].setTextColor(Color.parseColor("#000002"));
            final Context contextTextViewDz = new ContextThemeWrapper(this, R.style.style_dz_textview);
            TextView button=new TextView(contextTextViewDz);
            button.setOnClickListener(this);
            button.setText(jsonObject.getJSONArray(dayEng).getJSONObject(k).getString("homework"));
            button.setId(i);
            TextView textView=new TextView(this);
            textView.setTextColor(Color.parseColor("#000002"));
            textView.setText("Оценки");
            tableRow.addView(textViews[i],layoutParamsPred);
            tableRow.addView(button,layoutParamsBtn);
            tableRow.addView(textView,layoutParamsOc);
            layoutRoot.addView(tableRow,layoutParams);
            i++;
            k++;
        }
        /*
        textView=new TextView[40];

        int tekLay=1;
        int tekText=0;
        //предмет:дз;предмет:дз;
        array = new String[2];
        array[0]="математика:дз1;русский:дзРус1";
        array[1]="математика:дз2;русский:дзРус2";
        for(int i=0;i<array.length;i++){
            String[] a= array[i].split(";");
            for(int j=0;j<a.length;j++){
                String[] b = a[j].split(":");
                textView[tekText] = new TextView(this);
                textView[tekText].setText(b[0]+"\n"+b[1]);
                textView[tekText].setTextColor(Color.parseColor("#000000"));
                textView[tekText].setPadding(0,20,0,20);
                textView[tekText].setGravity(Gravity.CENTER);
                textView[tekText].setBackgroundResource(R.drawable.fortextview);
                LinearLayout layout = findViewById(hashMap.get(tekLay));
                layout.addView(textView[tekText],layoutParams);
                tekText++;
            }
            tekLay++;
        }
        */
    }

    public void getJsonObject() throws IOException, JSONException {
        jsonObject=new JSONObject(a);
        //Response<JSONObject> jsonObject = jsonObjectCall.execute();
    }

    @Override
    public void onClick(View v) {
        TextView textView = view.findViewById(R.id.text_dz_alert);
        textView.setTextColor(Color.parseColor("#000002"));
        String a =((TextView)findViewById(v.getId())).getText().toString();
        textView.setText(a);
        al.show();
    }
}
