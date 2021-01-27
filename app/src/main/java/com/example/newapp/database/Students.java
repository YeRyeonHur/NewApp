package com.example.newapp.database;

import io.realm.RealmObject;

public class Students extends RealmObject {
    private String name, phone;
    int date;
    private int mon, tue, wed, thu, fri, sat, sun;
    private int age; //결제 날짜
    private String par_name, par_phone;
    private String memo;

    public Students(String name, int age, String phone, int date){
        this.name=name;
        this.age=age;
        this.phone=phone;
        this.date=date;
        mon = tue = wed = thu = fri = sat = sun = -1;
    }

    public Students(){
        mon = tue = wed = thu = fri = sat = sun = -1;
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
    public int getDate(){
        return date;
    }
    public int getMon() { return mon; }
    public int getTue() { return tue; }
    public int getWed() { return wed; }
    public int getThu() { return thu; }
    public int getFri() { return fri; }
    public int getSat() { return sat; }
    public int getSun() { return sun; }
    public String getPar_name() { return par_name; }
    public String getPar_phone() { return par_phone; }
    public String getMemo() {return memo;}

    public void setStudent(String name, String phone, int age){
        this.name = name;
        this.phone = phone;
        this.age = age;
    }
    public void setParent(String name, String phone){
        this.par_name = name;
        this.par_phone = phone;
    }
    public void setDate(int date){
        this.date = date;
    }
    public void setMemo(String memo){
        this.memo = memo;
    }
    public void setTime(int mon, int tue, int wed, int thu, int fri, int sat, int sun){
        this.mon = mon; this.tue = tue; this.wed = wed; this.thu = thu; this.fri = fri; this.sat = sat; this.sun = sun;
    }
}
