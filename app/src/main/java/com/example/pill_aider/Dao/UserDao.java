package com.example.pill_aider.Dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.pill_aider.Entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Insert
    void insertUser(User... users);

    @Update
    void updateUser(User... users);

    @Delete
    void deleteUser(User... users);

    @Query("SELECT * FROM USER")
    LiveData<List<User>> getAllUsers();
}
