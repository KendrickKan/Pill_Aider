package com.example.pill_aider.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.Entity.Report;
import com.example.pill_aider.Repository.PillAiderRepository;

import java.util.List;

public class ReportViewModel extends AndroidViewModel {
    private PillAiderRepository pillAiderRepository;
    public ReportViewModel(@NonNull Application application) {
        super(application);
        pillAiderRepository = new PillAiderRepository(application);
    }

    LiveData<List<Report>> getAllReportsLive(){
        return pillAiderRepository.getAllReportsLive();
    }

    public void insertReport(Report... reports) {
        pillAiderRepository.insertReport(reports);
    }
    public void updateReport(Report... reports) {
        pillAiderRepository.updateReport(reports);
    }
    public void deleteReport(Report... reports) {
        pillAiderRepository.deleteReport(reports);
    }
}
