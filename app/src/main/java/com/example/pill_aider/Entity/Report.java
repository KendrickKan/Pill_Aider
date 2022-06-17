package com.example.pill_aider.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Report {

    @PrimaryKey(autoGenerate = true)
    private int id;  //编号

    @ColumnInfo(name = "REPORTDATE")
    private String report_date;  //日期

    @ColumnInfo(name = "OK_NUM")
    private int ok_num;//按时服药次数

    @ColumnInfo(name = "NO_NUM")
    private int no_num;//未按时服药次数

    public Report(String report_date, int ok_num, int no_num) {
        this.report_date = report_date;
        this.ok_num = ok_num;
        this.no_num = no_num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReport_date() {
        return report_date;
    }

    public void setReport_date(String report_date) {
        this.report_date = report_date;
    }

    public int getOk_num() {
        return ok_num;
    }

    public void setOk_num(int ok_num) {
        this.ok_num = ok_num;
    }

    public int getNo_num() {
        return no_num;
    }

    public void setNo_num(int no_num) {
        this.no_num = no_num;
    }
}
