package com.example.newapp.explain;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.example.newapp.R;

public class explain_page3 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return (ViewGroup) inflater.inflate(R.layout.exp_frag3, container, false);
    }
}