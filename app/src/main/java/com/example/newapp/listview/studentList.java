package com.example.newapp.listview;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;
import com.example.newapp.database.Students;

import java.util.ArrayList;

public class studentList extends AppCompatActivity {
    private ListView listview;
    private MyAdapter adapter;
    private EditText editTextFilter;
    private Button searchbtn;

    ArrayList<Students> studentlist=MyAdapter.studentlist;

    public studentList() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);

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

        // listview에 꾹 눌렀을 때 이벤트 정의 ==> 삭제 기능
        listview.setOnItemLongClickListener((new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Students students=(Students)parent.getItemAtPosition(position);
                int flag= OnClickHandler(view);

                if(flag==1){ //해당 학생 삭제
                    studentlist.remove(position);
                    adapter.notifyDataSetChanged();
                }
                else if(flag==2){ //수정

                }
                return true;
            }
        }));
    }

    private void CreateView(){
        //Adapter 생성
        adapter=new MyAdapter();

        // 리스트뷰 참조 및 Apater 달기
        listview=(ListView)findViewById(R.id.showlist);
        listview.setAdapter(adapter);
    }


    private int OnClickHandler(View view){
      AlertDialog.Builder builder=new AlertDialog.Builder(studentList.this);
      AlertDialog alertDialog;
        final int[] flag = {0};

      builder.setTitle("알림")
              .setMessage("수정/삭제")
              .setPositiveButton("수정",
              new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                      flag[0] =1;
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
              flag[0]=2;
          }
      });

        alertDialog = builder.create();
        alertDialog.show();

        return flag[0];
    }

}
