package com.example.newapp.listview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newapp.R;
import com.example.newapp.database.Students;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

import io.realm.Realm;

public class MyAdapter extends BaseAdapter{
    // Adapter에 추가된 데이터를 저장하기 위한 ArrayList
    private ArrayList<Students> studentlist, filteredItemList;
    Context mContext = null;
    LayoutInflater mLayoutInflater = null;
    int what;

    public MyAdapter(Context context, ArrayList<Students> data, int what) {
        mContext = context;
        filteredItemList=data;
        studentlist=new ArrayList<>();
        studentlist.addAll(filteredItemList);
        mLayoutInflater = LayoutInflater.from(mContext);
        this.what=what;
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
        }

        TextView nameText;
        TextView ageText;
        TextView phoneText;
        TextView dateText;


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

}
