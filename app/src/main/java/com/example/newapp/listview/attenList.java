package com.example.newapp.listview;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;
import com.example.newapp.database.Students;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class attenList extends AppCompatActivity {
    private ListView listview;
    private MyAdapter adapter;
    private EditText editTextFilter;
    private Realm realm;
    private RealmResults<Students> stu;
    private ArrayList<Students> studentlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendlistview);


        getStudent();
        CreateView();
        // filtering
        editTextFilter=(EditText)findViewById(R.id.edittxt);
        editTextFilter.addTextChangedListener(new TextWatcher(){

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String text=editTextFilter.getText().toString().toLowerCase(Locale.getDefault());
                Log.i("Text",text);
                adapter.filter(text);
            }
        });
    }

    private void CreateView(){
        //Adapter 생성
        adapter=new MyAdapter(this, studentlist,1);

        // 리스트뷰 참조 및 Apater 달기
        listview=(ListView)findViewById(R.id.listview2);
        listview.setAdapter(adapter);
    }

    private void getStudent(){
        Calendar cal=Calendar.getInstance();
        int day_of_week=cal.get(Calendar.DAY_OF_WEEK); //오늘 요일 학생들 쿼리
        realm=Realm.getDefaultInstance();
        studentlist=new ArrayList<>();

        if(day_of_week==1){//일
            stu = realm.where(Students.class).notEqualTo("sun", -1).findAll();

        }
        else if(day_of_week==2){//월
            stu = realm.where(Students.class).notEqualTo("mon", -1).findAll();

        }
        else if(day_of_week==3){//화
            stu = realm.where(Students.class).notEqualTo("tue", -1).findAll();

        }
        else if(day_of_week==4){//수
            stu = realm.where(Students.class).notEqualTo("wed", -1).findAll();

        }
        else if(day_of_week==5){//목
            stu = realm.where(Students.class).notEqualTo("thu", -1).findAll();

        }
        else if(day_of_week==6){//금
            stu = realm.where(Students.class).notEqualTo("fri", -1).findAll();
        }
        else {//토
            stu = realm.where(Students.class).notEqualTo("sat", -1).findAll();
        }

        if(stu.size()>0)
            studentlist.addAll(realm.copyFromRealm(stu));
    }
}
