package com.example.pill_aider.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.Repository.PillAiderRepository;

import java.util.List;

public class ReminderViewModel extends AndroidViewModel {
    private PillAiderRepository pillAiderRepository;
    public ReminderViewModel(@NonNull Application application) {
        super(application);
        pillAiderRepository = new PillAiderRepository(application);
    }
    LiveData<List<Reminder>> getAllRemindersLive(){
        return pillAiderRepository.getAllRemindersLive();
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
}
