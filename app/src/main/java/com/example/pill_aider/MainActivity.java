package com.example.pill_aider;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.pill_aider.Alarm.AlarmBuilder;
import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.Entity.Report;
import com.example.pill_aider.Entity.User;
import com.example.pill_aider.ViewModel.ReminderViewModel;
import com.example.pill_aider.ViewModel.ReportViewModel;
import com.example.pill_aider.ViewModel.UserViewModel;
import com.example.pill_aider.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    UserViewModel userViewModel;
    ReminderViewModel reminderViewModel;
    ReportViewModel reportViewModel;
    // 导航栏沉浸
    //是否使用特殊的标题栏背景颜色，android5.0以上可以设置状态栏背景色，如果不使用则使用透明色值
    protected boolean useThemestatusBarColor = false;
    //是否使用状态栏文字和图标为暗色，如果状态栏采用了白色系，则需要使状态栏和图标为暗色，android6.0以上可以设置
    protected boolean useStatusBarColor = true;
    String TAG = "MY_TAG";

    protected void setStatusBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {//5.0及以上
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            //根据上面设置是否对状态栏单独设置颜色
            getWindow().setStatusBarColor(Color.TRANSPARENT);//透明
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//4.4到5.0
            WindowManager.LayoutParams localLayoutParams = getWindow().getAttributes();
            localLayoutParams.flags = (WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS | localLayoutParams.flags);
        } else {
            Toast.makeText(this, "低于4.4的android系统版本不存在沉浸式状态栏", Toast.LENGTH_SHORT).show();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && useStatusBarColor) {//android6.0以后可以对状态栏文字颜色和图标进行修改
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // switch按钮颜色统一设定
//        setTheme(R.style.Color1SwitchStyle);
        setContentView(R.layout.activity_main);
        ActivityCollector.activities.add(this);    // protocol 点按退出（联合 activitycollector
        //实现沉浸式状态栏
        setStatusBar();
//        NavController controller = Navigation.findNavController(this,R.id.fragmentContainerView);
//        NavigationUI.setupActionBarWithNavController(this,controller);
        NavController controller;
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView);
        controller =navHostFragment.getNavController();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);

//        //KD Test
//        //User user = new User("10:10","15:30","19:30",5,1);
//        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
//        //userViewModel.insertUser(user);
//        userViewModel.getAllUsersLive().observe(this, new Observer<List<User>>() {
//            @Override
//            public void onChanged(List<User> users) {
//                StringBuilder text = new StringBuilder();
//                for(int i=0;i<users.size();i++) {
//                    User user = users.get(i);
//                    Log.d(TAG, String.valueOf(user.getId()));
//                }
//            }
//        });

//        VibrateUtil.vibrate(this);
//        MediaUtil.playRing(this);
//        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
//        List<User> users = userViewModel.getAllUsers();
//        for(int i = 0;i<users.size();i++){
//            userViewModel.deleteUser(users.get(i));
//        }


        // 通知测试！！！！！！！！！！！！！！！！！
//        User user = new User("10:01","15:20","04:04",5,5);
//        user.setId(99);
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
//        List<User> users = userViewModel.getAllUsers();
//        for(User u:users)
//            Log.d("用户测试",String.valueOf(u.getId()));
        User user = userViewModel.getUserByID(1);
//        if(userViewModel.getUserByID(99) == null)
//            userViewModel.insertUser(user);
//        else
//            userViewModel.updateUser(user);
        AlarmBuilder alarm = new AlarmBuilder(user);
        Reminder r = new Reminder("含笑半步颠",3,2,2,3,3,"极乐登仙");
        r.setItem_id(9);
        ReminderViewModel reminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);
        List<Reminder> rs = reminderViewModel.getAllReminders();
        for(Reminder rr:rs) {
            Log.e("reminder", String.valueOf(rr.getItem_id()));
            alarm.cancelAlarm(this, rr);
        }
//        r = reminderViewModel.getReminderByID(11);
////        alarm.updateAlarm(this,r);
//        if(reminderViewModel.getReminderByID(9) == null)
//            reminderViewModel.insertReminder(r);

//        alarm.cancelAlarm(this,r);
//        alarm.createAlarm(this,r );
        // ！！！！！！！！！！！！！！
//        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

//
//        AlarmBuilder alarm = new AlarmBuilder(user);
//        alarm.createAlarm(this, r);


//        alarm.createAlarm(this, r);
//        userViewModel.insertUser(user);
//
//        Reminder reminder = new Reminder("感冒灵",3,1,1,1,3,"我是lhk的好父亲");
//        reminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);
//        reminderViewModel.insertReminder(reminder);
//
//        reportViewModel =new ViewModelProvider(this).get(ReportViewModel.class);
//        Report report1=new Report("2020-6-13",3,3);
//        Report report2=new Report("2020-6-14",1,3);
//        Report report3=new Report("2020-6-15",3,4);
//        Report report4=new Report("2020-6-16",1,6);
//        Report report5=new Report("2020-6-17",2,2);
//        Report report6=new Report("2020-6-18",5,0);
//        Report report7=new Report("2020-6-19",3,0);
//        reportViewModel.insertReport(report1);
//        reportViewModel.insertReport(report2);
//        reportViewModel.insertReport(report3);
//        reportViewModel.insertReport(report4);
//        reportViewModel.insertReport(report5);
//        reportViewModel.insertReport(report6);
//        reportViewModel.insertReport(report7);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: ");
    }
    //1
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getAllUsersLive().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for(int i=0;i<users.size();i++) {
                    User user = users.get(i);
                    userViewModel.deleteUser(user);
                }
            }
        });
    }
}