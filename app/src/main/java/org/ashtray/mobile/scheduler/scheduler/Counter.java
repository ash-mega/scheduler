package org.ashtray.mobile.scheduler.scheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

import org.ashtray.mobile.scheduler.util.Utils;

import static org.ashtray.mobile.scheduler.IConstants.NEEDS_RESCHEDULED;
import static org.ashtray.mobile.scheduler.IConstants.START_RETURN;
import static org.ashtray.mobile.scheduler.IConstants.STOP_RETURN;
import static org.ashtray.mobile.scheduler.IConstants.WHAT_INT;

public class Counter extends JobService {
    
    @Override
    public boolean onStartJob(JobParameters jobParameters) {
        Utils.log("onStartJob");
        mJobHandler.sendMessage(Message.obtain(mJobHandler,WHAT_INT,jobParameters));
        return START_RETURN;
    }
    
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        Utils.log("onStopJob");
        mJobHandler.removeMessages(WHAT_INT);
        return STOP_RETURN;
    }
    
    private Handler mJobHandler = new Handler(new Handler.Callback() {
        
        @Override
        public boolean handleMessage(Message msg) {
            Utils.log("handleMessage");
            Toast.makeText(getApplicationContext(),"JobService task running",Toast.LENGTH_SHORT).show();
            jobFinished((JobParameters)msg.obj,NEEDS_RESCHEDULED);
            return true;
        }
    });
}
