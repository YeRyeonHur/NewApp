package com.example.newapp.info;

import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.newapp.R;

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

    public void clicked_days(View v){

    }

    public void clicked_pay(View v){

    }
}