package com.example.newapp.database;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.newapp.R;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmQuery;

public class realmData extends AppCompatActivity {
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        // Realm 객체 생성
        realm=Realm.getDefaultInstance();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        realm.close();
    }

    // 학생들 데이터베이스에 저장
    private void addData(Realm realm, Students student){
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // 전달받은은 학생들 추가
                Students realmStudent=realm.copyToRealm(student);
            }
        });
    }

    // 데이터베이스에서 이름으로 학생들 결제날짜 찾기
    private String findData(Realm realm, String name){
        String date; //결제날짜
        Students student=realm.where(Students.class).equalTo("name",name).findFirst();
        date=student.getDate();
        return date;
    }

    private void updateData(Realm realm){
        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {

            }
        });
    }

    //삭제
    private void deleteData(Realm realm, String name, String age, String date){
        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {
                Students student=realm.where(Students.class).equalTo("name",name).and().equalTo("age",age)
                        .and().equalTo("date",date).findFirst();
                student.deleteFromRealm(); //삭제
            }
        });
    }
}

/* 참고자료
 https://youngest-programming.tistory.com/81
 https://realm.io/docs/java/latest/#working-with-realmobjects
 */

