package com.example.pill_aider.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.pill_aider.Dao.ReminderDao;
import com.example.pill_aider.Dao.UserDao;
import com.example.pill_aider.Database.PillAiderDatabase;
import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.Entity.User;

import java.util.List;

public class PillAiderRepository {
    private LiveData<List<User>> allUsersLive;
    private LiveData<List<Reminder>> allRemindersLive;
    private UserDao userDao;
    private ReminderDao reminderDao;

    public PillAiderRepository(Context context) {
        PillAiderDatabase pillAiderDatabase = PillAiderDatabase.getDatabase(context.getApplicationContext());
        userDao = pillAiderDatabase.getUserDao();
        reminderDao = pillAiderDatabase.getReminderDao();
        allUsersLive = userDao.getAllUsers();
        allRemindersLive = reminderDao.getAllReminders();
    }

    public void insertUser(User... users) {
        new InsertAsyncTask(userDao).execute(users);
    }

    public void updateUser(User... users) {
        new UpdateAsyncTask(userDao).execute(users);
    }

    public void deleteUser(User... users) {
        new DeleteAsyncTask(userDao).execute(users);
    }

    public LiveData<List<User>> getAllUsersLive() {
        return allUsersLive;
    }

    public void insertReminder(Reminder... reminders){
        new InsertAsyncTasks(reminderDao).execute(reminders);
    }

    public void updateReminder(Reminder... reminders){
        new UpdateAsyncTasks(reminderDao).execute(reminders);
    }

    public void deleteReminder(Reminder... reminders){
        new DeleteAsyncTasks(reminderDao).execute(reminders);
    }

    public LiveData<List<Reminder>> getAllRemindersLive(){
        return allRemindersLive;
    }

    static class InsertAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        InsertAsyncTask(UserDao userDao){
            this.userDao=userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users);
            return null;
        }
    }

    static class UpdateAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        UpdateAsyncTask(UserDao userDao){
            this.userDao=userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.updateUser(users);
            return null;
        }
    }

    static class DeleteAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        DeleteAsyncTask(UserDao userDao){
            this.userDao=userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.deleteUser(users);
            return null;
        }
    }

    static class InsertAsyncTasks extends AsyncTask<Reminder,Void,Void>{
        private ReminderDao reminderDao;

        InsertAsyncTasks(ReminderDao reminderDao){
            this.reminderDao=reminderDao;
        }
        @Override
        protected Void doInBackground(Reminder... reminders) {
            reminderDao.insertReminder(reminders);
            return null;
        }
    }
    static class UpdateAsyncTasks extends AsyncTask<Reminder,Void,Void>{
        private ReminderDao reminderDao;

        UpdateAsyncTasks(ReminderDao reminderDao){
            this.reminderDao=reminderDao;
        }
        @Override
        protected Void doInBackground(Reminder... reminders) {
            reminderDao.updateReminder(reminders);
            return null;
        }
    }

    static class DeleteAsyncTasks extends AsyncTask<Reminder,Void,Void>{
        private ReminderDao reminderDao;

        DeleteAsyncTasks(ReminderDao reminderDao){
            this.reminderDao=reminderDao;
        }
        @Override
        protected Void doInBackground(Reminder... reminders) {
            reminderDao.deleteReminder(reminders);
            return null;
        }
    }

}
