package com.example.kuttr.stockpredictor;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.SharedPreferences;

public class Job extends JobService {
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        SharedPreferences sp=getSharedPreferences("mycred",MODE_PRIVATE);
        sp.edit().clear().commit();

        return true;
    }

    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return false;
    }
}
