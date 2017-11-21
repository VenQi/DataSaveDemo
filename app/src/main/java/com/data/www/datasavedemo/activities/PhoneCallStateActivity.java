package com.data.www.datasavedemo.activities;

import android.Manifest;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;

import com.data.www.datasavedemo.R;
import com.data.www.datasavedemo.utils.PermissionUtil;

/**
 * 已经测试在activity正常打开的时候可以检测到来电状态，获取来点号码
 * TODO 检测应用打开，activity没打开的时候能不能检测到
 * TODO 思考如何开机自启动，以及如何保持长时间监听来电状态
 */
public class PhoneCallStateActivity extends AppCompatActivity {

    String[] permissions = new String[]{
            Manifest.permission.READ_PHONE_STATE
    };
    private PermissionUtil util;
    private TextView textView;
    TelephonyManager manager ;
    String result = "监听电话状态：/n";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_call_state);
        textView =(TextView) findViewById(R.id.phone_state_result);
        util = new PermissionUtil(this);
        util.requestPermissions(permissions, 110, new PermissionUtil.PermissionCallBack() {
            @Override
            public void onpermissionsGranted(String[] permissions) {
                //获取电话服务
                manager = (TelephonyManager) PhoneCallStateActivity.this.getSystemService(TELEPHONY_SERVICE);
                // 手动注册对PhoneStateListener中的listen_call_state状态进行监听
                manager.listen(new MyPhoneStateListener(), PhoneStateListener.LISTEN_CALL_STATE);
            }

            @Override
            public void onUserHasAlreadyTurnedDownAndDontAsk(String[] permissions) {
                finish();
            }

            @Override
            public void onUserHasAlreadyTurnedDown(String[] permissions) {
                finish();
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        util.onRequestPermissionsResult(requestCode,permissions,grantResults);
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
                    Log.e("手机铃:",result);
                    break;
                case TelephonyManager.CALL_STATE_OFFHOOK:
                    result+=" 电话被挂起了 ";
                    Log.e("挂起:",result);
                default:
                    break;
            }
            textView.setText(result);
            super.onCallStateChanged(state, incomingNumber);
        }

    }

}
