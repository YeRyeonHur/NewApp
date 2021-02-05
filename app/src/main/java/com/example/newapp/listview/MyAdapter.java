package com.example.newapp.listview;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newapp.R;
import com.example.newapp.calendar_page.calendar;
import com.example.newapp.database.Students;
import com.example.newapp.info.add_std;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmResults;

public class MyAdapter extends BaseAdapter{
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<Students> studentlist, filteredItemList;
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    int what;
    Calendar calendar;
    private int day_of_week;
    private Realm realm;
    private Students stu;
    Context context;

    public MyAdapter(Context context, ArrayList<Students> data, int what) {
        mContext = context;
        filteredItemList=data;
        studentlist=new ArrayList<>();
        studentlist.addAll(filteredItemList);
        mLayoutInflater = LayoutInflater.from(mContext);
        this.what=what;
        calendar = Calendar.getInstance();
        day_of_week = calendar.get(Calendar.DAY_OF_WEEK);
        realm = Realm.getDefaultInstance();
        this.context = context;
    }

    //Adapter에 사용되는 데이터의 개수
    @Override
    public int getCount() {
        return filteredItemList.size();
    }

    // 지정한 position에 있는 데이터 리턴
    @Override
    public Object getItem(int position) {
        return filteredItemList.get(position);
    }

    // 지정한 position에 있는 데이터와 관계된 아이템의 ID 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }


    // position에 위치한 데이터를 화면에 출력하는데 사용될 View 리턴
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      //  final int pos=position;
       // final Context context=parent.getContext();

        if(convertView==null){
           // LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if(what==0) {
                convertView = mLayoutInflater.inflate(R.layout.showlist, parent, false);
            }
            else if(what==1){
                convertView=mLayoutInflater.inflate(R.layout.attendlist,parent,false);
            }
            else if(what==2){
                convertView=mLayoutInflater.inflate(R.layout.paymentlist,parent,false);
            }
        }

        TextView nameText;
        TextView ageText;
        TextView phoneText;
        TextView dateText;
        TextView timeText;
        TextView idText;
        ImageButton sendBtn;

        // position에 위치한 데이터 참조 획득
        Students students=filteredItemList.get(position);

        if(what==0) {
            // 화면에 표시될 View(Layout이 inflate 된)으로부터 위젯에 대한 참조 획득
            nameText = (TextView) convertView.findViewById(R.id.Name);
            ageText = (TextView) convertView.findViewById(R.id.Age);
            phoneText = (TextView) convertView.findViewById(R.id.Phone);
            dateText = (TextView) convertView.findViewById(R.id.date);
            nameText.setText("이름: "+ students.getName());
            ageText.setText("나이: " + students.getAge());
            phoneText.setText("핸드폰: " + students.getPhone());
            dateText.setText("결제 날짜: " + students.getDate());
        }
        else if(what==1){
            nameText=(TextView)convertView.findViewById(R.id.Name);
            nameText.setText(students.getName());
            timeText = convertView.findViewById(R.id.Time);
            idText = convertView.findViewById(R.id.attend_std_id);
            sendBtn = convertView.findViewById(R.id.attend_send_message);

            //등원 시각 표시
            idText.setText(students.getStd_id() + "");
            int dow;
            if(day_of_week==1){//일
                dow = students.getSun();
            }
            else if(day_of_week==2){//월
                dow = students.getMon();
            }
            else if(day_of_week==3){//화
                dow = students.getTue();
            }
            else if(day_of_week==4){//수
                dow = students.getWed();
            }
            else if(day_of_week==5){//목
                dow = students.getThu();
            }
            else if(day_of_week==6){//금
                dow = students.getFri();
            }
            else {//토
                dow = students.getSat();
            }

            timeText.setText(dow/100 + " : " + dow % 100);

            //출석 체크 관리
            CheckBox chk1 = convertView.findViewById(R.id.checkBox1);
            TextView attended = convertView.findViewById(R.id.attended);

            if(students.getAttended()){//출석이 체크 되었다면
                chk1.setVisibility(View.GONE);
                attended.setVisibility(View.VISIBLE);
                sendBtn.setImageResource(R.drawable.send_off);
            }
            else if(students.getAttendchk()){//출석 체크 박스에 체크가 되어 있었다면
                chk1.setChecked(true);
            }
            else{//아직 등원하지 않았다면
                chk1.setChecked(false);
            }

            chk1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    realm.beginTransaction();
                    stu = realm.where(Students.class).equalTo("std_id", Integer.parseInt(idText.getText().toString())).findFirst();
                    if(chk1.isChecked()){
                        stu.setAttendchk(true);
                    }
                    else{
                        stu.setAttendchk(false);
                    }
                    realm.commitTransaction();
                }
            });

            //메시지 전송 버튼을 눌렀을 때
            sendBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    if(students.getAttended() == true) return;
                    if(students.getPhone().length() == 0 && students.getPar_phone().length() == 0){
                        Toast.makeText((attenList) context, "전화번호 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    AlertDialog.Builder clsBuilder = new AlertDialog.Builder((attenList) context );
                    clsBuilder.setTitle( "메시지를 전송합니다." );
                    EditText message = new EditText((attenList)context);
                    if(students.getPar_phone().length()>0){
                        clsBuilder.setMessage("학부모 P. " + students.getPar_phone());
                    }
                    else{
                        clsBuilder.setMessage("학생 P. " + students.getPhone());
                    }
                    message.setText(students.getName() + " 학생이 아직 등원하지 않았습니다. 확인 부탁 드립니다.");
                    clsBuilder.setView( message);
                    clsBuilder.setPositiveButton("전송", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText((attenList) context, message.getText().toString(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    clsBuilder.setNegativeButton("취소", null);
                    clsBuilder.show();
                }
            });
        }
        else if(what==2){
            nameText=(TextView)convertView.findViewById(R.id.payment_name);
            nameText.setText(students.getName());
        }


        return convertView;
    }

    public void filter(String charText){
        charText=charText.toLowerCase(Locale.getDefault());
        filteredItemList.clear();
        if(charText.length()==0){
            filteredItemList.addAll(studentlist);
        }
        else{
            for(Students student:studentlist){
                String name= student.getName();
                Log.i("Name",name);
                Log.i("Text",charText);
                if(name.toLowerCase().contains(charText)){
                    filteredItemList.add(student);
                }
            }
        }
        notifyDataSetChanged();
    }

    void send_message(Students students){

    }
}
