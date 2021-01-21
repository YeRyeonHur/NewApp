package com.example.newapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class find_id extends AppCompatActivity {

    EditText academy_name, name;
    Button check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findid);

        academy_name = findViewById(R.id.academy_name);
        name = findViewById(R.id.name);
        check = findViewById(R.id.check);



        //아이디 찾기 눌렀을때
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String academ_name=academy_name.getText().toString().trim();
                String nam=name.getText().toString().trim();

                //확인하기




                //알려주기
            }
        });


    }


}