package com.data.www.datasavedemo.receivers;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;

import com.data.www.datasavedemo.entity.HistoryItem;
import com.data.www.datasavedemo.utils.DBHelper;

public class PhoneStateReceiver extends BroadcastReceiver {
    TelephonyManager manager ;
    String result = "监听电话状态：/n";
    DBHelper helper;
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        System.out.println("action"+intent.getAction());
        helper = new DBHelper(context);
        if(intent.getAction().equals(Intent.ACTION_NEW_OUTGOING_CALL)){
            //如果是去电（拨出）
            String num = getResultData();
            Log.e("拨出:",num);
            HistoryItem item = new HistoryItem();
            item.date = String.valueOf(System.currentTimeMillis());
            item.address = "";
            item.phone = num;
            item.inorout = "out";
            helper.insertHistory(item);
        }else{
            //查了下android文档，貌似没有专门用于接收来电的action,所以，非去电即来电
            System.out.println("来电");
            TelephonyManager tm = (TelephonyManager)context.getSystemService(Service.TELEPHONY_SERVICE);
            tm.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
            //设置一个监听器
        }
    }

    /***
     * 继承PhoneStateListener类，我们可以重新其内部的各种监听方法
     *然后通过手机状态改变时，系统自动触发这些方法来实现我们想要的功能
     */
    class MyPhoneStateListener extends PhoneStateListener {

        @Override
        public void onCallStateChanged(int state, String incomingNumber) {
            switch (state) {
                case TelephonyManager.CALL_STATE_IDLE:
                    result+=" 手机空闲起来了  ";
                    Log.e("空闲:",result);
                    break;
                case TelephonyManager.CALL_STATE_RINGING:
                    result+="  手机铃声响了，来电号码:"+incomingNumber;
                    HistoryItem item = new HistoryItem();
                    item.date = String.valueOf(System.currentTimeMillis());
                    item.address = "";
                    item.phone = incomingNumber;
                    item.inorout = "in";
                    helper.insertHistory(item);
                    Log.e("手机铃:",result);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    result+=" 电话被挂起了 ";
                    Log.e("挂起:",result);
                default:
                    break;
            }
//            textView.setText(result);
            super.onCallStateChanged(state, incomingNumber);
        }

    }
}
