package com.example.newapp.info;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
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

import java.sql.Time;

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

        timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener(){
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


}