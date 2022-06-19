package com.example.pill_aider.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.Repository.PillAiderRepository;

import java.util.ArrayList;
import java.util.List;

public class ReminderViewModel extends AndroidViewModel {
    private PillAiderRepository pillAiderRepository;

    public ReminderViewModel(@NonNull Application application) {
        super(application);
        pillAiderRepository = new PillAiderRepository(application);
    }

    public LiveData<List<Reminder>> getAllRemindersLive() {
        return pillAiderRepository.getAllRemindersLive();
    }

    public List<Reminder> getAllReminders() {
        return pillAiderRepository.getAllReminders();
    }

    public void insertReminder(Reminder... reminders) {
        pillAiderRepository.insertReminder(reminders);
    }

    public void updateReminder(Reminder... reminders) {
        pillAiderRepository.updateReminder(reminders);
    }

    public void deleteReminder(Reminder... reminders) {
        pillAiderRepository.deleteReminder(reminders);
    }

    //如果有一次的返回一个list 0，1，0
    //如果有两次的返回一个list 1，0，1
    //如果有三次的返回一个list 1，1，1
    public List<Integer> getTakeMedicineNum() {
        List<Reminder> reminders = pillAiderRepository.getAllReminders();
        boolean bre = false;
        boolean lun = false;
        boolean din = false;
        for (int i = 0; i < reminders.size(); i++) {
            if (reminders.get(i).getNum_day() == 1) {
                lun = true;
            } else if (reminders.get(i).getNum_day() == 2) {
                bre = true;
                din = true;
            } else {
                bre = true;
                lun = true;
                din = true;
            }
        }
        List<Integer> list = new ArrayList<>();
        if (bre) list.add(1);
        else list.add(0);
        if (lun) list.add(1);
        else list.add(0);
        if (din) list.add(1);
        else list.add(0);
        return list;
    }

}
