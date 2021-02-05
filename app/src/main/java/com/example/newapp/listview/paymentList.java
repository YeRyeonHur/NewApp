package com.example.newapp.listview;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;
import com.example.newapp.database.Students;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class paymentList extends AppCompatActivity {
    private ListView listview;
    private MyAdapter adapter;
    private EditText editTextFilter;
    private Realm realm;
    private RealmResults<Students> stu;
    private ArrayList<Students> studentlist;
    private TextView tv_payment_month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.paymentlistview);
        Intent payIntent = getIntent();
        int month = payIntent.getIntExtra("month",0);
        tv_payment_month = (TextView)findViewById(R.id.payment_month);
        tv_payment_month.setText(month+"월");

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
        adapter=new MyAdapter(this, studentlist,2);

        // 리스트뷰 참조 및 Adapter 달기
        listview=(ListView)findViewById(R.id.payment_listview);
        listview.setAdapter(adapter);
    }

    private void getStudent(){
        realm=Realm.getDefaultInstance();
        studentlist=new ArrayList<>();

        stu = realm.where(Students.class).findAll();
        if(stu.size()>0)
            studentlist.addAll(realm.copyFromRealm(stu));
    }
}
