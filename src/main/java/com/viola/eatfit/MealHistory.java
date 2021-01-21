package com.viola.eatfit;


public class MealHistory {
    String date;
    String bName;
    String bservings;
    String bTime;
    String lname;
    String lservings;
    String ltime;
    String sname;
    String sservings;
    String stime;

    public MealHistory(String date, String bName, String bservings, String bTime, String lname, String lservings, String ltime, String sname, String sservings, String stime) {
        this.date = date;
        this.bName = bName;
        this.bservings = bservings;
        this.bTime = bTime;
        this.lname = lname;
        this.lservings = lservings;
        this.ltime = ltime;
        this.sname = sname;
        this.sservings = sservings;
        this.stime = stime;
    }

    public String getDate() {
        return date;
    }

    public String getbName() {
        return bName;
    }

    public String getBservings() {
        return bservings;
    }

    public String getbTime() {
        return bTime;
    }

    public String getLname() {
        return lname;
    }

    public String getLservings() {
        return lservings;
    }

    public String getLtime() {
        return ltime;
    }

    public String getSname() {
        return sname;
    }

    public String getSservings() {
        return sservings;
    }

    public String getStime() {
        return stime;
    }
}
