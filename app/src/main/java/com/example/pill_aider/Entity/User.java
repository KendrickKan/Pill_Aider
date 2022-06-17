package com.example.pill_aider.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "BRE_TIME")
    private String bre_time;//早餐时间

    @ColumnInfo(name = "LUN_TIME")
    private String lun_time;//午餐时间

    @ColumnInfo(name = "DIN_TIME")
    private String din_time;//晚餐时间

    @ColumnInfo(name = "INTERVAL")
    private int interval;//提醒时间间隔（minutes）

    @ColumnInfo(name = "REM_NUM")
    private int rem_num;//最大提醒次数

    public User(String bre_time, String lun_time, String din_time, int interval, int rem_num) {
        this.bre_time = bre_time;
        this.lun_time = lun_time;
        this.din_time = din_time;
        this.interval = interval;
        this.rem_num = rem_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBre_time() {
        return bre_time;
    }

    public void setBre_time(String bre_time) {
        this.bre_time = bre_time;
    }

    public String getLun_time() {
        return lun_time;
    }

    public void setLun_time(String lun_time) {
        this.lun_time = lun_time;
    }

    public String getDin_time() {
        return din_time;
    }

    public void setDin_time(String din_time) {
        this.din_time = din_time;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public int getRem_num() {
        return rem_num;
    }

    public void setRem_num(int rem_num) {
        this.rem_num = rem_num;
    }
}
