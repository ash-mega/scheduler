package org.ashtray.mobile.scheduler.task;

import android.content.Context;
import android.os.AsyncTask;

import org.ashtray.mobile.scheduler.MainActivity;
import org.ashtray.mobile.scheduler.notification.NotificationCentre;
import org.ashtray.mobile.scheduler.util.Utils;

import java.lang.ref.WeakReference;

import static org.ashtray.mobile.scheduler.IConstants.ID_INT;

public class WorkTask extends AsyncTask<Integer, Integer, Integer> {

    private NotificationCentre centre;

    private int max;

    public WorkTask(Context context) {
        centre = new NotificationCentre(context);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        centre.showNotification(ID_INT);
    }

    @Override
    protected Integer doInBackground(Integer... integers) {
        int param = integers[0];
        max = param;
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
//        Utils.log("onProgressUpdate:" + progress);
        float p = (1.0f - (float)progress / (float)max) * 100;
        int i = (int)p;
        centre.updateProgress(i);
    }

    @Override
    protected void onPostExecute(Integer integer) {
        Utils.log("onPostExecute:" + integer);
        centre.dismiss(ID_INT);
    }
}
