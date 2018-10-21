package org.ashtray.mobile.scheduler.task;

import android.os.AsyncTask;

import org.ashtray.mobile.scheduler.MainActivity;
import org.ashtray.mobile.scheduler.util.Utils;

import java.lang.ref.WeakReference;

public class WorkTask extends AsyncTask<Integer,Integer,Integer> {
    
    private WeakReference<MainActivity> context;
    
    public WorkTask(MainActivity context) {
        this.context = new WeakReference<>(context);
    }
    
    @Override
    protected Integer doInBackground(Integer... integers) {
        int param = integers[0];
        Utils.log("doInBackground:" + param);
        
        while (param > 0) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(param);
            param--;
        }
        return param;
    }
    
    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        Utils.log("onProgressUpdate:" + progress);
        MainActivity mainActivity = context.get();
        if(mainActivity != null) {
            mainActivity.showProgress(progress);
        }
    }
    
    @Override
    protected void onPostExecute(Integer integer) {
        Utils.log("onPostExecute:" + integer);
    }
}
