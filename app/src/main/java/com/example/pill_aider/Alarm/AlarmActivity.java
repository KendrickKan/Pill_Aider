package com.example.pill_aider.Alarm;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.Entity.User;
import com.example.pill_aider.R;
import com.example.pill_aider.ViewModel.ReminderViewModel;
import com.example.pill_aider.ViewModel.ReportViewModel;
import com.example.pill_aider.ViewModel.UserViewModel;

public class AlarmActivity extends AppCompatActivity {

    private TextView item_name, dasage_per_time, item_type, item_time, item_rem, notice;

    @RequiresApi(api = Build.VERSION_CODES.R)
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
        if(reminder == null)
            Log.e("alarm", "reminder is null!");

        item_name = findViewById(R.id.textView67);
        dasage_per_time = findViewById(R.id.textView64);
        item_type = findViewById(R.id.textView65);
        notice = findViewById(R.id.textView63);
        Button button_fail = findViewById(R.id.button6);
        Button button_done = findViewById(R.id.button7);
        // 服药未成功
        button_fail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(AlarmActivity.this,getString(R.string.button_fail), Toast.LENGTH_SHORT);//实例化toast对象
                toast.show();
                VibrateUtil.cancel(AlarmActivity.this);
                MediaUtil.stopRing();
                finish();
            }
        });
        // 服药成功
        button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ReportViewModel reportViewModel = new ViewModelProvider(AlarmActivity.this).get(ReportViewModel.class);
                reportViewModel.takeMedicineOnTime();
                Toast toast = Toast.makeText(AlarmActivity.this,getString(R.string.Button_done), Toast.LENGTH_SHORT);//实例化toast对象
                toast.show();
                VibrateUtil.cancel(AlarmActivity.this);
                MediaUtil.stopRing();
                finish();
            }
        });

        item_name.setText(reminder.getItem_name());
        dasage_per_time.setText(String.valueOf(reminder.getDasage_per_time()));
        item_type.setText(itemTypeToString(reminder.getItem_type()));
        notice.setText(reminder.getNotice());

        VibrateUtil.vibrate(this);
        MediaUtil.playRing(this);
    }

    private String itemTypeToString(int itemType){
        switch (itemType){
            case 1:
                return getString(R.string.pill_type_piece);
            case 2:
                return getString(R.string.pill_type_grain);
            case 3:
                return getString(R.string.pill_type_ml);
            default:
                return "";
        }
    }
}