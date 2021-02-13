package com.example.newapp.sign;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newapp.calendar_page.calendar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;

public class login extends AppCompatActivity {
    EditText id, password;
    Button login;
    TextView find_id, find_password, join;
    private FirebaseAuth firebaseAuth;
    String Id, Pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        id = findViewById(R.id.id);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login_button);
        find_id = findViewById(R.id.find_id);
        join = findViewById(R.id.join);
        firebaseAuth = FirebaseAuth.getInstance();
        //로그인 버튼 리스너
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(check()==true){
                firebaseAuth.signInWithEmailAndPassword(Id, Pass).addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent = new Intent(login.this, calendar.class);
                            startActivity(intent);

                        }else{
                            Toast.makeText(login.this,"아이디/비밀번호를 다시 확인해주세요",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            }

        });

        //아이디 찾기 눌렀을때
        find_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //아이디 찾기 화면으로 넘어감
                Intent intent = new Intent(login.this, com.example.newapp.sign.find_id.class); //this 대신 getActivity()
                startActivity(intent);
            }
        });
        //회원가입 눌렀을때 넘어가게
        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //아이디 찾기 화면으로 넘어감
                Intent intent = new Intent(login.this, com.example.newapp.sign.Register.class); //this 대신 getActivity()
                startActivity(intent);
            }
        });


    }

    private boolean check(){
        Id = id.getText().toString().trim();
        Pass = password.getText().toString().trim();
        if(Id.equals("")){
            Toast.makeText(login.this,"아이디를 바르게 입력해주세요",Toast.LENGTH_SHORT).show();
            return false;
        }
        else if(Pass.equals("")){
            Toast.makeText(login.this,"비밀번호를 바르게 입력해주세요",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

}