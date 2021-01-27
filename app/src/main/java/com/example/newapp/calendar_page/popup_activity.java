package com.example.newapp.calendar_page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.example.newapp.R;

public class popup_activity extends Activity {

    TextView txtText;
    Button click;
    TextView date_show;
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

        // 이것좀 쓸게요 ㅎㅎ -은화가-
        String info=intent.getStringExtra("Info");
        txtText.setText(info);

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



}
