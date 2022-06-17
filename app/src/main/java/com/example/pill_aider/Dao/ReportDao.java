package com.example.pill_aider.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pill_aider.Entity.Report;
import com.example.pill_aider.Entity.User;

import java.util.List;

@Dao
public interface ReportDao {
    @Insert
    void insertReport(Report... reports);

    @Update
    void updateReport(Report... reports);

    @Delete
    void deleteReport(Report... reports);

    @Query("SELECT * FROM Report")
    LiveData<List<Report>> getAllReports();
}
