package com.example.pill_aider.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Reminder {


    @PrimaryKey(autoGenerate = true)
    private int item_id;  //药物编号

    @ColumnInfo(name = "ITEM_NAME")
    private String item_name;  //药物名

    @ColumnInfo(name = "NUM_DAY")
    private int num_day;  //每日次数

    @ColumnInfo(name = "DASAGE_PER_TIME")
    private int dasage_per_time;  //每次用量

    @ColumnInfo(name = "ITEM_TYPE")
    private int item_type;//药品用量单位（片、粒、毫升）

    @ColumnInfo(name = "ITEM_TIME")
    private int item_time;  //服用时间（饭前、饭中、饭后）

    @ColumnInfo(name = "ITEM_REM")
    private int item_rem; //提醒方式（响铃、振动、二者）

    @ColumnInfo(name = "NOTICE")
    private String notice;  //注意事项

    public Reminder(String item_name, int num_day, int dasage_per_time, int item_type, int item_time, int item_rem, String notice) {
        this.item_name = item_name;
        this.num_day = num_day;
        this.dasage_per_time = dasage_per_time;
        this.item_type = item_type;
        this.item_time = item_time;
        this.item_rem = item_rem;
        this.notice = notice;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getNum_day() {
        return num_day;
    }

    public void setNum_day(int num_day) {
        this.num_day = num_day;
    }

    public int getDasage_per_time() {
        return dasage_per_time;
    }

    public void setDasage_per_time(int dasage_per_time) {
        this.dasage_per_time = dasage_per_time;
    }

    public int getItem_type() {
        return item_type;
    }

    public void setItem_type(int item_type) {
        this.item_type = item_type;
    }

    public int getItem_time() {
        return item_time;
    }

    public void setItem_time(int item_time) {
        this.item_time = item_time;
    }

    public int getItem_rem() {
        return item_rem;
    }

    public void setItem_rem(int item_rem) {
        this.item_rem = item_rem;
    }

    public String getNotice() {
        return notice;
    }

    public void setNotice(String notice) {
        this.notice = notice;
    }
}
