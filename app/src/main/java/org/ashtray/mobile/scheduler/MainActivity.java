package org.ashtray.mobile.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.ashtray.mobile.scheduler.task.WorkTask;
import org.ashtray.mobile.scheduler.util.Utils;

public class MainActivity extends AppCompatActivity {
    
    private TextView tvProgress;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvProgress = findViewById(R.id.tv_progress);
        
        new WorkTask(this).execute(50);
    }
    
    public void showProgress(int progress) {
        tvProgress.setText(Utils.toString(progress));
    }
    
    void click(View view) {
        switch (view.getId()) {
        
        }
    }
}
