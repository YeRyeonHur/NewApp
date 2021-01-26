package com.example.newapp.database;

import io.realm.RealmObject;

public class Students extends RealmObject {
    private String name, phone, date;
    private int mon, tue, wed, thu, fri, sat, sun;
    private int age; //결제 날짜

    public Students(String name, int age, String phone, String date){
        this.name=name;
        this.age=age;
        this.phone=phone;
        this.date=date;
    }

    public Students(){
    }

    public String getName(){
        return name;
    }
    public String getPhone(){
        return phone;
    }
    public int getAge(){
        return age;
    }
    public String getDate(){
        return date;
    }
}
