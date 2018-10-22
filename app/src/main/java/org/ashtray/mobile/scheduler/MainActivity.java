package org.ashtray.mobile.scheduler;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.ashtray.mobile.scheduler.notification.NotificationCentre;
import org.ashtray.mobile.scheduler.scheduler.Counter;
import org.ashtray.mobile.scheduler.task.WorkTask;
import org.ashtray.mobile.scheduler.util.Utils;

import static org.ashtray.mobile.scheduler.IConstants.ID_INT;
import static org.ashtray.mobile.scheduler.IConstants.JOB_ID;
import static org.ashtray.mobile.scheduler.IConstants.PERIODIC;

public class MainActivity extends AppCompatActivity {

    private NotificationCentre centre;

    private TextView tvProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvProgress = findViewById(R.id.tv_progress);

        centre = new NotificationCentre(this);
    }

    private void start() {
        startTask();
    }

    private void stop() {
        centre.dismiss(ID_INT);
    }

    private void startJob() {
        JobScheduler mJobScheduler = (JobScheduler)getSystemService(Context.JOB_SCHEDULER_SERVICE);
        JobInfo.Builder builder = new JobInfo.Builder(JOB_ID,new ComponentName(getPackageName(),Counter.class.getName()));

        builder.setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY);
        builder.setPeriodic(PERIODIC);
        builder.setPersisted(true);

        int r = mJobScheduler.schedule(builder.build());
        Utils.log("schedule job and return: " + r);
    }

    private void startTask() {
        new WorkTask(this).execute(50);
    }

    public void showProgress(int progress) {
        tvProgress.setText(Utils.toString(progress));
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                start();
                break;
            case R.id.btn_stop:
                stop();
                break;
        }
    }
}
