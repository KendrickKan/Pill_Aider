package com.example.pill_aider.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.pill_aider.Entity.PillAiderFunction;
import com.example.pill_aider.Entity.Reminder;
import com.example.pill_aider.Entity.Report;
import com.example.pill_aider.Repository.PillAiderRepository;

import java.util.List;
import java.util.Objects;

public class ReportViewModel extends AndroidViewModel {
    private PillAiderRepository pillAiderRepository;

    public ReportViewModel(@NonNull Application application) {
        super(application);
        pillAiderRepository = new PillAiderRepository(application);
    }

    public LiveData<List<Report>> getAllReportsLive() {
        return pillAiderRepository.getAllReportsLive();
    }

    public List<Report> getAllReports() {
        return pillAiderRepository.getAllReports();
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

    public void takeMedicineOnTime() {
        List<Report> reports = pillAiderRepository.getAllReports();
        String todayDate = PillAiderFunction.getTodayDate();
        Report todayReport = null;
        for (int i = 0; i < reports.size(); i++) {
            if (Objects.equals(reports.get(i).getReport_date(), todayDate)) {
                todayReport = reports.get(i);
            }
        }
        if (null != todayReport) {
            todayReport.setOk_num(todayReport.getOk_num() + 1);
            pillAiderRepository.updateReport(todayReport);
        } else {
            todayReport = new Report(todayDate, 1, 0);
            pillAiderRepository.insertReport(todayReport);
        }
    }
//
    public void notTakeMedicineOnTime() {
        List<Report> reports = pillAiderRepository.getAllReports();
        String todayDate = PillAiderFunction.getTodayDate();
        Report todayReport = null;
        for (int i = 0; i < reports.size(); i++) {
            if (Objects.equals(reports.get(i).getReport_date(), todayDate)) {
                todayReport = reports.get(i);
            }
        }
        if (null != todayReport) {
            todayReport.setNo_num(todayReport.getNo_num() + 1);
            pillAiderRepository.updateReport(todayReport);
        } else {
            todayReport = new Report(todayDate, 0, 1);
            pillAiderRepository.insertReport(todayReport);
        }
    }

}
