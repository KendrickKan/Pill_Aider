package com.example.pill_aider.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.pill_aider.Dao.UserDao;
import com.example.pill_aider.Entity.User;

@Database(entities = {User.class},version = 1,exportSchema = false)
public abstract class PillAiderDatabase extends RoomDatabase {
    private static PillAiderDatabase INSTANCE;
    //单例
    public static synchronized PillAiderDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),PillAiderDatabase.class,"pill_aider_database")
                    .build();
        }
        return INSTANCE;
    }
    public abstract UserDao getUserDao();
}
