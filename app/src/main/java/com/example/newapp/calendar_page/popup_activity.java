package com.example.newapp.calendar_page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newapp.R;
import com.example.newapp.database.Students;
import com.example.newapp.listview.MyAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Iterator;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class popup_activity extends Activity {
    private ArrayList<Students> studentlist, filteredItemList;
    private ArrayList<String> name;
    TextView txtText;
    Button click;
    TextView date_show;
    //정렬
    int[] sort = new int[100];

    private  Realm realm;
    int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.popup);

        //UI 객체생성
        date_show=(TextView)findViewById(R.id.show_date);//윗칸 날짜보여주는 칸
        txtText = (TextView)findViewById(R.id.txtText);
        click = (Button)findViewById(R.id.close);


        //데이터 가져오기
        Intent intent = getIntent();

        String data = intent.getStringExtra("data");
        date_show.setText(data);


        String info=intent.getStringExtra("Info");
        txtText.setText(info);

        int final_day = intent.getIntExtra("spec", 0);
        int day_of_week = intent.getIntExtra("day_of_week",0);


        String paying="결제 필요"+"\n";
        Realm realm = Realm.getDefaultInstance();
        RealmResults<Students> stu;
        stu = realm.where(Students.class).equalTo("date", final_day).findAll();
        int count = stu.size();
        if(count == 0){
            paying="";
        }
        String coming ="등원생 목록"+"\n";


        for(Students s : stu){
            String name = s.getName();
            paying+=name +"\n";
        }

        if(day_of_week==1){//일
            stu = realm.where(Students.class).notEqualTo("sun", -1).findAll();
            int num = stu.size();
            for(int i=0;i<num;i++){
                sort[i] = i;
            }
            for(int i=0;i<num;i++){
                for(int j=i+1;j<num;j++){
                    if(stu.get(i).getSun()>stu.get(j).getSun()){
                        int tmp = sort[i];
                        sort[i] = sort[j];
                        sort[j] = tmp;
                    }
                }
            }
            for(int i=0;i<num;i++){
                int hour = stu.get(sort[i]).getSun()/100;
                int min = stu.get(sort[i]).getSun()%100;
                coming += stu.get(sort[i]).getName() + " " + hour +"시"+min +"분\n";
            }
            /*for(Students s : stu){
                String name = s.getName();
                int hour = s.getSun()/100;
                int min = s.getSun()%100;
                coming += name + " " + hour +"시"+min +"분\n";
            }*/

        }
        else if(day_of_week==2){//월

            stu = realm.where(Students.class).notEqualTo("mon", -1).findAll();
            int num = stu.size();
            for(int i=0;i<num;i++){
                sort[i] = i;
            }
            for(int i=0;i<num;i++){
                for(int j=i+1;j<num;j++){
                    if(stu.get(i).getMon()>stu.get(j).getMon()){
                        int tmp = sort[i];
                        sort[i] = sort[j];
                        sort[j] = tmp;
                    }
                }
            }
            for(int i=0;i<num;i++){
                int hour = stu.get(sort[i]).getMon()/100;
                int min = stu.get(sort[i]).getMon()%100;
                coming += stu.get(sort[i]).getName() + " " + hour +"시"+min +"분\n";
            }
        }
        else if(day_of_week==3){//화
            stu = realm.where(Students.class).notEqualTo("tue", -1).findAll();
            int num = stu.size();
            for(int i=0;i<num;i++){
                sort[i] = i;
            }
            for(int i=0;i<num;i++){
                for(int j=i+1;j<num;j++){
                    if(stu.get(i).getTue()>stu.get(j).getTue()){
                        int tmp = sort[i];
                        sort[i] = sort[j];
                        sort[j] = tmp;
                    }
                }
            }
            for(int i=0;i<num;i++){
                int hour = stu.get(sort[i]).getTue()/100;
                int min = stu.get(sort[i]).getTue()%100;
                coming += stu.get(sort[i]).getName() + " " + hour +"시"+min +"분\n";
            }
        }
        else if(day_of_week==4){//수
            stu = realm.where(Students.class).notEqualTo("wed", -1).findAll();
            int num = stu.size();
            for(int i=0;i<num;i++){
                sort[i] = i;
            }
            for(int i=0;i<num;i++){
                for(int j=i+1;j<num;j++){
                    if(stu.get(i).getWed()>stu.get(j).getWed()){
                        int tmp = sort[i];
                        sort[i] = sort[j];
                        sort[j] = tmp;
                    }
                }
            }
            for(int i=0;i<num;i++){
                int hour = stu.get(sort[i]).getWed()/100;
                int min = stu.get(sort[i]).getWed()%100;
                coming += stu.get(sort[i]).getName() + " " + hour +"시"+min +"분\n";
            }
        }
        else if(day_of_week==5){//목
            stu = realm.where(Students.class).notEqualTo("thu", -1).findAll();
            int num = stu.size();
            for(int i=0;i<num;i++){
                sort[i] = i;
            }
            for(int i=0;i<num;i++){
                for(int j=i+1;j<num;j++){
                    if(stu.get(i).getThu()>stu.get(j).getThu()){
                        int tmp = sort[i];
                        sort[i] = sort[j];
                        sort[j] = tmp;
                    }
                }
            }
            for(int i=0;i<num;i++){
                int hour = stu.get(sort[i]).getThu()/100;
                int min = stu.get(sort[i]).getThu()%100;
                coming += stu.get(sort[i]).getName() + " " + hour +"시"+min +"분\n";
            }
        }
        else if(day_of_week==6){//금
            stu = realm.where(Students.class).notEqualTo("fri", -1).findAll();
            int num = stu.size();
            for(int i=0;i<num;i++){
                sort[i] = i;
            }
            for(int i=0;i<num;i++){
                for(int j=i+1;j<num;j++){
                    if(stu.get(i).getFri()>stu.get(j).getFri()){
                        int tmp = sort[i];
                        sort[i] = sort[j];
                        sort[j] = tmp;
                    }
                }
            }
            for(int i=0;i<num;i++){
                int hour = stu.get(sort[i]).getFri()/100;
                int min = stu.get(sort[i]).getFri()%100;
                coming += stu.get(sort[i]).getName() + " " + hour +"시"+min +"분\n";
            }
        }
        else {//토
            stu = realm.where(Students.class).notEqualTo("sat", -1).findAll();
            int num = stu.size();
            for(int i=0;i<num;i++){
                sort[i] = i;
            }
            for(int i=0;i<num;i++){
                for(int j=i+1;j<num;j++){
                    if(stu.get(i).getSat()>stu.get(j).getSat()){
                        int tmp = sort[i];
                        sort[i] = sort[j];
                        sort[j] = tmp;
                    }
                }
            }
            for(int i=0;i<num;i++){
                int hour = stu.get(sort[i]).getSat()/100;
                int min = stu.get(sort[i]).getSat()%100;
                coming += stu.get(sort[i]).getName() + " " + hour +"시"+min +"분\n";
            }
        }

        count = stu.size();
        if(count == 0){
            coming="";
        }

        txtText.setText(paying+coming);


        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                setResult(RESULT_OK, intent);

                //액티비티(팝업) 닫기
                finish();


            }
        });

    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }



    private void readData(){
        studentlist.clear();
        RealmResults<Students>results=realm.where(Students.class).findAll();
        studentlist.addAll(realm.copyFromRealm(results));
    }

}
