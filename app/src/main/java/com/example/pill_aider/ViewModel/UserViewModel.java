package com.example.pill_aider.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pill_aider.Database.PillAiderDatabase;
import com.example.pill_aider.Entity.PillAiderFunction;
import com.example.pill_aider.Entity.User;
import com.example.pill_aider.Repository.PillAiderRepository;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private PillAiderRepository pillAiderRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        pillAiderRepository = new PillAiderRepository(application);
    }

    public LiveData<List<User>> getAllUsersLive() {
        return pillAiderRepository.getAllUsersLive();
    }

    public List<User> getAllUsers() {
        return pillAiderRepository.getAllUsers();
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

    //获取当前时间到三餐时间的最近一个三餐时间
    //eg 返回午餐时间 list：12，21，0/1/2/3
    //其中0/1/2/3 代表处于的时段，0代表在早餐之前，1代表在早午餐间，以此类推
    public List<Integer> getRecentTime() {
        List<User> userList = pillAiderRepository.getAllUsers();
        User user = userList.get(userList.size() - 1);
        int bre_time_H, bre_time_M;
        int lun_time_H, lun_time_M;
        int din_time_H, din_time_M;
        bre_time_H = PillAiderFunction.stringToTwoTime(user.getBre_time()).get(0);
        bre_time_M = PillAiderFunction.stringToTwoTime(user.getBre_time()).get(1);
        lun_time_H = PillAiderFunction.stringToTwoTime(user.getLun_time()).get(0);
        lun_time_M = PillAiderFunction.stringToTwoTime(user.getLun_time()).get(1);
        din_time_H = PillAiderFunction.stringToTwoTime(user.getDin_time()).get(0);
        din_time_M = PillAiderFunction.stringToTwoTime(user.getDin_time()).get(1);
        int bre_time, lun_time, din_time;
        bre_time = bre_time_H * 60 + bre_time_M;
        lun_time = lun_time_H * 60 + lun_time_M;
        din_time = din_time_H * 60 + din_time_M;
        GregorianCalendar calendar = new GregorianCalendar();
        int nowH = calendar.get(Calendar.HOUR_OF_DAY);
        int nowM = calendar.get(Calendar.MINUTE);
        int nowTime = nowH * 60 + nowM;
        List<Integer> list = new ArrayList<>();
        if (nowTime < bre_time) {
            list.add(bre_time_H);
            list.add(bre_time_M);
            list.add(0);
        } else if (nowTime < lun_time) {
            list.add(lun_time_H);
            list.add(lun_time_M);
            list.add(1);
        } else if (nowTime < din_time) {
            list.add(din_time_H);
            list.add(din_time_M);
            list.add(2);
        } else {
            list.add(0);
            list.add(0);
            list.add(3);
        }
        return list;
    }

    //获取当前时间到早餐时间的时间间隔
    //list 小时，分钟
    //如果已经超过 返回 0，0
    public List<Integer> getNowTimeToBreTime() {
        List<User> userList = pillAiderRepository.getAllUsers();
        User user = userList.get(userList.size() - 1);
        int bre_time_H, bre_time_M;
        bre_time_H = PillAiderFunction.stringToTwoTime(user.getBre_time()).get(0);
        bre_time_M = PillAiderFunction.stringToTwoTime(user.getBre_time()).get(1);
        int bre_time = bre_time_H * 60 + bre_time_M;
        GregorianCalendar calendar = new GregorianCalendar();
        int nowH = calendar.get(Calendar.HOUR_OF_DAY);
        int nowM = calendar.get(Calendar.MINUTE);
        int nowTime = nowH * 60 + nowM;
        List<Integer> list = new ArrayList<>();
        if (nowTime > bre_time) {
            list.add(0);
            list.add(0);
        } else {
            if (bre_time >= nowM) {
                list.add(bre_time_H - nowH);
                list.add(bre_time_M - nowM);
            }
            else{
                list.add(bre_time_H - nowH - 1);
                list.add(60 - nowM + bre_time_M);
            }
        }
        return list;
    }

    //获取当前时间到午餐时间的时间间隔
    //list 小时，分钟
    //如果已经超过 返回 0，0
    public List<Integer> getNowTimeToLunTime() {
        List<User> userList = pillAiderRepository.getAllUsers();
        User user = userList.get(userList.size() - 1);
        int bre_time_H, bre_time_M;
        bre_time_H = PillAiderFunction.stringToTwoTime(user.getLun_time()).get(0);
        bre_time_M = PillAiderFunction.stringToTwoTime(user.getLun_time()).get(1);
        int bre_time = bre_time_H * 60 + bre_time_M;
        GregorianCalendar calendar = new GregorianCalendar();
        int nowH = calendar.get(Calendar.HOUR_OF_DAY);
        int nowM = calendar.get(Calendar.MINUTE);
        int nowTime = nowH * 60 + nowM;
        List<Integer> list = new ArrayList<>();
        if (nowTime > bre_time) {
            list.add(0);
            list.add(0);
        } else {
            if (bre_time >= nowM) {
                list.add(bre_time_H - nowH);
                list.add(bre_time_M - nowM);
            }
            else{
                list.add(bre_time_H - nowH - 1);
                list.add(60 - nowM + bre_time_M);
            }
        }
        return list;
    }

    //获取当前时间到晚餐时间的时间间隔
    //list 小时，分钟
    //如果已经超过 返回 0，0
    public List<Integer> getNowTimeToDinTime() {
        List<User> userList = pillAiderRepository.getAllUsers();
        User user = userList.get(userList.size() - 1);
        int bre_time_H, bre_time_M;
        bre_time_H = PillAiderFunction.stringToTwoTime(user.getDin_time()).get(0);
        bre_time_M = PillAiderFunction.stringToTwoTime(user.getDin_time()).get(1);
        int bre_time = bre_time_H * 60 + bre_time_M;
        GregorianCalendar calendar = new GregorianCalendar();
        int nowH = calendar.get(Calendar.HOUR_OF_DAY);
        int nowM = calendar.get(Calendar.MINUTE);
        int nowTime = nowH * 60 + nowM;
        List<Integer> list = new ArrayList<>();
        if (nowTime > bre_time) {
            list.add(0);
            list.add(0);
        } else {
            if (bre_time >= nowM) {
                list.add(bre_time_H - nowH);
                list.add(bre_time_M - nowM);
            }
            else{
                list.add(bre_time_H - nowH - 1);
                list.add(60 - nowM + bre_time_M);
            }
        }
        return list;
    }

}
