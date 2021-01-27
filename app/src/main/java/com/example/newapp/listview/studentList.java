package com.example.newapp.listview;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;
import com.example.newapp.calendar_page.popup_activity;
import com.example.newapp.database.Students;
import com.example.newapp.info.add_std;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class studentList extends AppCompatActivity {
    private ListView listview;
    private MyAdapter adapter;
    private EditText editTextFilter;
    private Button searchbtn;
    private  Realm realm;

    private ArrayList<Students> studentlist;

    public studentList() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

        realm=Realm.getDefaultInstance();
        studentlist=new ArrayList<>();

        readData();
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
                String filterText=s.toString();
                if(filterText.length()>0){
                    listview.setFilterText(filterText);
                }
                else{
                    listview.clearTextFilter();
                }
            }
        });

        //listview 클릭시 --> 팝업
        listview.setOnItemClickListener((new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Students students=(Students)parent.getItemAtPosition(position);
                TextView txtResult=(TextView)findViewById(R.id.txtText);
                Intent intent=new Intent(getApplicationContext(), popup_activity.class);
                String name=students.getName();
                int age=students.getAge();
                int date=students.getDate();
                String parentname=students.getPar_name();
                String parentphone=students.getPar_phone();
                String memo=students.getMemo();
                int mon=students.getMon();
                int tue=students.getTue();
                int wed=students.getWed();
                int thu=students.getThu();
                int fri=students.getFri();
                int sat=students.getSat();
                int sun=students.getSun();

                String day="";
                if(mon!=-1){
                    day=day+"월요일: "+mon;
                }
                if(tue!=-1) day=day+"화요일: "+tue;
                if(wed!=-1) day=day+"수요일: "+wed;
                if(thu!=-1) day=day+"목요일: "+thu;
                if(fri!=-1) day=day+"금요일: "+fri;
                if(sat!=-1) day=day+"토요일: "+sat;
                if(sun!=-1) day=day+"일요일: "+sun;




                intent.putExtra("data", "Test Popup");
                startActivity(intent);
            }
        }));

        // listview에 꾹 눌렀을 때 이벤트 정의 ==> 삭제 기능
        listview.setOnItemLongClickListener((new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Students students=(Students)parent.getItemAtPosition(position);
                AlertDialog.Builder builder=new AlertDialog.Builder(studentList.this);
                AlertDialog alertDialog;

                builder.setTitle("알림")
                        .setMessage("삭제 혹은 수정 하시겠습니까?")
                        .setPositiveButton("수정",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent intent=new Intent(getApplicationContext(), add_std.class);
                                        intent.putExtra("Student", (Parcelable) students);
                                        startActivity(intent);
                                        finish();

                                        modify(students);
                                        Toast.makeText(getApplicationContext(), "수정되었습니다.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setNegativeButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        deleteData(students);
                        studentlist.remove(position);
                        Toast.makeText(getApplicationContext(),"삭제되었습니다.",Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                    }
                });

                alertDialog = builder.create();
                alertDialog.show();

                return true;
            }
        }));
    }

    private void CreateView(){
        //Adapter 생성
        adapter=new MyAdapter(this, studentlist);

        // 리스트뷰 참조 및 Apater 달기
        listview=(ListView)findViewById(R.id.listview);
        listview.setAdapter(adapter);
    }


    //학생들 데이터 읽기
    private void readData(){
        RealmResults<Students>results=realm.where(Students.class).findAll();
        studentlist.addAll(realm.copyFromRealm(results));
    }

    private void modify(Students student){

        String name=student.getName();
        int age=student.getAge();
        String phone=student.getPhone();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {

                // 쿼리를 해서 하나를 가져온다.
                Students std=realm.where(Students.class).equalTo("name",name).and().equalTo("age",age)
                        .and().equalTo("phone",phone).findFirst();



            }
        });

    }

    private void deleteData(Students student){

        String name=student.getName();
        int age=student.getAge();
        String phone=student.getPhone();

        realm.executeTransaction(new Realm.Transaction(){
            @Override
            public void execute(Realm realm) {
                Students student=realm.where(Students.class).equalTo("name",name).and().equalTo("age",age)
                        .and().equalTo("phone",phone).findFirst();
                student.deleteFromRealm(); //삭제
            }
        });
    }

}
