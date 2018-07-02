package com.data.www.datasavedemo.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.data.www.datasavedemo.R;

import java.util.Timer;
import java.util.TimerTask;

public class TimerActivity extends AppCompatActivity {
    TextView txtView;
    Timer timer = new Timer();
    //时间间隔，单位s
    int interval=0;
    //上一次时间.单位 ms
    long lasttime = System.currentTimeMillis();
    //间隔控制
    int period=0;

    int time=0;//时间 s
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        txtView = (TextView)findViewById(R.id.txttime);
        timer.schedule(task, 0, 1000);       // timeTask
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
//                    Log.e("TimerActivity","doSomething...");
//                    timer.cancel();
//                    timer.schedule(task, 0, (long) (Math.pow(2,period)*1000));
//                    period++;
                    txtView.setText(""+time+"s");
                    time++;
                    interval = (int) ((System.currentTimeMillis()-lasttime)/1000);
                    if((System.currentTimeMillis()-lasttime)/1000==Math.pow(2,period)){
                        period++;
                        lasttime = System.currentTimeMillis();
                        Log.e("TimerActivity","doSomething...");
                    }
                }
            });
        }
    };
}
