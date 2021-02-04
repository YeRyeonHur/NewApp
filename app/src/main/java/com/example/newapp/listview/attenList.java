package com.example.newapp.listview;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;
import com.example.newapp.calendar_page.calendar;
import com.example.newapp.database.PreferenceManager;
import com.example.newapp.database.Students;
import com.example.newapp.info.add_std;

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
    private RealmResults<Students> attendstudents;
    private TextView today_date_text;
    private String saved_date, new_date;
    private ArrayList<String> today_attend_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attendlistview);

        Calendar cal=Calendar.getInstance();

        today_date_text = findViewById(R.id.today_date);
        int month = cal.get(Calendar.MONTH) + 1;
        today_date_text.setText(" " + month + "월 " + cal.get(Calendar.DATE) + "일 출석");

        realm=Realm.getDefaultInstance();

        saved_date = PreferenceManager.getString(this, "attend_date");
        new_date = cal.get(Calendar.YEAR) + "/" + cal.get(Calendar.MONTH) + "/" + cal.get(Calendar.DATE);
        if (!saved_date.equals(new_date)) {
            PreferenceManager.setString(this, "attend_date", new_date);
            realm.beginTransaction();
            stu = realm.where(Students.class).equalTo("attendchk", false).findAll();
            for(int i = 0; i < stu.size(); i++){
                stu.get(i).setAttendchk(false);
            }
            stu = realm.where(Students.class).equalTo("attended", false).findAll();
            for(int i = 0; i < stu.size(); i++){
                stu.get(i).setAttended(false);
            }
            realm.commitTransaction();
        }
        //오늘 출석한 학생들의 id를 불러온다
        today_attend_id = PreferenceManager.getArrayList(this, saved_date + "attend");

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

    //출석 체크 버튼을 눌렀을 때
    public void clicked_attend(View v){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage("선택한 학생들의 출석을 체크합니다.");
        alert.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                attendstudents = realm.where(Students.class).equalTo("attendchk", true).findAll();
                if(attendstudents.size() == 0){
                    Toast.makeText(attenList.this, "출석할 학생들을 체크해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                realm.beginTransaction();
                for(int i = 0; i < attendstudents.size(); i++){
                    attendstudents.get(i).setAttendchk(false);
                    attendstudents.get(i).setAttended(true);
                    today_attend_id.add(attendstudents.get(i).getStd_id() + "");
                }
                realm.commitTransaction();
                adapter.notifyDataSetChanged();
                CreateView();
                Toast.makeText(attenList.this, "출석 체크가 완료되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        alert.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alert.show();
    }
}
