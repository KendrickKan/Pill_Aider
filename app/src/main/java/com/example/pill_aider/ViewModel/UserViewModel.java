package com.example.pill_aider.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pill_aider.Database.PillAiderDatabase;
import com.example.pill_aider.Entity.User;
import com.example.pill_aider.Repository.PillAiderRepository;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private PillAiderRepository pillAiderRepository;
    public UserViewModel(@NonNull Application application) {
        super(application);
        pillAiderRepository = new PillAiderRepository(application);
    }

    public LiveData<List<User>> getAllUsersLive(){
        return pillAiderRepository.getAllUsersLive();
    }

    public void insertUser(User... users) {
        pillAiderRepository.insertUser(users);
    }
    public void updateUser(User... users) {
        pillAiderRepository.updateUser(users);
    }
    public void deleteUser(User... users) {
        pillAiderRepository.deleteUser(users);
    }

//    public String twoTimeToString(int h,int m){
//        String
//    }
}
