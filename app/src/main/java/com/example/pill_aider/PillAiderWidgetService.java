package com.example.pill_aider;

import android.app.Service;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.widget.RemoteViews;

import androidx.lifecycle.ViewModelProvider;

import com.example.pill_aider.Entity.PillAiderFunction;
import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.Entity.User;
import com.example.pill_aider.Repository.PillAiderRepository;
import com.example.pill_aider.ViewModel.UserViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PillAiderWidgetService extends Service {
    private PillAiderRepository pillAiderRepository;


    public PillAiderWidgetService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    // 定时器
    private Timer timer;
    // 日期格式
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        timer = new Timer();
        /**
         * 参数：1.事件2.延时事件3.执行间隔事件
         */
        pillAiderRepository = new PillAiderRepository(getApplication());

        timer.schedule(new TimerTask() {

            @Override
            public void run() {
                updateView();
            }
        }, 0, 1000);
    }


    /**
     * 更新事件的方法
     */
    private void updateView() {
        // 时间
        String time = sdf.format(new Date());
        /**
         * 参数：1.包名2.小组件布局
         */
        RemoteViews rViews = new RemoteViews(getPackageName(),
                R.layout.pill_aider_widget);
        // 显示当前事件
        //rViews.setTextViewText(R.id.appwidget_text, time);

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
        List<Integer> timelist = new ArrayList<>();
        if (nowTime < bre_time) {
            timelist.add(bre_time_H);
            timelist.add(bre_time_M);
            timelist.add(0);
        } else if (nowTime < lun_time) {
            timelist.add(lun_time_H);
            timelist.add(lun_time_M);
            timelist.add(1);
        } else if (nowTime < din_time) {
            timelist.add(din_time_H);
            timelist.add(din_time_M);
            timelist.add(2);
        } else {
            timelist.add(0);
            timelist.add(0);
            timelist.add(3);
        }
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
        List<Integer> numlist = new ArrayList<>();
        if (bre) numlist.add(1);
        else numlist.add(0);
        if (lun) numlist.add(1);
        else numlist.add(0);
        if (din) numlist.add(1);
        else numlist.add(0);

        int totalnum = 0;
        totalnum += numlist.get(0);
        totalnum += numlist.get(1);
        totalnum += numlist.get(2);
        int numLeft = 0;
        if (timelist.get(2) == 0) {
            numLeft += numlist.get(0);
            numLeft += numlist.get(1);
            numLeft += numlist.get(2);
        } else if (timelist.get(2) == 1) {
            numLeft += numlist.get(1);
            numLeft += numlist.get(2);
        } else if (timelist.get(2) == 2) {
            numLeft += numlist.get(2);
        } else {
            numLeft = 0;
        }

        rViews.setTextViewText(R.id.textView33, String.valueOf(numLeft));

        int zuijinnayican = 0;
        if (totalnum == 0) {
            zuijinnayican = 3;
        } else if (totalnum == 1) {
            if (timelist.get(2) == 0 || timelist.get(2) == 1) {
                zuijinnayican = 1;//最近的一次是午餐
            } else {
                zuijinnayican = 3;//没有了}
            }
        } else if (totalnum == 2) {
            if (timelist.get(2) == 0) {
                zuijinnayican = 0;
            } else if (timelist.get(2) == 3) {
                zuijinnayican = 3;
            } else {
                zuijinnayican = 2;
            }
        } else {
            zuijinnayican = timelist.get(2);
        }


        int toH, toM;
        if (zuijinnayican == 0) {
            toH = PillAiderFunction.stringToTwoTime(user.getBre_time()).get(0);
            toM = PillAiderFunction.stringToTwoTime(user.getBre_time()).get(1);
        } else if (zuijinnayican == 1) {
            toH = PillAiderFunction.stringToTwoTime(user.getLun_time()).get(0);
            toM = PillAiderFunction.stringToTwoTime(user.getLun_time()).get(1);
        } else if (zuijinnayican == 2) {
            toH = PillAiderFunction.stringToTwoTime(user.getDin_time()).get(0);
            toM = PillAiderFunction.stringToTwoTime(user.getDin_time()).get(1);
        } else {
            toH = 0;
            toM = 0;
        }
        int totime = toH * 60 + toM;
        List<Integer> totimelist = new ArrayList<>();
        if (nowTime > totime) {
            totimelist.add(0);
            totimelist.add(0);
        } else {
            if (toM >= nowM) {
                totimelist.add(toH - nowH);
                totimelist.add(toM - nowM);
            } else {
                totimelist.add(toH - nowH - 1);
                totimelist.add(60 - nowM + toM);
            }
        }
        String timeleft = String.valueOf(totimelist.get(0)) + " h " + String.valueOf(totimelist.get(1)) + " m ";

        rViews.setTextViewText(R.id.textView36, timeleft);

        // 刷新
        AppWidgetManager manager = AppWidgetManager
                .getInstance(getApplicationContext());
        ComponentName cName = new ComponentName(getApplicationContext(),
                PillAiderWidget.class);
        manager.updateAppWidget(cName, rViews);
    }


}