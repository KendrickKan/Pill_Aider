package com.example.pill_aider.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pill_aider.Dao.ReminderDao;
import com.example.pill_aider.Dao.ReportDao;
import com.example.pill_aider.Dao.UserDao;
import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.Entity.Report;
import com.example.pill_aider.Entity.User;

@Database(entities = {User.class, Reminder.class, Report.class},version = 2,exportSchema = false)
public abstract class PillAiderDatabase extends RoomDatabase {
    private static PillAiderDatabase INSTANCE;
    //单例
    public static synchronized PillAiderDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),PillAiderDatabase.class,"pill_aider_database")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return INSTANCE;
    }
    public abstract UserDao getUserDao();
    public abstract ReminderDao getReminderDao();
    public abstract ReportDao getReportDao();
}
