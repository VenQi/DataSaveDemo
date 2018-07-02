package com.data.www.datasavedemo;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.data.www.datasavedemo.activities.FileIoActivity;
import com.data.www.datasavedemo.activities.GreenDaoDemoActivity;
import com.data.www.datasavedemo.activities.LocationActivity;
import com.data.www.datasavedemo.activities.PhoneCallStateActivity;
import com.data.www.datasavedemo.activities.SharedPreferencesActivity;
import com.data.www.datasavedemo.activities.SqliteDBActivity;
import com.data.www.datasavedemo.activities.TestHandlerActivity;
import com.data.www.datasavedemo.activities.TimerActivity;
import com.data.www.datasavedemo.utils.PermissionUtil;

public class MainActivity extends AppCompatActivity {

    //我是feaure1
    //这是feature2
    //这是feature1-fix
    String[] permissions = new String[]{
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
    };
    private PermissionUtil util;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        util = new PermissionUtil(this);
        util.requestPermissions(permissions, 110, new PermissionUtil.PermissionCallBack() {
            @Override
            public void onpermissionsGranted(String[] permissions) {

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
    public void btnClick(View v){
        switch (v.getId()){
            case R.id.sp_sample:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, SharedPreferencesActivity.class);
                startActivity(intent);
                break;
            case R.id.db_sample:
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this, SqliteDBActivity.class);
                startActivity(intent2);
                break;
            case R.id.db_sample_greendao:
                Intent intent3 = new Intent();
                intent3.setClass(MainActivity.this, GreenDaoDemoActivity.class);
                startActivity(intent3);
                break;
            case R.id.file_io_sample:
                Intent intent4 = new Intent();
                intent4.setClass(MainActivity.this, FileIoActivity.class);
                startActivity(intent4);
                break;
            case R.id.handler_test_sample:
                Intent intent5 = new Intent();
                intent5.setClass(MainActivity.this, TestHandlerActivity.class);
                startActivity(intent5);
                break;
            case R.id.handler_phonestate_listener:
                Intent intent6 = new Intent();
                intent6.setClass(MainActivity.this, PhoneCallStateActivity.class);
                startActivity(intent6);
                break;
            case R.id.handler_location:
                Intent intent7 = new Intent();
                intent7.setClass(MainActivity.this, LocationActivity.class);
                startActivity(intent7);
                break;
            case R.id.handler_timer_task_activity:
                Intent intent8 = new Intent();
                intent8.setClass(MainActivity.this, TimerActivity.class);
                startActivity(intent8);
                break;
            default:
                break;
        }
    }
}
