package com.example.pill_aider.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pill_aider.Entity.Reminder;


import java.util.List;

@Dao
public interface ReminderDao {
    @Insert
    void insertReminder(Reminder reminder);

    @Update
    void updateReminder(Reminder reminder);

    @Delete
    void deleteReminder(Reminder reminder);

    @Query("SELECT * FROM Reminder")
    LiveData<List<Reminder>> getAllReminders();
}
