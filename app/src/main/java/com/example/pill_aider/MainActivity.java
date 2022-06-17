package com.example.pill_aider;

import android.os.Bundle;
import android.util.Log;

import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.Entity.Report;
import com.example.pill_aider.Entity.User;
import com.example.pill_aider.ViewModel.ReminderViewModel;
import com.example.pill_aider.ViewModel.ReportViewModel;
import com.example.pill_aider.ViewModel.UserViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.pill_aider.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    UserViewModel userViewModel;
    ReminderViewModel reminderViewModel;
    ReportViewModel reportViewModel;

    String TAG = "MY_TAG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(binding.navView, navController);

        //KD Test
        User user = new User("10","20","30",5,1);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.insertUser(user);
        userViewModel.getAllUsersLive().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                StringBuilder text = new StringBuilder();
                for(int i=0;i<users.size();i++) {
                    User user = users.get(i);
                    Log.d(TAG, String.valueOf(user.getId()));
                }
            }
        });

        Reminder reminder = new Reminder("感冒灵",3,1,1,1,3,"我是lhk的好父亲");
        reminderViewModel = new ViewModelProvider(this).get(ReminderViewModel.class);
        reminderViewModel.insertReminder(reminder);

        Report report=new Report("2020-06-27",3,3);
        reportViewModel =new ViewModelProvider(this).get(ReportViewModel.class);
        reportViewModel.insertReport(report);

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
    }
}