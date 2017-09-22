package com.data.www.datasavedemo.activities;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.data.www.datasavedemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author yhy
 * @time 2017/1/9 11:33
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static final int REQUEST_CODE = 1;
    private static IPermission mListener;

    /**
     * 需要由子类实现，根据以permission为key查找拒绝后的提示，推荐使用map来存储key和提示的对应关系
     * @param permission
     * @return
     */
    abstract String getNotice(String permission);
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
    }
    @Override
    protected void onDestroy() {
        ActivityCollector.removeActivity(this);
        super.onDestroy();
    }
    public static void requestRunTimePermission(String[] permissions, IPermission listener) {
        mListener = listener;
        if (Build.VERSION.SDK_INT<Build.VERSION_CODES.M){
            mListener.onGranted();
            return;
        }
        Activity topActivity = ActivityCollector.getTopActivity();

        if (topActivity == null) {
            return;
        }
        List<String> permissionList = new ArrayList<>();
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(topActivity, permission) != PackageManager.PERMISSION_GRANTED) {
                permissionList.add(permission);
            }
        }
        if (!permissionList.isEmpty()) {
            ActivityCompat.requestPermissions(topActivity, permissionList.toArray(new String[permissionList.size()]), REQUEST_CODE);
        } else {
            //doSomething
            mListener.onGranted();
        }
    }
    private String getNoticeString(List<String> deniedPermissions){
        StringBuilder builder = new StringBuilder();
//        builder.append("你需要一下权限以保证应用功能的正常运行:");
        for (String permission:deniedPermissions) {
            if (!TextUtils.isEmpty(getNotice(permission))){
                builder.append(getNotice(permission)+"\n");
            }
        }
        if (TextUtils.isEmpty(builder.toString()))
            return getString(R.string.app_name);
        return builder.toString();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CODE:
                if (grantResults.length > 0) {
                    final List<String> deniedPermissions = new ArrayList<>();
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        String permission = permissions[i];
                        if (grantResult != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        mListener.onGranted();
                    } else {
                        //权限被拒绝
                        AlertDialog.Builder builder = new AlertDialog.Builder(BaseActivity.this).setTitle("提示").setMessage(getNoticeString(deniedPermissions))
                           .setPositiveButton("去设置", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent=getAppDetailSettingIntent(BaseActivity.this);
                                startActivity(intent);
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mListener.onDenied(deniedPermissions);
                            }
                        });
                        builder.show();

                    }
                }
                break;
            default:
                break;
        }
    }
    /**
     * 获取应用详情页面intent
     *
     * @return
     */
    public static Intent getAppDetailSettingIntent(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        return localIntent;
    }
}
