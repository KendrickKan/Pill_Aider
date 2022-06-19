package com.example.pill_aider.Alarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.Entity.User;
import com.example.pill_aider.R;
import com.example.pill_aider.ViewModel.ReminderViewModel;
import com.example.pill_aider.ViewModel.UserViewModel;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        //获取唤起该通知的User与Reminder
        int userId = getIntent().getIntExtra(AlarmBuilder.ExtraUserId,0);
        int reminderId = getIntent().getIntExtra(AlarmBuilder.ExtraReminderId, 0);
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        User user = userViewModel.getUserByID(userId);
        ReminderViewModel reminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);
        Reminder reminder = reminderViewModel.getReminderByID(reminderId);


    }
}