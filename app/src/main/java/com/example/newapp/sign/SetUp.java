package com.example.newapp.sign;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;
import com.example.newapp.database.PreferenceManager;
import com.example.newapp.info.add_std;
import com.example.newapp.listview.studentList;
import com.example.newapp.sign.login;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SetUp extends AppCompatActivity {


    private Button logout_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setup);


        // 여기부터 id 등록해주세요
        logout_btn=findViewById(R.id.logout);

        btn_clicked(); //버튼 눌렀을 때 리스너 등록하는 메소드, (다른 버튼들도 여기에다 넣어주세요)
    }


    private void btn_clicked(){
        logout_btn.setOnClickListener(new View.OnClickListener() {
            private FirebaseAuth firebaseAuth=FirebaseAuth.getInstance();
            @Override
            public void onClick(View v) {
                //유저가 로그인 하지 않는 상태 --> 이 액티비티를 종료하고 로그인 액티비티를 연다.
                if(firebaseAuth.getCurrentUser()==null){
                    finish();
                    startActivity(new Intent(getApplicationContext(), login.class));
                }
                else{
                  //  FirebaseUser user=firebaseAuth.getCurrentUser();
                    showDialog(firebaseAuth);
                }
            }
        });
    }
    private void showDialog(FirebaseAuth firebaseAuth){
        AlertDialog.Builder builder=new AlertDialog.Builder(SetUp.this);
        AlertDialog alertDialog;

        builder.setTitle("알림")
                .setMessage("로그아웃 하시겠습니까?")
                .setPositiveButton("네",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Context login_context=login.getContext();
                                // 자동로그인 방지/헤제
                                boolean check_auto = PreferenceManager.getBoolean(login_context, "auto_login");
                                if(check_auto) {
                                    PreferenceManager.setBoolean(login_context, "auto_login", false);
                                }
                               firebaseAuth.signOut();
                                finish();

                                Toast.makeText(getApplicationContext(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),login.class));
                            }
                        });

        builder.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        alertDialog = builder.create();
        alertDialog.show();

    }
}
