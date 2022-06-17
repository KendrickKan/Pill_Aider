package com.example.pill_aider.Repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.pill_aider.Dao.UserDao;
import com.example.pill_aider.Database.PillAiderDatabase;
import com.example.pill_aider.Entity.User;

import java.util.List;

public class PillAiderRepository {
    private LiveData<List<User>> allUsersLive;
    private UserDao userDao;

    public PillAiderRepository(Context context) {
        PillAiderDatabase pillAiderDatabase = PillAiderDatabase.getDatabase(context.getApplicationContext());
        userDao = pillAiderDatabase.getUserDao();
        allUsersLive = userDao.getAllUsers();
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

    static class InsertAsyncTask extends AsyncTask<User,Void,Void>{
        private UserDao userDao;

        InsertAsyncTask(UserDao userDao){
            this.userDao=userDao;
        }
        @Override
        protected Void doInBackground(User... users) {
            userDao.insertUser(users[0]);
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
            userDao.updateUser(users[0]);
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
            userDao.deleteUser(users[0]);
            return null;
        }
    }
}
