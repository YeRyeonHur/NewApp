package com.example.newapp.info;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;
import com.example.newapp.database.Students;
import com.example.newapp.listview.MyAdapter;

import java.sql.Time;
import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class add_std extends AppCompatActivity {

    private EditText std_phone, par_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student);

        //학생, 학부모 핸드폰번호 -> 하이픈 자동 추가
        std_phone = findViewById(R.id.add_std_phone);
        par_phone = findViewById(R.id.add_par_phone);
        std_phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
        par_phone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());

    }

    //등원 시간 설정 메소드
    public void clicked_days(View v){
        TimePickerDialog timePicker;
        timePicker = new TimePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog, new TimePickerDialog.OnTimeSetListener(){
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String setTime = hourOfDay + ":" + minute;
                TextView tv = (TextView) v;
                tv.setText(setTime);
            }
        }, 00, 00, false);
        timePicker.setButton(TimePickerDialog.BUTTON_NEGATIVE, "삭제", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView tv = (TextView) v;
                tv.setText("--:--");
            }
        });
        timePicker.show();
    }

    public void save_info(View v){
        //이름, 나이, 전화번호
        String name, phone;
        int age = 0; String tmp_age;
        //등원 시각 : 0000 (앞자리2 : 월 / 뒷자리2 : 달)
        int mon = -1, tue = -1, wed = -1, thu = -1, fri = -1, sat = -1, sun = -1; String tmp_day;
        //학부모 이름, 연락처, 결제일
        String par_name, par_phone;
        int pay_date; String tmp_date;
        //메모
        String memo;
        TextView tv;

        //이름, 나이, 전화번호
        tv = findViewById(R.id.add_std_name);
        name = tv.getText().toString();
        tv = findViewById(R.id.add_std_phone);
        phone = tv.getText().toString();
        tv = findViewById(R.id.add_std_age);
        tmp_age = tv.getText().toString();
        if(tmp_age.length() > 0) age = Integer.parseInt(tmp_age);

        if(name.length() == 0 || name == null || tmp_age.length() == 0){
            Toast.makeText(this, "학생 이름과 나이는 필수 입력 사항입니다.", Toast.LENGTH_SHORT).show();
            return;
        }

        //등원 시각
        tv = findViewById(R.id.days_0);//월
        tmp_day = tv.getText().toString();
        if(!tmp_day.equals("--:--")){
            String[] time = tmp_day.split(":");
            mon = Integer.parseInt(time[0]) * 100 + Integer.parseInt(time[1]);
        }
        tv = findViewById(R.id.days_1);//화
        tmp_day = tv.getText().toString();
        if(!tmp_day.equals("--:--")){
            String[] time = tmp_day.split(":");
            tue = Integer.parseInt(time[0]) * 100 + Integer.parseInt(time[1]);
        }
        tv = findViewById(R.id.days_2);//수
        tmp_day = tv.getText().toString();
        if(!tmp_day.equals("--:--")){
            String[] time = tmp_day.split(":");
            wed = Integer.parseInt(time[0]) * 100 + Integer.parseInt(time[1]);
        }
        tv = findViewById(R.id.days_3);//목
        tmp_day = tv.getText().toString();
        if(!tmp_day.equals("--:--")){
            String[] time = tmp_day.split(":");
            thu = Integer.parseInt(time[0]) * 100 + Integer.parseInt(time[1]);
        }
        tv = findViewById(R.id.days_4);//금
        tmp_day = tv.getText().toString();
        if(!tmp_day.equals("--:--")){
            String[] time = tmp_day.split(":");
            fri = Integer.parseInt(time[0]) * 100 + Integer.parseInt(time[1]);
        }
        tv = findViewById(R.id.days_5);//토
        tmp_day = tv.getText().toString();
        if(!tmp_day.equals("--:--")){
            String[] time = tmp_day.split(":");
            sat = Integer.parseInt(time[0]) * 100 + Integer.parseInt(time[1]);
        }
        tv = findViewById(R.id.days_6);//일
        tmp_day = tv.getText().toString();
        if(!tmp_day.equals("--:--")){
            String[] time = tmp_day.split(":");
            sun = Integer.parseInt(time[0]) * 100 + Integer.parseInt(time[1]);
        }

        //학부모 이름, 연락처, 결제일
        tv = findViewById(R.id.add_par_name);
        par_name = tv.getText().toString();
        tv = findViewById(R.id.add_par_phone);
        par_phone = tv.getText().toString();
        tv = findViewById(R.id.pay_date);
        tmp_date = tv.getText().toString();
        if(tmp_date.length()>0){
            pay_date = Integer.parseInt(tmp_date);
            if(pay_date > 31 || pay_date < 1){
                Toast.makeText(this, "결제일은 1일부터 31일까지입니다.", Toast.LENGTH_SHORT).show();
                return;
            }
        } else pay_date = 0;
        //메모
        tv = findViewById(R.id.add_memo);
        memo = tv.getText().toString();

        //저장
        Students students = new Students();
        Realm realm;

        //Realm.init(this);
        realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        students = realm.createObject(Students.class);
        students.setStudent(name, phone, age);
        students.setTime(mon, tue, wed, thu, fri, sat, sun);
        students.setDate(pay_date);
        students.setParent(par_name, par_phone);
        students.setMemo(memo);

        realm.commitTransaction();
        //RealmResults<Students> realmResults = realm.where(Students.class).findAll();

        Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show();
    }
}