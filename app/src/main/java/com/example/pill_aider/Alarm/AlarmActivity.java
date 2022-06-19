package com.example.pill_aider.Alarm;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.Entity.User;
import com.example.pill_aider.R;
import com.example.pill_aider.ViewModel.ReminderViewModel;
import com.example.pill_aider.ViewModel.UserViewModel;

public class AlarmActivity extends AppCompatActivity {

    private TextView item_name, num_day, dasage_per_time, item_type, item_time, item_rem, notice;

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

        item_name = findViewById(R.id.textView67);
        num_day = findViewById(R.id.textView66);
        dasage_per_time = findViewById(R.id.textView64);
        item_type = findViewById(R.id.textView65);
        notice = findViewById(R.id.textView63);

        item_name.setText(reminder.getItem_name());
        num_day.setText(String.valueOf(reminder.getNum_day()));
        dasage_per_time.setText(String.valueOf(reminder.getDasage_per_time()));
        item_type.setText(String.valueOf(reminder.getItem_type()));
        notice.setText(reminder.getNotice());
    }
}