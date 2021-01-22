package com.example.newapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class find_id extends AppCompatActivity {
    //아이디찾기
    EditText academy_name, name;
    Button check;
    //비밀번호 찾기
    private EditText email;
    private Button pw_But;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findid);

        academy_name = findViewById(R.id.academy_name);
        name = findViewById(R.id.name);
        check = findViewById(R.id.check);
        pw_But = findViewById(R.id.pw_check);
        firebaseAuth = FirebaseAuth.getInstance();

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

        //비밀번호 찾기 눌렀을 때
        pw_But.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                email = findViewById(R.id.find_email);
                String Email = email.getText().toString().trim();

                if (!isValidEmail(Email)) {
                    Toast.makeText(find_id.this, "가입 시 입력한 이메일을 적어주세요.", Toast.LENGTH_SHORT).show();
                }
                else{
                    firebaseAuth.sendPasswordResetEmail(Email);
                    Toast.makeText(find_id.this, "이메일을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    //이후 메인화면으로 넘어가기
                }
            }
        });

    }

    //이메일 유효 검사
    private boolean isValidEmail(String email){
        if (email.isEmpty()) {
            Log.i("유효", "비었음");
            // 이메일 공백
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            // 이메일 형식 불일치
            Log.i("유효", "형식오류");
            return false;
        } else {
            return true;
        }
    }

}