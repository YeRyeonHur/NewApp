package com.example.newapp.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.database.Students;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    static public ArrayList<Students> studentlist=new ArrayList<Students>();

    public MyAdapter(){

    }

    //Adapter에 사용되는 데이터의 개수
    @Override
    public int getCount() {
        return studentlist.size();
    }

    // 지정한 position에 있는 데이터 리턴
    @Override
    public Object getItem(int position) {
        return studentlist.get(position);
    }

    // 지정한 position에 있는 데이터와 관계된 아이템의 ID 리턴
    @Override
    public long getItemId(int position) {
        return position;
    }

    //아이템 데이터 추가를 위한 함수
    public void addItem(String name, int age, String phone, String date){
        Students students=new Students(name, age, phone, date);
        studentlist.add(students);
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View 리턴
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos=position;
        final Context context=parent.getContext();

        if(convertView==null){
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.showlist,parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate 된)으로부터 위젯에 대한 참조 획득
        TextView nameText=(TextView)convertView.findViewById(R.id.Name);
        TextView ageText=(TextView)convertView.findViewById(R.id.Age);
        TextView phoneText=(TextView)convertView.findViewById(R.id.Phone);
        TextView dateText=(TextView)convertView.findViewById(R.id.date);

        // position에 위치한 데이터 참조 획득
        Students students=studentlist.get(position);

        nameText.setText("이름: "+ students.getName());
        ageText.setText("나이: "+students.getAge());
        phoneText.setText("핸드폰: "+students.getPhone());
        dateText.setText("결제 날짜: "+students.getDate());

        return convertView;
    }
}
