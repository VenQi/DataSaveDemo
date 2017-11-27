package com.data.www.datasavedemo.receivers;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.data.www.datasavedemo.MainActivity;
import com.data.www.datasavedemo.entity.HistoryItem;
import com.data.www.datasavedemo.entity.SystemCache;
import com.data.www.datasavedemo.utils.DBHelper;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PhoneStateReceiver extends BroadcastReceiver {
    TelephonyManager manager;
    String result = "监听电话状态：/n";
    DBHelper helper;
    Long starttime = 0L;
    boolean outgoing_flag = false;//电话是拨出还 是拨入 true为拨出 false为拨入
    String phone_num;
    private MediaRecorder mediaRecorder;
    private File file;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        System.out.println("action" + intent.getAction());
        helper = new DBHelper(context);
        starttime = System.currentTimeMillis();
        Log.e("ActionHelo",intent.getAction());
        Log.e("去电?：",SystemCache.outgoing_call+"");
        if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
            Intent it = new Intent();
            it.setAction(Intent.ACTION_MAIN);
            it.setClass(context, MainActivity.class);
            context.startActivity(it);
        }
        if (intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)) {
            //如果是去电（拨出）
            SystemCache.outgoing_call = true;
            phone_num = getResultData();
        } else {
            //查了下android文档，貌似没有专门用于接收来电的action,所以，非去电即来电
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Service.TELEPHONY_SERVICE);
            tm.listen(new MyPhoneStateListener(context), PhoneStateListener.LISTEN_CALL_STATE);
            //设置一个监听器
        }
    }

    /***
     * 继承PhoneStateListener类，我们可以重新其内部的各种监听方法
     *然后通过手机状态改变时，系统自动触发这些方法来实现我们想要的功能
     */
    class MyPhoneStateListener extends PhoneStateListener {
        private final Context context;
        //获取本次通话的时间(单位:秒)
        int time = 0;
        //判断是否正在通话
        boolean isCalling;
        //控制循环是否结束
        boolean isFinish;
        private ExecutorService service;

        public MyPhoneStateListener(Context context) {
            this.context = context;
            service = Executors.newSingleThreadExecutor();
        }

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            Log.e("incomming",incomingNumber);
            if(!TextUtils.isEmpty(incomingNumber)){
                phone_num = incomingNumber;
            }
            try {
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    Log.e("phone state","CALL_STATE_IDLE"+"正在通话吗："+isCalling+"结束了吗："+isFinish+"去电："+SystemCache.outgoing_call);
//                    if(mediaRecorder != null)
//                    {
//                        mediaRecorder.stop();
//                        mediaRecorder.release();
//                        mediaRecorder = null;
//                    }
                    if (isCalling) {
                        isCalling = false;
                        isFinish = true;
                        service.shutdown();
                        Toast.makeText(context, "本次通话" + time + "秒",
                                Toast.LENGTH_LONG).show();
                        Log.e("本次通话",time+"秒");
                        HistoryItem item = new HistoryItem();
                        item.date = String.valueOf(starttime);
                        item.inorout = SystemCache.outgoing_call?"out":"in";
                        item.lasttime = time+"s";
                        item.phone = phone_num;
                        item.address = "";
                        helper.insertHistory(item);
                        time = 0;
                    }
                    SystemCache.outgoing_call = false;
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    isCalling = true;
                    Log.e("phone state","CALL_STATE_OFFHOOK"+"正在通话吗："+isCalling+"结束了吗："+isFinish+"去电："+SystemCache.outgoing_call);
                    SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    file = new File(Environment.getExternalStorageDirectory(), format.format(new Date(starttime))+ ".3gp");
//                    mediaRecorder = new MediaRecorder();
//                    mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);   //获得声音数据源
//                    mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);   // 按3gp格式输出
//                    mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//                    mediaRecorder.setOutputFile(file.getAbsolutePath());   //输出文件
//
//                        mediaRecorder.prepare();    //准备
//
//                    mediaRecorder.start();
                    service.execute(new Runnable() {
                        @Override
                        public void run() {
                            while (!isFinish) {
                                try {
                                    Thread.sleep(1000);
                                    time++;
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    isFinish = false;
                    Log.e("phone state","CALL_STATE_RINGING"+"正在通话吗："+isCalling+"结束了吗："+isFinish+"去电："+SystemCache.outgoing_call);
                    if (service.isShutdown()) {
                        service = Executors.newSingleThreadExecutor();
                    }
                    break;
            }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}