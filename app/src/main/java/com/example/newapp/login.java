package com.example.newapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    EditText id, password;
    Button login;
    TextView find_id, find_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_button);
        find_id = findViewById(R.id.find_id);
        find_password=findViewById(R.id.find_password);

        //로그인 버튼 리스너
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //아이디 찾기 눌렀을때
        find_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //아이디 찾기 화면으로 넘어감
                Intent intent = new Intent(login.this, find_id.class); //this 대신 getActivity()
                startActivity(intent);
            }
        });

        find_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }


}