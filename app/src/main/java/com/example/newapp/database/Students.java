package com.example.newapp.database;

import java.io.Serializable;

import io.realm.RealmObject;

public class Students extends RealmObject implements Serializable {
    private String name, phone;
    int date;
    private int mon, tue, wed, thu, fri, sat, sun;
    private int age;
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

    public String getInfo(){

        String day="";
        if(mon!=-1) day=day+" 월요일 "+mon/100+"시 "+mon%100+"분 ";
        if(tue!=-1) day=day+", 화요일 "+tue/100+"시 "+tue%100+"분 ";
        if(wed!=-1) day=day+", 수요일 "+wed/100+"시 "+wed%100+"분 ";
        if(thu!=-1) day=day+", 목요일 "+thu/100+"시 "+thu%100+"분 ";
        if(fri!=-1) day=day+", 금요일 "+fri/100+"시 "+fri%100+"분 ";
        if(sat!=-1) day=day+", 토요일 "+sat/100+"사 "+sat%100+"분 ";
        if(sun!=-1) day=day+", 일요일 "+sun/100+"시 "+sat%100+"분 ";

        String stu="";
        stu=stu+"이름: "+name+'\n'+"나이: "+age+'\n'+"전화: "+phone+'\n'+"등원 시간: "+day+'\n'+"결제 날짜: 매달 "+date+"일"+'\n'+'\n'
                +"학부모 이름: "+par_name+'\n'+"학부모 전화: "+par_phone+'\n'+"메모: "+memo+'\n';

        return stu;
    }

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
