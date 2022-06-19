package com.example.pill_aider.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.util.Log;

import com.example.pill_aider.Entity.PillAiderFunction;
import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.Entity.User;

import java.util.List;

/**
 * @description 由reminder与user设置定时启动activity的工具类
 */
public class AlarmBuilder {
    static private final long MilisOfOneDay = 1000*60*60*24;
    static final String action =    "com.example.pill_aider.Alarm.AlarmReceiverActivity.set_notify";
    static final String ExtraReminderId = "com.example.pill_aider.Alarm.AlarmReceiverActivity.ExtraReminderId";
    static final String ExtraUserId = "com.example.pill_aider.Alarm.AlarmReceiverActivity.ExtraUserId";

    int breakH,breakM,lunchH,lunchM,dinH,dinM;
    int userId;

    public AlarmBuilder(User user){
        userId = user.getId();
        List<Integer> list = PillAiderFunction.stringToTwoTime(user.getBre_time());
        breakH = list.get(0);
        breakM = list.get(1);
        list = PillAiderFunction.stringToTwoTime(user.getLun_time());
        lunchH = list.get(0);
        lunchM = list.get(1);
        list = PillAiderFunction.stringToTwoTime(user.getDin_time());
        dinH = list.get(0);
        dinM = list.get(1);
    }

    /**
     * @description 设置一个提醒
     * @param context
     * @param reminder
     */
    public void createAlarm(Context context, Reminder reminder){
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if(reminder.getNum_day() == 2 || reminder.getNum_day()==3) {
            PendingIntent breakIntent = getPendingIntent(context, reminder.getItem_id(), toId1(reminder), userId, PendingIntent.FLAG_UPDATE_CURRENT);
            PendingIntent dinIntent   = getPendingIntent(context, reminder.getItem_id(), toId3(reminder), userId, PendingIntent.FLAG_UPDATE_CURRENT);
//            alarm.setExact(AlarmManager.RTC_WAKEUP, getMilliSecondsNext(breakH,breakM), breakIntent);
//            alarm.setExact(AlarmManager.RTC_WAKEUP, getMilliSecondsNext(dinH,dinM), dinIntent);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+ getMilliSecondsNext(breakH,breakM), MilisOfOneDay, breakIntent);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+ getMilliSecondsNext(dinH, dinM), MilisOfOneDay, dinIntent);
//            Log.e("构造器",String.valueOf(MilisOfOneDay));
//            Log.e("构造器",String.valueOf(dinH));
//            Log.e("构造器",String.valueOf(dinM));
            Log.e("构造器",String.valueOf(getMilliSecondsNext(dinH, dinM)));
        }

        if(reminder.getNum_day() != 2){
            PendingIntent lunchIntent = getPendingIntent(context, reminder.getItem_id(), toId2(reminder), userId, PendingIntent.FLAG_UPDATE_CURRENT);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()+ getMilliSecondsNext(lunchH,lunchM), MilisOfOneDay, lunchIntent);
        }
    }

    /**
     * @description 更新一个提醒
     */
    public void updateAlarm(Context context, Reminder reminder){
        cancelAlarm(context, reminder);
        createAlarm(context, reminder);
    }

    /**
     * @description 取消一个提醒
     * @param context
     * @param reminder
     */
    public void cancelAlarm(Context context, Reminder reminder){
        AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

        if(reminder.getNum_day()==2||reminder.getNum_day()==3){
            PendingIntent breakIntent = getPendingIntent(context, reminder.getItem_id(), toId1(reminder), userId, PendingIntent.FLAG_CANCEL_CURRENT);
            PendingIntent lunchIntent = getPendingIntent(context, reminder.getItem_id(), toId2(reminder), userId, PendingIntent.FLAG_CANCEL_CURRENT);
            alarm.cancel(breakIntent);
            alarm.cancel(lunchIntent);

        }
        if(reminder.getNum_day()!=2){
            PendingIntent dinIntent   = getPendingIntent(context, reminder.getItem_id(), toId3(reminder), userId, PendingIntent.FLAG_UPDATE_CURRENT);
            alarm.cancel(dinIntent);
        }
    }

    static private PendingIntent getPendingIntent(Context context, int reminderId, int intentId, int userId, int flag){
        //TODO 修改为新的提醒activity
        Intent intent = new Intent(context, AlarmActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra(ExtraReminderId, reminderId);
        intent.putExtra(ExtraUserId, userId);

        intent.setAction(action);

        return PendingIntent.getActivity(context, intentId, intent, flag|PendingIntent.FLAG_IMMUTABLE);
    }

    /**
     * @description 获取当前时间到指定时间点的时间差
     * @param hour 小时
     * @param minute 分钟
     * @return 到现在的时间差，单位毫秒
     */
    static private long getMilliSecondsNext(int hour,int minute) {
        long systemTime = System.currentTimeMillis();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        // 选择的定时时间
        long selectTime = calendar.getTimeInMillis();
        // 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
        if(systemTime > selectTime + 28800000L) {//28800000L为东八区与utc的差
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            selectTime = calendar.getTimeInMillis();
        }
        // 计算设定时间到现在时间的时间差

        return selectTime-systemTime- 28800000L;//减去八小时时区差
    }

    static private int toId1(Reminder r){
        return r.getItem_id();
    }
    static private int toId2(Reminder r){
        return -r.getItem_id()-1;
    }
    static private int toId3(Reminder r){
        return 4*(r.getItem_id()+1);
    }
}
